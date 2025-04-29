package org.nano.redstoneLink.infra.util.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.nano.redstoneLink.infra.util.component.ColorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta meta;

    public ItemBuilder() {}

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.meta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack){
        this.itemStack = new ItemStack(itemStack);
        this.meta = itemStack.getItemMeta();
    }

    public static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public ItemBuilder setDisplay(String display){
        Component name = ColorUtil.convert(display);
        meta.displayName(name);
        return this;
    }

    public ItemBuilder setLore(List<String> lore){
        List<Component> components = ColorUtil.convert(lore);
        meta.lore(components);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchantment, int lv){
        meta.addEnchant(enchantment,lv,true);
        return this;
    }

    public ItemBuilder addLore(String line) {
        List<Component> lore = Optional.ofNullable(meta.lore())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
        lore.add(ColorUtil.convert(line));
        meta.lore(lore);
        return this;
    }

    public ItemBuilder model(int customModelData){
        meta.setCustomModelData(customModelData);
        return this;
    }
    public ItemStack build(){
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
