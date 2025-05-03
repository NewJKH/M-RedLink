package org.nano.redstoneLink.domain.repository;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.domain.block.BlockLink;
import org.nano.redstoneLink.domain.remoter.Remoter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoterRepository {
    private static RemoterRepository instance;
    private List<Remoter> remoters;
    public RemoterRepository() {
        this.remoters = new ArrayList<>();
    }

    public static RemoterRepository getInstance() {
        if (instance == null) {
            instance = new RemoterRepository();
        }
        return instance;
    }

    public List<Remoter> getRemoters() {
        return remoters;
    }

    public void setRemoters(List<Remoter> remoters) {
        this.remoters = remoters;
    }

    public Optional<Remoter> getById(String id){
        return remoters.stream()
                .filter(remoter -> remoter.getId().equals(id))
                .findFirst();
    }

    public void addRemoter(Remoter remoter){
        remoters.removeIf(r -> r.getId().equals(remoter.getId()));
        remoters.add(remoter);
    }

    public void addLinedBlock(Remoter remoter, BlockLink blockLink){
        remoter.getLinkedBlocks().add(blockLink);
    }

    public Optional<Remoter> getByLocation(Location loc) {
        return remoters.stream()
                .filter(remoter -> remoter.getLocation().equals(loc))
                .findFirst();
    }

    public boolean findByBlock(@NotNull Location location) {
        return remoters.stream()
                .anyMatch(remoter -> remoter.getLinkedBlocks().stream()
                        .anyMatch(blockLink -> blockLink.getLocation().equals(location))
                );
    }

}
