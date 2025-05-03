package org.nano.redstoneLink.domain.service;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.domain.repository.RemoterRepository;
import org.nano.redstoneLink.shared.enums.ControllerType;

import java.util.ArrayList;

public class RemoterService {

    private final RemoterRepository remoterRepository = RemoterRepository.getInstance();

    public boolean has(String unq) {
        return remoterRepository.getById(unq).isPresent();
    }

    public boolean isLocationCache(@NotNull Location location) {
        return remoterRepository.getRemoters().stream()
                .map(remoter -> remoter.getLocation().equals(location))
                .findAny()
                .isPresent();
    }

    public void save(Remoter remoter) {
        remoterRepository.addRemoter(remoter);
    }

    public void save(String uni, Location loc) {
        Remoter remoter = new Remoter(uni,ControllerType.LEVER,loc,new ArrayList<>());
        remoterRepository.addRemoter(remoter);
    }
}
