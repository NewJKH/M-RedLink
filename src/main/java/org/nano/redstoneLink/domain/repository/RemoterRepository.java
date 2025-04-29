package org.nano.redstoneLink.domain.repository;

import org.nano.redstoneLink.domain.block.BlockLink;
import org.nano.redstoneLink.domain.remoter.Remoter;

import java.util.List;
import java.util.Optional;

public class RemoterRepository {
    private List<Remoter> remoters;

    public RemoterRepository(List<Remoter> remoters) {
        this.remoters = remoters;
    }

    public List<Remoter> getRemoters() {
        return remoters;
    }

    public void setRemoters(List<Remoter> remoters) {
        this.remoters = remoters;
    }

    public Optional<Remoter> getById(int id){
        return remoters.stream()
                .filter(remoter -> remoter.getId() == id)
                .findFirst();
    }

    public void addRemoter(Remoter remoter){
        remoters.add(remoter);
    }

    public void addLinedBlock(BlockLink blockLink){

    }
}
