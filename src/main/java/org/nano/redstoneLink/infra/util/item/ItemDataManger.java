package org.nano.redstoneLink.infra.util.item;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.nano.redstoneLink.shared.enums.ItemKey;

import java.util.Optional;

public class ItemDataManger {
    private final Plugin plugin;

    public ItemDataManger(Plugin plugin) {
        this.plugin = plugin;
    }

    public ItemMeta setData(ItemMeta meta, ItemKey itemKey, String value) {
        NamespacedKey key = new NamespacedKey(plugin, itemKey.name());
        meta.getPersistentDataContainer().set(key,PersistentDataType.STRING, value);
        return meta;
    }

    public Optional<String> getData(ItemMeta meta, ItemKey itemKey) {
        NamespacedKey key = new NamespacedKey(plugin, itemKey.name());
        return Optional.ofNullable(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
    }

    public boolean hasData(ItemMeta meta, ItemKey itemKey){
        NamespacedKey key = new NamespacedKey(plugin, itemKey.name());
        return meta.getPersistentDataContainer().has(key, PersistentDataType.STRING);
    }

    public ItemMeta removeData(ItemMeta meta, ItemKey itemKey) {
        NamespacedKey key = new NamespacedKey(plugin, itemKey.name());
        meta.getPersistentDataContainer().remove(key);
        return meta;
    }
}
