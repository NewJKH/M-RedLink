package org.nano.redstoneLink.app.ui.handler;

import org.bukkit.entity.Player;

public abstract class GUIHandler {
    public void progress(Player player){
        setup();
        open(player);
    }
    protected abstract void setup();
    protected abstract void open(Player player);
}
