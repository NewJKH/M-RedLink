package org.nano.redstoneLink.domain.service;

import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.bukkit.entity.Player;
import org.nano.redstoneLink.domain.block.BlockLink;
import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.domain.repository.RemoterRepository;
import org.nano.redstoneLink.shared.model.Disposable;

public class LinkedService {

    private final Disposable disposable = Disposable.getInstance();
    private final RemoterRepository remoterRepository = RemoterRepository.getInstance();

    public void prepareLinking(Player player, Remoter remoter) {
        disposable.addLinkStat(player,true);
        disposable.addRemoter(player,remoter);
    }

    public boolean succeedLinking(Player player, Block block){
        if ( !disposable.getLinkStats().containsKey(player) ||
             !disposable.getRemoters().containsKey(player)) {
            disposable.init(player);
            return false;
        }

        if (!(block.getBlockData() instanceof Powerable) || !(block.getBlockData() instanceof Directional)) {
            player.sendMessage("레드스톤이 적용되지 않는 블럭입니다.");
            return false;
        }

        if ( remoterRepository.findByBlock(block.getLocation())){
            player.sendMessage(" 이미 연결된 블럭 입니다.");
            return false;
        }

        Remoter remoter = disposable.getRemoters().get(player);
        BlockLink blockLink = new BlockLink(remoter.getLinkedBlocks().size(),block.getLocation(),"블럭","테스트");
        remoterRepository.addLinedBlock(remoter,blockLink);

        player.sendMessage("연결되었습니다.");

        disposable.getLinkStats().remove(player);
        return true;
    }
}
