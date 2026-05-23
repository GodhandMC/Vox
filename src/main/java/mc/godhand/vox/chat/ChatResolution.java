package mc.godhand.vox.chat;

import mc.godhand.vox.Vox;
import mc.godhand.vox.channel.VoxChannel;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public record ChatResolution(VoxChannel channel, String message, boolean focusSwitch) {
    public static ChatResolution resolveChat(Chatter chatter, String rawMessage) {
        if(!rawMessage.startsWith("#")) {
            VoxChannel focused = chatter.getFocusedChannel();
            return new ChatResolution(focused, rawMessage, false);
        }

        String withoutHash = rawMessage.substring(1);
        String[] split = withoutHash.split(" ", 2);
        String alias = split[0].toLowerCase();
        VoxChannel channel = Vox.getInstance().getVoxConfig().getChannel(alias);
        if(channel == null) {
            return new ChatResolution(null, rawMessage, false);
        }

        if(split.length == 1) {
            chatter.setFocusedChannel(channel);
            chatter.getPlayer().sendMessage(Component.text("Focused channel set to ", NamedTextColor.GREEN).append(channel.getFormattedDisplayName()));
            return new ChatResolution(null, null, true);
        }

        return new ChatResolution(channel, split[1], false);
    }
}
