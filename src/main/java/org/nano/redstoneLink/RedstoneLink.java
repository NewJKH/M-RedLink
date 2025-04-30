package org.nano.redstoneLink;

import org.bukkit.plugin.java.JavaPlugin;
import org.nano.redstoneLink.app.command.Command;
import org.nano.redstoneLink.app.event.RemoterListener;
import org.nano.redstoneLink.domain.controller.Controller;
import org.nano.redstoneLink.domain.controller.EventController;
import org.nano.redstoneLink.domain.service.ItemService;
import org.nano.redstoneLink.domain.service.LinkedService;
import org.nano.redstoneLink.domain.service.RemoterService;

import java.util.Objects;

public final class RedstoneLink extends JavaPlugin {

    @Override
    public void onEnable() {
        final LinkedService linkedService = new LinkedService();
        final RemoterService remoterService = new RemoterService();
        final ItemService itemService = new ItemService(this);

        Controller controller = new Controller(linkedService,remoterService,itemService);
        EventController eventController = new EventController(linkedService,remoterService,itemService);


        Objects.requireNonNull(getCommand("rs")).setExecutor(new Command(controller));
        getServer().getPluginManager().registerEvents(new RemoterListener(eventController),this);

    }

    @Override
    public void onDisable() {


    }
}
