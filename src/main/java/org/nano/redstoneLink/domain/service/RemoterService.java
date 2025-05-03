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

    public void addRemoter(String unq) {
        Remoter remoter = new Remoter(unq, ControllerType.LEVER, null,new ArrayList<>());
        remoterRepository.addRemoter(remoter);
    }

    public boolean isLocationCache(@NotNull Location location) {
        return remoterRepository.getRemoters().stream()
                .map(remoter -> remoter.getLocation().equals(location))
                .findFirst()
                .isPresent();
    }

    public void save(String uni, Location loc) {
        remoterRepository.getById(uni)
                .ifPresentOrElse(
                        remoter -> remoter.setLocation(loc),
                        ()-> System.out.println("오류발생 = " + "ERROR 2"));
    }
}
