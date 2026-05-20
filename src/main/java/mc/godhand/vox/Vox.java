package mc.godhand.vox;

import mc.godhand.vox.channel.VoxChannel;
import mc.godhand.vox.data.ConfigValidationException;
import mc.godhand.vox.data.VoxConfig;
import mc.godhand.vox.data.VoxConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vox extends JavaPlugin {

    private VoxConfig voxConfig;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        try {
            this.voxConfig = new VoxConfigLoader(this).load();
        } catch(ConfigValidationException e) {
            getLogger().severe("Failed to load configuration: " + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        for(VoxChannel channel : voxConfig.channels().values()) {
            getLogger().info("Loaded channel '" + channel.getId() + "' with tag '" + channel.getTag() + "'.");
        }
    }

    @Override
    public void onDisable() {
    }

    public VoxConfig getVoxConfig() {
        return voxConfig;
    }

    public void setVoxConfig(VoxConfig voxConfig) {
        this.voxConfig = voxConfig;
    }
}
