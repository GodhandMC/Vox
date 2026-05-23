package mc.godhand.vox.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandManager;
import co.aikar.commands.InvalidCommandArgument;
import co.aikar.commands.PaperCommandManager;
import co.aikar.commands.annotation.*;
import mc.godhand.vox.Vox;
import mc.godhand.vox.channel.VoxChannel;
import mc.godhand.vox.chat.Chatter;
import mc.godhand.vox.config.VoxConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("vox")
public class VoxCommand extends BaseCommand {

    private final Vox plugin;

    public VoxCommand(Vox plugin, PaperCommandManager manager) {
        this.plugin = plugin;
        manager.getCommandContexts().registerContext(VoxChannel.class, c -> {
            VoxConfig config = plugin.getVoxConfig();
            VoxChannel channel = config.getChannel(c.popFirstArg());
            if(channel == null) {
                throw new InvalidCommandArgument("Unknown channel.");
            }
            return channel;
        });
        manager.getCommandCompletions().registerCompletion("voxchannels", c -> {
            VoxConfig config = plugin.getVoxConfig();
            return config.channels().keySet();
        });
    }

    @Subcommand("focus")
    @Description("Switches focused channel")
    @CommandCompletion("@voxChannel")
    public void focus(Player player, VoxChannel channel) {
        if(channel == null) {
            player.sendMessage("Channel not found.");
            return;
        }
        Chatter chatter = plugin.getChatManager().getChatter(player.getUniqueId());
        if(!player.hasPermission(channel.getSpeakPermission())) {
            player.sendMessage("You don't have permission to join that channel.");
            return;
        }
        chatter.setFocusedChannel(channel);
        player.sendMessage("Focused channel set to " + channel.getId());
    }

    @Default
    @Description("Lists available channels")
    public void list(Player player) {
        VoxConfig config = plugin.getVoxConfig();
        Component message = Component.text("Available channels: ");
        for(VoxChannel channel : config.channels().values()) {
            if(!player.hasPermission(channel.getSpeakPermission())) continue;
            Component channelComp = Component.text(channel.getDisplayName()).color(channel.getColor())
                    .clickEvent(ClickEvent.suggestCommand("/vox focus " + channel.getId()));
            message = message.append(channelComp).append(Component.text(", "));
        }
        player.sendMessage(message);
    }
}
