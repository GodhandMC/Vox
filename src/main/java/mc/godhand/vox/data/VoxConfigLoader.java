package mc.godhand.vox.data;

import mc.godhand.vox.channel.VoxChannel;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class VoxConfigLoader {

    private final JavaPlugin plugin;

    public VoxConfigLoader(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public VoxConfig load() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        boolean saveFocus = config.getBoolean("save-focus", true);

        ConfigurationSection channelsSection = config.getConfigurationSection("channels");
        if(channelsSection == null) {
            throw new ConfigValidationException("Missing 'channels' section.");
        }

        Map<String, VoxChannel> channels = new HashMap<>();
        Map<String, VoxChannel> aliasIndex = new HashMap<>();

        for(String id : channelsSection.getKeys(false)) {
            ConfigurationSection section = channelsSection.getConfigurationSection(id);
            VoxChannel channel = ChannelConfigParser.parse(id, section);
            validateUniqueAliases(channel, aliasIndex);
            channels.put(id, channel);
            indexAliases(channel, aliasIndex);
        }

        return new VoxConfig(saveFocus, Map.copyOf(channels), Map.copyOf(aliasIndex));
    }

    private void validateUniqueAliases(VoxChannel channel, Map<String, VoxChannel> aliasIndex) {
        for(String alias : channel.getCommands()) {
            String normalized = alias.toLowerCase(Locale.ROOT);
            if(aliasIndex.containsKey(normalized)) {
                throw new ConfigValidationException(
                        "Duplicate command alias '"
                                + alias
                                + "' between channels '"
                                + channel.getId()
                                + "' and '"
                                + aliasIndex.get(normalized).getId()
                                + "'"
                );
            }
        }
    }

    private void indexAliases(VoxChannel channel, Map<String, VoxChannel> aliasIndex) {
        for(String alias : channel.getCommands()) {
            aliasIndex.put(alias.toLowerCase(Locale.ROOT), channel);
        }
    }
}