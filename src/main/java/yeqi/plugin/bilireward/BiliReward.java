package yeqi.plugin.bilireward;

import org.bukkit.plugin.java.JavaPlugin;
import yeqi.plugin.bilireward.autoreward.RewardGetTimer;
import yeqi.plugin.bilireward.commands.BiliRewardCommand;
import yeqi.plugin.bilireward.util.Setting;
import yeqi.plugin.bilireward.yml.Data;
import yeqi.plugin.bilireward.yml.Reward;
import yeqi.plugin.bilireward.yml.Video;
import yeqi.tools.yeqilib.message.Sender;


public final class BiliReward extends JavaPlugin {
    public static BiliReward plugin;
    public static Sender sd;
    @Override
    public void onEnable() {
        plugin=this;
        sd=new Sender(plugin);
        saveDefaultConfig();
        regCommand();
        loadYml();
        RewardGetTimer.run();
        sd.sendOnEnableMsgToLogger("BiliReward","Yeqi");
    }

    @Override
    public void onDisable() {
        sd.sendOnDisableMsgToLogger("BiliReward","Yeqi");
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
