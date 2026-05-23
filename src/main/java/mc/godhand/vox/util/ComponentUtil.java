package mc.godhand.vox.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class ComponentUtil {
    public static String getContent(Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }
}
