package mc.godhand.vox.chat;

import mc.godhand.vox.channel.VoxChannel;

import java.util.UUID;

public class Chatter {
    private final UUID uuid;
    private VoxChannel focusedChannel;
    private VoxChannel lastChannel;
    private String emoteColor;
    private long lastMessageTimestamp;
    public Chatter(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
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

    public String getEmoteColor() {
        return emoteColor;
    }

    public void setEmoteColor(String emoteColor) {
        this.emoteColor = emoteColor;
    }

    public long getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public void setLastMessageTimestamp(long lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
    }
}
