package org.nano.redstoneLink.domain.controller;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.nano.redstoneLink.domain.service.ItemService;
import org.nano.redstoneLink.domain.service.LinkedService;
import org.nano.redstoneLink.domain.service.PlayerStatService;
import org.nano.redstoneLink.domain.service.RemoterService;

public class EventController {
    private final LinkedService linkedService;
    private final RemoterService remoterService;
    private final ItemService itemService;
    private final PlayerStatService playerStatService;

    public EventController(LinkedService linkedService,
                      RemoterService remoterService,
                      ItemService itemService) {
        this.linkedService = linkedService;
        this.remoterService = remoterService;
        this.itemService = itemService;
        this.playerStatService = new PlayerStatService();
    }

    /**
     * 컨트롤러를 설치할 때 발동되는 Event
     * 검사 과정 :
     *  1. 설치할 때 해당 아이템이 컨트롤러인지 확인
     *  2. 컨트롤러인지 확인되었다면, 해당 UniqueName 으로된 컨트롤러가 이미 설치가 되어 있는지 확인
     *  3. 설치하려는 위치에 다른 컨트롤러가 있는지 확인
     *
     *  없다면,
     *  해당 위치에 컨트롤러 설치완료 메세지.
     *
     *
     * @param e BLockPlaceEvent
     */
    public void place(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItemInHand();
        Block block = e.getBlockPlaced();


        // 우클릭한 아이템이 컨트롤러인지 확인
        if ( !itemService.has(item) ){
            player.sendMessage(" 이 아이템은 컨트롤러가 아님");
            return;
        }
        String uni = itemService.getUnique(item);
        if( !remoterService.has(uni) ){
            player.sendMessage(" 캐시에 저장되지 않은 정보 ");
            e.setCancelled(true);
            return;
        }

        if ( remoterService.isLocationCache(block.getLocation())){
            player.sendMessage(" 이미 해당 위치에 같은 위치에 등록된 객체가 있습니다. ");
            return;
        }

        remoterService.save(uni,block.getLocation());
        player.sendMessage(" "+block.getLocation()+" 위치에 컨트롤러가 설치되었습니다. ");
    }
}
