package org.nano.redstoneLink.app.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nano.redstoneLink.domain.controller.Controller;

public class Command implements CommandExecutor {
    private final Controller controller;

    public Command(Controller controller) {
        this.controller = controller;
    }
    /*
           /rs apply <uniqueInt> - 손에 들고 있는 아이템을 레버 또는 버튼으로 지정 ( 반드시 레버 또는 버튼 이여야함 )
            /rs set <uniqueInt> max <ea>
            /rs set <uniqueInt> pw <string>

            /rs list <page> / 페이지당 20개
            /rs tp <uniqueInt>
     */

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player player) || !player.isOp()) {
            commandSender.sendMessage("관리자만 사용 가능합니다.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("명령어를 입력해주세요.");
            return false;
        }

        try {
            switch (args[0].toLowerCase()) {
                case "apply" -> {
                    if (args.length != 2) return false;
                    apply(player, args[1]);
                }
                case "set" -> {
                    if (args.length != 4) return false;
                    String unique = args[1];
                    switch (args[2].toLowerCase()) {
                        case "max" -> setMaxEA(player, unique, args[3]);
                        case "pw" -> setPassword(player, unique, args[3]);
                        default -> player.sendMessage("지원하지 않는 set 하위 명령입니다.");
                    }
                }
                case "list" -> list(player);
                case "tp" -> {
                    if (args.length != 2) return false;
                    tp(player, args[1]);
                }
                default -> player.sendMessage("알 수 없는 명령입니다.");
            }
        } catch (Exception e) {
            player.sendMessage("명령어 실행 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return true;
    }
    private void apply(Player player, String unique){
        controller.apply(player,unique);
    }

    private void setMaxEA(Player player, String unique, String strEA){

    }
    private void setPassword(Player player, String unique, String password){

    }
    private void list(Player player){

    }
    private void tp(Player player, String unique){

    }
}
