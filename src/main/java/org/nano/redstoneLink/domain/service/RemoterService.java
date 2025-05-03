package org.nano.redstoneLink.domain.service;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.app.ui.MenuGUI;
import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.domain.repository.RemoterRepository;
import org.nano.redstoneLink.shared.enums.ControllerType;

import java.util.ArrayList;
import java.util.Optional;

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
       if ( remoterOpt.isPresent() ) {
           Remoter remoter = remoterOpt.get();
           return player.isOp() || player.getUniqueId().equals(remoter.getOwner()) || remoter.getWhitelist().contains(player.getUniqueId());
       }
       return false;
    }

    public void openRemoter(Player player, Remoter remoter) {
        new MenuGUI(remoter).progress(player);
    }
}
