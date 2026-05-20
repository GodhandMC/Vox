package mc.godhand.vox.channel;

import mc.godhand.vox.channel.range.ChannelRange;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Set;

public final class VoxChannel {

    // Internal ID
    private final String id;

    // User-facing metadata
    private final String tag;
    private final String displayName;

    // Commands / aliases
    private final Set<String> commands;

    // Styling
    private final NamedTextColor color;
    private final NamedTextColor bracketColor;

    // Behavior
    private final ChannelRange range;
    private final int cooldownSeconds;
    private final boolean permanent;
    private final boolean proxy;

    // Permissions
    private final String usePermission;
    private final String speakPermission;
    private final String moderatePermission;

    // Cached formatted components
    private final Component formattedTag;
    private final Component formattedDisplayName;

    public VoxChannel(String id, String tag, String displayName, Set<String> commands, NamedTextColor color, NamedTextColor bracketColor, ChannelRange range, int cooldownSeconds,
                      boolean permanent, boolean proxy) {
        this.id = id.toLowerCase();
        this.tag = tag;
        this.displayName = displayName;
        this.commands = Set.copyOf(commands);

        this.color = color;
        this.bracketColor = bracketColor;

        this.range = range;
        this.cooldownSeconds = cooldownSeconds;
        this.permanent = permanent;
        this.proxy = proxy;
        this.usePermission = "vox.channel." + id;
        this.speakPermission = usePermission + ".speak";
        this.moderatePermission = usePermission + ".mod";

        // Cache formatted components once
        this.formattedTag = Component.text("[", bracketColor)
                .append(Component.text(tag, color))
                .append(Component.text("]", bracketColor));

        this.formattedDisplayName =
                Component.text("[", bracketColor)
                        .append(Component.text(displayName, color))
                        .append(Component.text("]", bracketColor));
    }

    public String getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Set<String> getCommands() {
        return commands;
    }

    public NamedTextColor getColor() {
        return color;
    }

    public NamedTextColor getBracketColor() {
        return bracketColor;
    }

    public ChannelRange getRange() {
        return range;
    }

    public int getCooldownSeconds() {
        return cooldownSeconds;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public boolean isProxy() {
        return proxy;
    }

    public String getUsePermission() {
        return usePermission;
    }

    public String getSpeakPermission() {
        return speakPermission;
    }

    public String getModeratePermission() {
        return moderatePermission;
    }

    public Component getFormattedTag() {
        return formattedTag;
    }

    public Component getFormattedDisplayName() {
        return formattedDisplayName;
    }
}
