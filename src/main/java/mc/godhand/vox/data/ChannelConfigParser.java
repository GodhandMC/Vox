package mc.godhand.vox.data;

import mc.godhand.vox.channel.VoxChannel;
import mc.godhand.vox.channel.range.ChannelRange;
import mc.godhand.vox.util.ConfigUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.Set;

public final class ChannelConfigParser {

    public static VoxChannel parse(
            String id,
            ConfigurationSection section
    ) {

        String tag = ConfigUtil.requireString(section, "tag");
        String displayName = ConfigUtil.requireString(section, "display-name");
        Set<String> commands = new HashSet<>(section.getStringList("commands"));
        NamedTextColor color = ColorParser.parse(section.getString("color"));
        NamedTextColor bracketColor = ColorParser.parse(section.getString("bracket-color", section.getString("color")));
        ChannelRange range = RangeParser.parse(section.getConfigurationSection("range"));
        int cooldown = section.getInt("cooldown", 0);
        boolean permanent = section.getBoolean("permanent", true);
        boolean networked = section.getBoolean("networked", false);
        boolean staff = section.getBoolean("staff", false);

        return new VoxChannel(
                id,
                tag,
                displayName,
                commands,
                color,
                bracketColor,
                range,
                cooldown,
                permanent,
                networked,
                staff
        );
    }
}
