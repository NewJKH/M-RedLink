package org.nano.redstoneLink.domain.remoter;

import org.bukkit.Location;
import org.nano.redstoneLink.domain.block.BlockLink;
import org.nano.redstoneLink.shared.enums.ControllerType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Remoter {
    private final UUID owner;
    private final String  id;
    private final ControllerType type;
    private Location location;
    private List<BlockLink> linkedBlocks;

    private int maxLinkedBlocks = 10;
    private String password = "0000";
    private Set<UUID> whitelist = new HashSet<>();

    public Remoter(UUID owner, String id, ControllerType type, Location location, List<BlockLink> linkedBlocks) {
        this.owner = owner;
        this.linkedBlocks = linkedBlocks;
        this.location = location;
        this.type = type;
        this.id = id;
    }

    public Remoter(UUID owner, String id, ControllerType type, Location location, List<BlockLink> linkedBlocks, int maxLinkedBlocks, String password, Set<UUID> whitelist) {
        this.owner = owner;
        this.id = id;
        this.type = type;
        this.location = location;
        this.linkedBlocks = linkedBlocks;
        this.maxLinkedBlocks = maxLinkedBlocks;
        this.password = password;
        this.whitelist = whitelist;
    }

    public UUID getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }

    public ControllerType getType() {
        return type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public List<BlockLink> getLinkedBlocks() {
        return linkedBlocks;
    }

    public void setLinkedBlocks(List<BlockLink> linkedBlocks) {
        this.linkedBlocks = linkedBlocks;
    }

    public int getMaxLinkedBlocks() {
        return maxLinkedBlocks;
    }

    public void setMaxLinkedBlocks(int maxLinkedBlocks) {
        this.maxLinkedBlocks = maxLinkedBlocks;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UUID> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(Set<UUID> whitelist) {
        this.whitelist = whitelist;
    }
}
