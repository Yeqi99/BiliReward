package yeqi.plugin.bilireward.util.command;

import hook.PlaceholderAPIHook;
import hook.PlayerPointsHook;
import hook.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import yeqi.plugin.bilireward.BiliReward;
import yeqi.tools.yeqilib.YeqiLib;
import yeqi.tools.yeqilib.message.Sender;

public class FormatCommand {
    public CommandAction action;
    public String command;
    public Player player;
    public String strCommand;

    public FormatCommand(String strCommand) {
        this.strCommand=strCommand;
        action = getCommandAction(strCommand);
        command = getCommand(strCommand);
    }

    public boolean runCommand() {
        action = getCommandAction(strCommand);
        command = getCommand(strCommand);
        //基础判空
        if (player == null | command == null) {
            return false;
        }
        if (PlaceholderAPIHook.isLoad){
            command=PlaceholderAPIHook.getPlaceholder(player,command);
        }
        command=Processing.getVerCommand(command,player);
        //分不同动作执行指令
        switch (action) {
            //玩家自身执行
            case SELF: {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(BiliReward.plugin, () -> player.performCommand(command));
                    }
                }.runTaskAsynchronously(BiliReward.plugin);
                return true;
            }
            //控制台运行
            case CONSOLE: {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getScheduler().runTask(BiliReward.plugin, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
                    }
                }.runTaskAsynchronously(BiliReward.plugin);
                return true;
            }
            //无视权限运行
            case ADMIN: {
                if(player.isOp()){
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Bukkit.getScheduler().runTask(BiliReward.plugin, () -> player.performCommand(command));
                        }
                    }.runTaskAsynchronously(BiliReward.plugin);
                }else{
                    try {
                        player.setOp(true);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Bukkit.getScheduler().runTask(BiliReward.plugin, () -> player.performCommand(command));
                            }
                        }.runTaskAsynchronously(BiliReward.plugin);
                    } finally {
                        player.setOp(false);
                    }
                }
                return true;
            }
            //对玩家发送消息
            case TELL: {
                new Sender(BiliReward.plugin).sendToPlayer(player,command);
                return true;
            }
            //对所有玩家发送消息
            case TELLALL: {
                new Sender(BiliReward.plugin).sendToAllPlayer(command);
                return true;
            }
            //奖励点券
            case POINTS: {
                if (PlayerPointsHook.isLoad){
                    PlayerPointsHook.givePoints(player.getUniqueId(), Integer.parseInt(command));
                }
                return true;
            }
            //金币点券
            case MONEY: {
                if (VaultHook.isLoad){
                    VaultHook.giveMoney(player, Double.parseDouble(command));
                }
                return true;
            }
            //未被定义过的类型
            case UNDEFINED: {
                return false;
            }
        }
        return true;
    }

    public CommandAction getCommandAction(String strCommand) {
        String strAction = "";
        for (char c : strCommand.toCharArray()) {
            if (c == '[') {
                continue;
            }
            if (c == ']') {
                break;
            }
            strAction += c;
        }
        return CommandAction.fromString(strAction);
    }

    public String getCommand(String strCommand) {
        boolean flag = false;
        String returnCommand = "";
        for (char c : strCommand.toCharArray()) {
            if (c == ']' & !flag) {
                flag = true;
                continue;
            }
            if (flag) {
                returnCommand += c;
            }
        }
        return returnCommand;
    }
}
