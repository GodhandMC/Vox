package mc.godhand.vox.data;

import mc.godhand.vox.channel.VoxChannel;

import java.util.Map;

public record VoxConfig(boolean saveFocus, Map<String, VoxChannel> channels, Map<String, VoxChannel> aliasIndex) {


}
