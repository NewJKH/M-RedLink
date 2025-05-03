package org.nano.redstoneLink.shared.model;

import org.bukkit.entity.Player;
import org.nano.redstoneLink.domain.remoter.Remoter;

import java.util.HashMap;
import java.util.Map;

public class Disposable {
    private static Disposable instance;
    private final Map<Player, Boolean> linkStats;
    private final Map<Player, Remoter> remoters;
    public Disposable() {
        linkStats = new HashMap<>();
        remoters = new HashMap<>();
    }

    public static Disposable getInstance() {
        if (instance == null) {
            instance = new Disposable();
        }
        return instance;
    }

    public Map<Player, Boolean> getLinkStats() {
        return linkStats;
    }
    public void addLinkStat(Player player, boolean stat) {
        linkStats.put(player, stat);
    }

    public Map<Player, Remoter> getRemoters() {
        return remoters;
    }
    public void addRemoter(Player player, Remoter remoter) {
        remoters.put(player, remoter);
    }

    public void init(Player player){
        linkStats.remove(player);
        remoters.remove(player);
    }
}
