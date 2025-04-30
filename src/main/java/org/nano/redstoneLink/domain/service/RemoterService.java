package org.nano.redstoneLink.domain.service;

import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.domain.repository.RemoterRepository;
import org.nano.redstoneLink.shared.enums.ControllerType;

import java.util.ArrayList;

public class RemoterService {

    private final RemoterRepository remoterRepository = RemoterRepository.getInstance();

    public boolean has(int unq) {
        return remoterRepository.getById(unq).isPresent();
    }

    public void addRemoter(int unq) {
        Remoter remoter = new Remoter(unq, ControllerType.LEVER, null,new ArrayList<>());
        remoterRepository.addRemoter(remoter);
    }
}
