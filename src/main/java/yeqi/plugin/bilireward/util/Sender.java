package yeqi.plugin.bilireward.util;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yeqi.plugin.bilireward.BiliReward;

import java.util.List;

import static yeqi.plugin.bilireward.util.Color.toColor;


public class Sender {
    private BiliReward plugin;
    public Sender(){
        plugin=BiliReward.plugin;
    }
    public void sendToLogger(String inStr){
        Bukkit.getConsoleSender().sendMessage(toColor(inStr));
    }
    public void sendToLogger(List<String> inList){
        for (String s : inList) {
            Bukkit.getConsoleSender().sendMessage(toColor(s));
        }
    }
    public void sendToPlayer(Player player, String inStr){
        player.sendMessage(toColor(inStr));
    }
    public void sendToPlayer(Player player, List<String> inList){
        for (String s : inList) {
            player.sendMessage(toColor(s));
        }
    }
    public void sendToPlayerBC(Player player, List<BaseComponent[]> bcList){
        if(bcList==null){
            return;
        }
        for(BaseComponent[] bc:bcList){
            player.spigot().sendMessage(bc);
        }
    }
    public void sendToPlayerBC(Player player, BaseComponent[] bc){
        if(bc==null){
            return;
        }
        player.spigot().sendMessage(bc);
    }
    public void sendToSender(CommandSender sender, String inStr) {
        sender.sendMessage(toColor(inStr));
    }
    public void sendToSender(CommandSender sender, List<String> inList) {
        for (String s : inList) {
            sender.sendMessage(toColor(s));
        }
    }
    public void sendToAllPlayer(List<String> inList){
        for (String s : inList) {
            Bukkit.broadcastMessage(toColor(s));
        }
    }
}
