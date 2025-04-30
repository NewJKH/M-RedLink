package org.nano.redstoneLink.app.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.nano.redstoneLink.domain.controller.EventController;

public class RemoterListener implements Listener {
    private final EventController eventController;

    public RemoterListener(EventController eventController) {
        this.eventController = eventController;
    }

    @EventHandler
    public void place(BlockPlaceEvent e){
        eventController.place(e);
    }
}
