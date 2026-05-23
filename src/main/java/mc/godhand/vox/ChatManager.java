package mc.godhand.vox;

import mc.godhand.vox.channel.VoxChannel;
import mc.godhand.vox.config.VoxConfig;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChatManager {

    private final Vox plugin;

    // Player state registry
    private final Map<UUID, Chatter> chatters = new ConcurrentHashMap<>();

    public ChatManager(Vox plugin) {
        this.plugin = plugin;
    }

    // -----------------------------
    // Lifecycle
    // -----------------------------

    public void join(Player player) {
        Chatter chatter = new Chatter(player.getUniqueId());
        chatters.put(player.getUniqueId(), chatter);

        chatter.loadSettings();
    }

    public void quit(Player player) {
        Chatter chatter = chatters.remove(player.getUniqueId());
        if(chatter == null) return;

        chatter.saveSettings();
    }

    // -----------------------------
    // Access
    // -----------------------------

    public Chatter getChatter(Player player) {
        return chatters.get(player.getUniqueId());
    }

    public Chatter getChatter(UUID uuid) {
        return chatters.get(uuid);
    }

    public boolean hasChatter(Player player) {
        return chatters.containsKey(player.getUniqueId());
    }

    // -----------------------------
    // Channel helpers
    // -----------------------------

    public VoxChannel getChannel(String alias) {
        VoxConfig config = plugin.getVoxConfig();
        return config.getChannelByAlias(alias);
    }

    public VoxChannel getDefaultChannel() {
        return plugin.getVoxConfig().channels().values()
                .stream()
                .filter(VoxChannel::isPermanent)
                .findFirst()
                .orElse(null);
    }

    // -----------------------------
    // Chat entry point (optional but recommended)
    // -----------------------------

    public void handleChat(Player player, String message) {
        Chatter chatter = getChatter(player);

        if(chatter == null) {
            chatter = new Chatter(player.getUniqueId());
            chatters.put(player.getUniqueId(), chatter);
        }

        chatter.handleChat(message);
    }
}
