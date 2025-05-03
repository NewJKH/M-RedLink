package org.nano.redstoneLink.domain.service;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.nano.redstoneLink.domain.block.BlockLink;
import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.domain.repository.RemoterRepository;
import org.nano.redstoneLink.shared.model.Disposable;

import java.util.List;

public class LinkedService {

    private final Disposable disposable = Disposable.getInstance();
    private final RemoterRepository remoterRepository = RemoterRepository.getInstance();

    public void prepareLinking(Player player, Remoter remoter) {
        disposable.addLinkStat(player,true);
        disposable.addRemoter(player,remoter);
    }
    public void succeedLinking(Player player, Block block){
        if ( !disposable.getLinkStats().containsKey(player) ||
             !disposable.getRemoters().containsKey(player)) {
            disposable.init(player);
            return;
        }

        if ( remoterRepository.findByBlock(block.getLocation())){

        }



        Remoter remoter = disposable.getRemoters().get(player);
        List<BlockLink> link = remoter.getLinkedBlocks();


        disposable.getLinkStats().remove(player);
    }
}
