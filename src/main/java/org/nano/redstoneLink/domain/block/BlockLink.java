package org.nano.redstoneLink.domain.block;

import org.bukkit.Location;
import org.nano.redstoneLink.shared.enums.BlockState;

public class BlockLink {
    private final int id;
    private final Location location;

    private String name;
    private String lore;
    private BlockState state = BlockState.UNLOCK;

    public BlockLink(int id, Location location, String name, String lore) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.lore = lore;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(BlockState state) {
        this.state = state;
    }
}
