package yeqi.plugin.bilireward.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Color {
    /**
     * 字符串转为带颜色的消息格式
     * @param instr 带&颜色格式的字符串
     * @return 游戏内可显示颜色的字符串
     */
    public static String toColor(String instr){
        return ChatColor.translateAlternateColorCodes('&',instr);
    }

    /**
     *字符串列表转为带颜色的消息格式
     * @param inList 带&颜色格式的字符串列表
     * @return 游戏内可显示颜色的字符串列表
     */
    public static List<String> toColor(List<String> inList){
        List<String> list = new ArrayList<>();
        for (String str : inList) {
            list.add(ChatColor.translateAlternateColorCodes('&', str));
        }
        return list;
    }
}
