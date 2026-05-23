package mc.godhand.vox.data.channel;

import mc.godhand.vox.channel.range.ChannelRange;
import mc.godhand.vox.channel.range.GlobalRange;
import mc.godhand.vox.channel.range.ProximityRange;
import mc.godhand.vox.data.ConfigValidationException;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Locale;

public final class RangeParser {

    public static ChannelRange parse(ConfigurationSection section) {
        if(section == null) {
            return GlobalRange.INSTANCE;
        }

        String type = section.getString("type", "GLOBAL");

        return switch(type.toUpperCase(Locale.ROOT)) {
            case "GLOBAL" -> GlobalRange.INSTANCE;
            case "PROXIMITY" -> {
                int distance = section.getInt("distance");
                if(distance < 0) {
                    throw new ConfigValidationException("Proximity distance cannot be negative.");
                }
                yield new ProximityRange(distance);
            }
            default -> throw new ConfigValidationException("Unknown range type: " + type);
        };
    }
}
