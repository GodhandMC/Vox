package mc.godhand.vox.chat;

import mc.godhand.vox.channel.VoxChannel;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Chatter {

    private final UUID uuid;
    private VoxChannel focusedChannel;
    private VoxChannel lastChannel;
    private TextColor emoteColor;
    private long lastMessageTimestamp;

    public Chatter(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public VoxChannel getFocusedChannel() {
        return focusedChannel;
    }

    public void setFocusedChannel(VoxChannel focusedChannel) {
        this.focusedChannel = focusedChannel;
    }

    public VoxChannel getLastChannel() {
        return lastChannel;
    }

    public void setLastChannel(VoxChannel lastChannel) {
        this.lastChannel = lastChannel;
    }

    public TextColor getEmoteColor() {
        return emoteColor;
    }

    public void setEmoteColor(TextColor emoteColor) {
        this.emoteColor = emoteColor;
    }

    public long getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public void setLastMessageTimestamp(long lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
    }
}
