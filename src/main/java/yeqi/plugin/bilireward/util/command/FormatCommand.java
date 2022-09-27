package yeqi.plugin.bilireward.util.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        command=Processing.getVerCommand(command,player);
        //分不同动作执行指令
        switch (action) {
            //玩家自身执行
            case SELF: {
                player.performCommand(command);
                return true;
            }
            //控制台运行
            case CONSOLE: {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                return true;
            }
            //无视权限运行
            case ADMIN: {
                if(player.isOp()){
                    player.performCommand(command);
                }else{
                    player.setOp(true);
                    try {
                        player.performCommand(command);
                    } finally {
                        player.setOp(false);
                    }
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
