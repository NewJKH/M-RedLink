package org.nano.redstoneLink.domain.controller;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.nano.redstoneLink.app.ui.MenuGUI;
import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.domain.service.ItemService;
import org.nano.redstoneLink.domain.service.LinkedService;
import org.nano.redstoneLink.domain.service.PlayerStatService;
import org.nano.redstoneLink.domain.service.RemoterService;

import java.util.Objects;

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
     *
     * 아이템에 있는 Unique 번호만 인식 ( 이미 등록된 객체라면 설치 금지 )
     *
     * @param e BLockPlaceEvent
     */
    public void place(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItemInHand();
        Location loc = e.getBlock().getLocation();

        if ( !itemService.has(item) ) {
            return;
        }

        String uni = itemService.getUnique(item);
        if ( remoterService.has(uni)){
            player.sendMessage(" 이미 설치된 객체입니다.");
            e.setCancelled(true);
            return;
        }

        if ( remoterService.isLocationCache(loc)){
            player.sendMessage(" 이미 해당 위치에 컨트롤러가 존재합니다. ");
            e.setCancelled(true);
            return;
        }


        remoterService.save(player, uni, loc);
        player.sendMessage(" 저장되었습니다 "+loc);
    }

    public void useRemoter(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Location loc = Objects.requireNonNull(e.getClickedBlock()).getLocation();

        if ( !remoterService.isLocationCache(loc) ){
            return;
        }
        if ( remoterService.useRemoter(player,loc) ) {
            e.setUseInteractedBlock(Event.Result.ALLOW);
        }else {
            e.setUseInteractedBlock(Event.Result.DENY);
            e.setUseItemInHand(Event.Result.DENY);
        }
    }

    public void openMenu(PlayerInteractEvent e) {
        e.setUseInteractedBlock(Event.Result.DENY);
        e.setUseItemInHand(Event.Result.DENY);

        Player player = e.getPlayer();
        Location loc = Objects.requireNonNull(e.getClickedBlock()).getLocation();

        if ( !remoterService.isLocationCache(loc) ){
            return;
        }
        Remoter remoter = remoterService.getRemoterByLocation(loc);
        remoterService.openRemoter(player,remoter);
    }

    public void clickGui(InventoryClickEvent e, MenuGUI gui) {
        try {
            int slot = e.getRawSlot();
            Player player = (Player) e.getWhoClicked();
            ItemStack item = e.getCurrentItem();

            switch (slot){
                case 45 ->{

                }
                case 47 ->{

                }
                case 48 ->{

                }
                case 49 ->{
                    linkedService.prepareLinking(player,gui.getRemoter());
                    player.closeInventory();
                    player.sendMessage(" 링크할 블럭을 우클릭 해주세요. ");
                }
                case 50 ->{

                }
                case 51 ->{

                }
                case 53 ->{

                }

            }


        }catch (Exception e2){
            e2.printStackTrace();
        }finally {
            e.setCancelled(true);
        }
    }

    public void tryLink(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        if ( linkedService.succeedLinking(player,block) ){

        }
    }
}
