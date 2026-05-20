package mc.godhand.vox.channel.range;

import org.bukkit.Location;

public record ProximityRange(double radius) implements ChannelRange {

    public ProximityRange {
        if(radius < 0) {
            throw new IllegalArgumentException("Radius cannot be negative");
        }
    }

    public boolean isInRange(Location senderLocation, Location recipientLocation) {
        if(senderLocation.getWorld() != recipientLocation.getWorld()) {
            return false;
        }
        return senderLocation.distanceSquared(recipientLocation) <= radius * radius;
    }
}
