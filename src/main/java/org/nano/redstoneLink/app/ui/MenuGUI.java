package org.nano.redstoneLink.app.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.app.ui.handler.GUIHandler;
import org.nano.redstoneLink.app.ui.items.GuiItems;
import org.nano.redstoneLink.domain.remoter.Remoter;
import org.nano.redstoneLink.infra.util.component.ColorUtil;

public class MenuGUI extends GUIHandler implements InventoryHolder {
    private final Inventory inventory;
    private final Remoter remoter;

    public MenuGUI(Remoter remoter) {
        this.remoter = remoter;
        this.inventory = Bukkit.createInventory(this,54, ColorUtil.convert("&6회로"));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    protected void setup() {
        GuiItems guiItems = new GuiItems();
        new Thread(()->{
            inventory.setItem(45,guiItems.previousButton());
            inventory.setItem(47,guiItems.password(remoter.getPassword()));
            inventory.setItem(48,guiItems.whitelist());
            inventory.setItem(49,guiItems.add());
            inventory.setItem(50,guiItems.allController());
            inventory.setItem(51,guiItems.retrieval());
            inventory.setItem(53,guiItems.nextButton());
        });
    }

    @Override
    protected void open(Player player) {
        player.openInventory(this.inventory);
    }
}
