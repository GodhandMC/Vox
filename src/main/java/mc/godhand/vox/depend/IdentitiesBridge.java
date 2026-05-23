package mc.godhand.vox.depend;

import mc.godhand.identities.Identities;
import mc.godhand.identities.data.Character;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public final class IdentitiesBridge {

    public static final boolean IS_ENABLED = Bukkit.getPluginManager().isPluginEnabled("Identities");

    private IdentitiesBridge() {
    }

    public static Character getCharacter(UUID uuid) {
        if(!IS_ENABLED) {
            return null;
        }

        return Identities.getInstance().getAccountHandler().getAccount(uuid).findActive().orElse(null);
    }

    public static String getDisplayName(Player player) {
        if(!IS_ENABLED) {
            return player.getDisplayName();
        }

        Character identity = getCharacter(player.getUniqueId());

        return identity != null ? identity.getName() : getPlayerName(player);
    }

    // Not implemented yet
    public static boolean hasPrefix(Player player) {
        if(!IS_ENABLED) {
            return false;
        }

        Character identity = getCharacter(player.getUniqueId());

        return false;
    }

    public static String getPersonaName(Player player) {
        if(!IS_ENABLED) {
            return player.getDisplayName();
        }

        Character identity = getCharacter(player.getUniqueId());

        return identity != null ? identity.getName() : getPlayerName(player);
    }

    public static int getCurrentPersonaId(Player player) {
        if(!IS_ENABLED) {
            return 0;
        }

        Character identity = getCharacter(player.getUniqueId());

        return identity != null ? identity.getId() : 0;
    }

    private static String getPlayerName(Player player) {
        return PlainTextComponentSerializer.plainText().serialize(player.displayName());
    }
}