package yeqi.plugin.bilireward;

import org.bukkit.plugin.java.JavaPlugin;
import yeqi.plugin.bilireward.bilibili.DataGet;
import yeqi.plugin.bilireward.commands.BiliRewardCommand;
import yeqi.plugin.bilireward.util.Sender;
import yeqi.plugin.bilireward.util.Setting;
import yeqi.plugin.bilireward.yml.Data;
import yeqi.plugin.bilireward.yml.Reward;
import yeqi.plugin.bilireward.yml.Video;




public final class BiliReward extends JavaPlugin {
    public static BiliReward plugin;
    public static Sender sd=new Sender();
    @Override
    public void onEnable() {
        plugin=this;
        saveDefaultConfig();
        regCommand();
        loadYml();
        sd.sendToLogger("");
        sd.sendToLogger("    &bBiliReward  &fv1.0.0-Free  &7Successfully loaded");
        sd.sendToLogger("                   &7Made by CnYeqi");
        sd.sendToLogger("");
    }

    @Override
    public void onDisable() {
        sd.sendToLogger("");
        sd.sendToLogger("    &bBiliReward  &fv1.0.0-Free  &7Successfully unloaded");
        sd.sendToLogger("                   &7Made by CnYeqi");
        sd.sendToLogger("");
    }
    public void loadYml(){
        Setting.loadLang();
        Reward.loadOrReload();
        Data.loadOrReload();
        Video.loadOrReload();
    }
    public void regCommand(){
        if(getServer().getPluginCommand("bilireward")!=null){
            getServer().getPluginCommand("bilireward").setExecutor(new BiliRewardCommand());
        }
    }
}
