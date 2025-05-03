package org.nano.redstoneLink.domain.controller;

import org.bukkit.entity.Player;
import org.nano.redstoneLink.domain.service.ItemService;
import org.nano.redstoneLink.domain.service.LinkedService;
import org.nano.redstoneLink.domain.service.RemoterService;

public class Controller {
    private final LinkedService linkedService;
    private final RemoterService remoterService;
    private final ItemService itemService;

    public Controller(LinkedService linkedService,
                      RemoterService remoterService,
                      ItemService itemService) {
        this.linkedService = linkedService;
        this.remoterService = remoterService;
        this.itemService = itemService;
    }

    public void apply(Player player, String unq) {
        if ( remoterService.has(unq) ){
            player.sendMessage(" 이미 존재하는 번호입니다. ");
            return;
        }

        if ( itemService.has(player.getInventory().getItemInMainHand())){
            player.sendMessage(" 이미 컨트롤러로 적용된 아이템 입니다. ");
            return;
        }

        if ( !itemService.materialCheck(player.getInventory().getItemInMainHand())){
            player.sendMessage(" 레버 또는 버튼을 손에 들어주세요. ");
            return;
        }
        remoterService.addRemoter(unq);
        itemService.changeByEquipMainItem(player,unq);

        player.sendMessage(" 적용이 완료되었습니다. ");
    }

}
