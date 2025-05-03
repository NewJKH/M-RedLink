package org.nano.redstoneLink.domain.service;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.infra.util.item.ItemDataManger;
import org.nano.redstoneLink.shared.enums.ControllerType;
import org.nano.redstoneLink.shared.enums.ItemKey;

public class ItemService {
    private final ItemDataManger itemDataManger;
    public ItemService(Plugin plugin) {
        this.itemDataManger = new ItemDataManger(plugin);
    }

    public boolean has(@NotNull ItemStack itemInMainHand) {
        return itemDataManger.hasData(itemInMainHand.getItemMeta(), ItemKey.SEQUENCE);
    }

    public boolean materialCheck(@NotNull ItemStack itemInMainHand){
        return ControllerType.check(itemInMainHand.getType());
    }

    public void changeByEquipMainItem(Player player,String unique){
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta meta = itemDataManger.setData(itemStack.getItemMeta(),ItemKey.SEQUENCE, unique);
        itemStack.setItemMeta(meta);
        player.getInventory().setItemInMainHand(itemStack);
    }

    public void updateLocation(Player player, Location loc) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta meta = itemDataManger.setData(itemStack.getItemMeta(),ItemKey.LOCATION, loc.toString());
        itemStack.setItemMeta(meta);
        player.getInventory().setItemInMainHand(itemStack);
    }

    public String getUnique(ItemStack item) {
        return itemDataManger.getData(item.getItemMeta(),ItemKey.SEQUENCE)
                .orElse("");
    }
}
