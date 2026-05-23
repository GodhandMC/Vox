package mc.godhand.vox;

import mc.godhand.vox.channel.VoxChannel;
import mc.godhand.vox.config.VoxConfig;
import mc.godhand.vox.config.VoxConfigLoader;
import mc.godhand.vox.config.parsers.ConfigValidationException;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vox extends JavaPlugin {

    public static Vox instance;
    private VoxConfig voxConfig;
    private ChatManager chatManager;

    private LuckPerms luckPerms;

    public static Vox getInstance() {
        return instance;
    }

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

        this.chatManager = new ChatManager(this);

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if(provider != null) {
            this.luckPerms = provider.getProvider();
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

    public ChatManager getChatManager() {
        return chatManager;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }
}
