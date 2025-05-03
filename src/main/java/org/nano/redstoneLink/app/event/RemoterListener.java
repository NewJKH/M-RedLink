package org.nano.redstoneLink.app.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.nano.redstoneLink.app.ui.MenuGUI;
import org.nano.redstoneLink.domain.controller.EventController;
import org.nano.redstoneLink.shared.enums.ControllerType;

public class RemoterListener implements Listener {
    private final EventController eventController;

    public RemoterListener(EventController eventController) {
        this.eventController = eventController;
    }

    @EventHandler
    public void place(BlockPlaceEvent e){
        eventController.place(e);
    }


    @EventHandler
    public void use(PlayerInteractEvent e){
        if ( e.getClickedBlock() == null ) return;
        if (!ControllerType.check(e.getClickedBlock().getType())) {
            return;
        }

        e.setUseInteractedBlock(Event.Result.DENY);
        e.setUseItemInHand(Event.Result.DENY);

        if ( !e.getPlayer().isSneaking() && e.getAction() == Action.RIGHT_CLICK_BLOCK ) {
            eventController.useRemoter(e);
        } else if (e.getPlayer().isSneaking() && e.getAction() == Action.RIGHT_CLICK_BLOCK ) {
            eventController.openMenu(e);
        }
    }
    @EventHandler
    public void clickInventory(InventoryClickEvent e){
        if ( e.getInventory().getHolder() instanceof MenuGUI gui){
            eventController.clickGui(e,gui);
        }
    }
}
