package mc.godhand.vox.util;

import mc.godhand.vox.data.ConfigValidationException;
import org.bukkit.configuration.ConfigurationSection;

public class ConfigUtil {
    public static String requireString(ConfigurationSection section, String path) {
        String value = section.getString(path);
        if(value == null || value.isBlank()) {
            throw new ConfigValidationException("Missing required string at path '" + path + "' in section '" + section.getCurrentPath() + "'.");
        }
        return value;
    }
}
