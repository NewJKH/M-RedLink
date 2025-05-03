package org.nano.redstoneLink.domain.service;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.app.ui.MenuGUI;
import org.nano.redstoneLink.domain.block.BlockLink;
import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.domain.repository.RemoterRepository;
import org.nano.redstoneLink.shared.enums.BlockState;
import org.nano.redstoneLink.shared.enums.ControllerType;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class RemoterService {

    private final RemoterRepository remoterRepository = RemoterRepository.getInstance();

    public boolean has(String unq) {
        return remoterRepository.getById(unq).isPresent();
    }

    public boolean isLocationCache(@NotNull Location location) {
        return remoterRepository.getRemoters().stream()
                .anyMatch(remoter -> remoter.getLocation().equals(location));
    }

    public void save(Remoter remoter) {
        remoterRepository.addRemoter(remoter);
    }

    public void save(Player player, String uni, Location loc) {
        Remoter remoter = new Remoter(player.getUniqueId(), uni,ControllerType.LEVER,loc,new ArrayList<>());
        remoterRepository.addRemoter(remoter);
    }

    public Remoter getRemoterByLocation(Location loc) {
        return remoterRepository.getByLocation(loc)
                .orElseThrow(() -> new IllegalArgumentException("Remoter not found"));
    }

    public boolean containWhiteList(Player player, Remoter remoter) {
        return remoter.getWhitelist()
                .contains(player.getUniqueId());
    }

    public boolean useRemoter(Player player, Location loc) {
        Optional<Remoter> remoterOpt = remoterRepository.getByLocation(loc);
        if (remoterOpt.isEmpty()) {
            return false;
        }

        Remoter remoter = remoterOpt.get();

        if (!canUseRemoter(player, remoter)) {
            return false;
        }

        World world = loc.getWorld();
        if (world == null) {
            return false;
        }

        for (BlockLink link : remoter.getLinkedBlocks()) {
            if (link.getState() != BlockState.UNLOCK) continue;

            Location linkLoc = link.getLocation();
            Block block = world.getBlockAt(linkLoc);
            BlockData data = block.getBlockData();

            if (data instanceof Powerable powerable) {
                powerable.setPowered(true);
                block.setBlockData(powerable);
            }
            if (data instanceof Directional directional){
                directional.setFacing(directional.getFacing());
            }
        }

        return true;
    }

    private boolean canUseRemoter(Player player, Remoter remoter) {
        UUID uuid = player.getUniqueId();
        return player.isOp()
                || uuid.equals(remoter.getOwner())
                || remoter.getWhitelist().contains(uuid);
    }


    public void openRemoter(Player player, Remoter remoter) {
        new MenuGUI(remoter).progress(player);
    }
}
