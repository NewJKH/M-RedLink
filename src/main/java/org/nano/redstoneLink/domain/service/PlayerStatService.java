package org.nano.redstoneLink.domain.service;

import org.bukkit.entity.Player;

public class PlayerStatService {
    public void consume(Player player){
        player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
    }
}
