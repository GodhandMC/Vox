package mc.godhand.vox.config;

import mc.godhand.vox.channel.VoxChannel;

import java.util.Map;

public record VoxConfig(boolean saveFocus, Map<String, VoxChannel> channels, Map<String, VoxChannel> aliasIndex) {
    public VoxChannel getChannelByAlias(String alias) {
        return aliasIndex.get(alias.toLowerCase());
    }
}
