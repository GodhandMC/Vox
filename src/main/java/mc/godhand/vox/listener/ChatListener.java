package mc.godhand.vox.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import mc.godhand.vox.Vox;
import mc.godhand.vox.channel.VoxChannel;
import mc.godhand.vox.channel.range.ChannelRange;
import mc.godhand.vox.channel.range.GlobalRange;
import mc.godhand.vox.channel.range.ProximityRange;
import mc.godhand.vox.chat.Chatter;
import mc.godhand.vox.util.ComponentUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


import java.util.HashSet;
import java.util.Set;

public class ChatListener implements Listener {

    private final Vox plugin;

    public ChatListener(Vox plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        String message = ComponentUtil.getContent(event.message());
        event.setCancelled(true);

        Chatter chatter = plugin.getChatManager().getChatter(player.getUniqueId());
        VoxChannel channel = chatter.getFocusedChannel();
        if(channel == null) {
            return; // Fallback later
        }

        handleMessage(player, channel, message);
    }

    private void handleMessage(Player player, VoxChannel channel, String message) {
        if(!player.hasPermission(channel.getSpeakPermission())) return;

        Set<Player> recipients = resolveRecipients(player, channel);
        Component formatted = format(player, channel, message);

        for(Player target : recipients) {
            target.sendMessage(formatted);
        }
    }

    private Set<Player> resolveRecipients(Player sender, VoxChannel channel) {
        ChannelRange range = channel.getRange();
        if(range instanceof GlobalRange) {
            return new HashSet<>(Bukkit.getOnlinePlayers());
        }

        if(range instanceof ProximityRange prox) {
            Set<Player> result = new HashSet<>();
            for(Player target : Bukkit.getOnlinePlayers()) {
                if(!target.getWorld().equals(sender.getWorld())) {
                    continue;
                }
                if(sender.getLocation().distanceSquared(target.getLocation()) <= prox.radius() * prox.radius()) {
                    result.add(target);
                }
            }
            return result;
        }
        return Set.of(sender);
    }

    private Component format(Player sender, VoxChannel channel, String message) {
        String name = sender.getName(); // later: character system
        return Component.text()
                .append(channel.getFormattedTag())
                .append(Component.space())
                .append(Component.text(name + ": ", NamedTextColor.WHITE))
                .append(Component.text(message, channel.getColor()))
                .build();
    }
}
