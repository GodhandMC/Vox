package mc.godhand.vox.config.parsers;

import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Locale;

public final class ColorParser {

    public static NamedTextColor parse(String input) {
        if(input == null) {
            return NamedTextColor.WHITE;
        }
        NamedTextColor color = NamedTextColor.NAMES.value(input.toLowerCase(Locale.ROOT));
        if(color == null) {
            throw new ConfigValidationException("Invalid color: " + input);
        }
        return color;
    }
}
