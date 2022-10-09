package yeqi.plugin.bilireward.util.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import yeqi.plugin.bilireward.BiliReward;
import yeqi.plugin.bilireward.yml.Reward;
import yeqi.plugin.bilireward.yml.abs.RewardGroup;
import yeqi.tools.yeqilib.YeqiLib;

import java.util.ArrayList;
import java.util.List;

public class Processing {
    public static void runFormatCommandGroup(Player player, FormatCommandGroup fcg) {
        List<FormatCommand> fcList = fcg.fcList;
        for (FormatCommand fc : fcList) {
            fc.player = player;
            fc.runCommand();
        }
    }

    public static FormatCommandGroup strCMDListToFormatCMDGroup(String name, List<String> strCommandList) {
        List<FormatCommand> fcList = new ArrayList<>();
        if (strCommandList.size() <= 0) {
            return null;
        }
        for (String str : strCommandList) {
            FormatCommand fc = new FormatCommand(str);
            fcList.add(fc);
        }
        return new FormatCommandGroup(name, fcList);
    }
    public static boolean run(String name,Player player){
        RewardGroup rg= Reward.getRewardGroup(name);
        if(rg==null){
            return false;
        }
        Processing.runFormatCommandGroup(player,rg.fcg);
        return true;
    }
    public static String getVerCommand(String str,Player player){
        String returnStr="";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '*') {
                returnStr += c;
            } else {
                if (str.length() <= i + 1) {
                    continue;
                }
                if (str.charAt(i + 1) == '*') {
                    returnStr += '*';
                    i++;
                } else {
                    String funName = "";
                    int nextSignIndex = i + 1;
                    char funNameChar = str.charAt(nextSignIndex);
                    while (funNameChar != '*') {
                        funName += funNameChar;
                        nextSignIndex++;
                        funNameChar = str.charAt(nextSignIndex);
                    }
                    if(funName.equalsIgnoreCase("player")){
                        returnStr += player.getDisplayName();
                    }
                    i = nextSignIndex;
                }
            }
        }
        return returnStr;
    }
}
