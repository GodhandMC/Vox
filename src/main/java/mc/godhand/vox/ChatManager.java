package mc.godhand.vox;

import mc.godhand.vox.chat.Chatter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChatManager {

    private final Vox plugin;

    private final Map<UUID, Chatter> chatters = new ConcurrentHashMap<>();

    public ChatManager(Vox plugin) {
        this.plugin = plugin;
    }

    public Chatter getChatter(UUID uuid) {
        return chatters.computeIfAbsent(uuid, Chatter::new);
    }

    public void removeChatter(UUID uuid) {
        chatters.remove(uuid);
    }
}
