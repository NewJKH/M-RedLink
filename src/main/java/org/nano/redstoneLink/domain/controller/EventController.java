package org.nano.redstoneLink.domain.controller;

import org.bukkit.event.block.BlockPlaceEvent;
import org.nano.redstoneLink.domain.service.ItemService;
import org.nano.redstoneLink.domain.service.LinkedService;
import org.nano.redstoneLink.domain.service.RemoterService;

public class EventController {
    private final LinkedService linkedService;
    private final RemoterService remoterService;
    private final ItemService itemService;

    public EventController(LinkedService linkedService,
                      RemoterService remoterService,
                      ItemService itemService) {
        this.linkedService = linkedService;
        this.remoterService = remoterService;
        this.itemService = itemService;
    }

    public void place(BlockPlaceEvent e) {
        if ( itemService.has(e.getItemInHand()) ){
            e.setCancelled(true);
        }
    }
}
