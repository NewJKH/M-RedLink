package org.nano.redstoneLink.domain.service;

import org.nano.redstoneLink.domain.repository.RemoterRepository;

public class RemoterService {

    private RemoterRepository remoterRepository;

    public boolean has(int unq) {
        return remoterRepository.getById(unq).isPresent();
    }
}
