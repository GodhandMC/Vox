package mc.godhand.vox.config;

import mc.godhand.vox.channel.VoxChannel;

import java.util.Locale;
import java.util.Map;

public record VoxConfig(boolean saveFocus, Map<String, VoxChannel> channels, Map<String, VoxChannel> aliasIndex) {
    public VoxChannel getChannel(String input) {
        if (input == null) return null;

        String key = input.toLowerCase(Locale.ROOT);

        VoxChannel ch = channels.get(key);
        if (ch != null) return ch;

        return aliasIndex.get(key);
    }
}
