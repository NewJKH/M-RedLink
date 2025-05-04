package org.nano.redstoneLink.shared.registry;

import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

public class VisualLampRegistry {
    private final Set<Location> litLocations = new HashSet<>();


    public boolean isLit(Location loc) {
        return litLocations.contains(loc);
    }


    public void toggle(Location loc) {
        if (isLit(loc)) {
            litLocations.remove(loc);
        } else {
            litLocations.add(loc);
        }
    }

    public void setLit(Location loc, boolean state) {
        if (state) {
            litLocations.add(loc);
        } else {
            litLocations.remove(loc);
        }
    }

    public void clear() {
        litLocations.clear();
    }

}
