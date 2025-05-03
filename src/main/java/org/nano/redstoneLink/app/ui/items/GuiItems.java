package org.nano.redstoneLink.app.ui.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.nano.redstoneLink.infra.util.item.ItemBuilder;

import java.util.List;

public class GuiItems {
    public ItemStack previousButton(){
        return ItemBuilder.builder(Material.OAK_BUTTON)
                .setDisplay("&ePrevious Button")
                .setLore(List.of(
                        "",
                        "&f이전 페이지로 넘어갑니다."
                ))
                .model(1)
                .addEnchant(Enchantment.ARROW_INFINITE,1)
                .build();
    }
    public ItemStack nextButton(){
        return ItemBuilder.builder(Material.OAK_BUTTON)
                .setDisplay("&eNext Button")
                .setLore(List.of(
                        "",
                        "&f다음 페이지로 넘어갑니다."
                ))
                .model(1)
                .addEnchant(Enchantment.ARROW_INFINITE,1)
                .build();
    }
    public ItemStack password(String password){
        return ItemBuilder.builder(Material.IRON_DOOR)
                .setDisplay("&e패스워드 설정")
                .setLore(List.of(
                        "&f:: 설정에 접근할 수 있는 패스워드 입니다.",
                        "&f:: 현재 : "+"*".repeat(password.length())
                ))
                .model(1)
                .addEnchant(Enchantment.ARROW_INFINITE,1)
                .build();
    }
    public ItemStack whitelist(){
        return ItemBuilder.builder(Material.OAK_DOOR)
                .setDisplay("&a화이트 리스트")
                .setLore(List.of(
                        "&f:: 버튼 또는 레버를 사용할 수 있는 권한입니다.",
                        "&f:: 화이트리스트에 추가된다면 사용이 가능합니다."
                ))
                .model(1)
                .addEnchant(Enchantment.ARROW_INFINITE,1)
                .build();
    }
    public ItemStack add(){
        return ItemBuilder.builder(Material.ENDER_CHEST)
                .setDisplay("&a추가하기")
                .setLore(List.of(
                        "&f:: 클릭 후 연결시킬 블럭을 우클릭해주세요.",
                        "&c:: 레드스톤 신호가 작동하는 블럭만 가능합니다.",
                        "&c:: 다른사람이 이미 같은 블럭에 연결했을 경우 해당 블럭은 연결할 수 없습니다."
                ))
                .model(1)
                .addEnchant(Enchantment.ARROW_INFINITE,1)
                .build();
    }
    public ItemStack allController(){
        return ItemBuilder.builder(Material.ENDER_EYE)
                .setDisplay("&eLOCK &7/ &6UNLOCK")
                .setLore(List.of(
                        "&f:: [좌클릭] : 모든 연결된 블럭들은 LOCK 상태로 변경합니다.",
                        "&c:: [우클릭] : 모든 연결된 블럭을 UNLOCK 상태로 변경합니다.",
                        "&7:: LOCK - 연결된 블럭을 작동시켜도 LOCK 상태라면 작동하지 않습니다."
                ))
                .model(1)
                .addEnchant(Enchantment.ARROW_INFINITE,1)
                .build();
    }
    public ItemStack retrieval(){
        return ItemBuilder.builder(Material.BARRIER)
                .setDisplay("&c회수하기")
                .setLore(List.of(
                        "&f:: 클릭하면 해당 컨트롤러를 회수 합니다.",
                        "&c:: 기존에 연결된 모든 블럭의 연결이 끊어집니다."
                ))
                .model(1)
                .addEnchant(Enchantment.ARROW_INFINITE,1)
                .build();
    }
}
