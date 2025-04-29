package org.nano.redstoneLink.app.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.app.ui.handler.GUIHandler;
import org.nano.redstoneLink.infra.util.component.ColorUtil;

public class MenuGUI extends GUIHandler implements InventoryHolder {
    private final Inventory inventory;

    public MenuGUI() {
        inventory = Bukkit.createInventory(this,54, ColorUtil.convert("&6회로"));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    @Override
    protected void setup() {
        // 아이템 넣는 로직 작성 그전에... 일단
    }

    @Override
    protected void open(Player player) {
        player.openInventory(this.inventory);
    }
}
