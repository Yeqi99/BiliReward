package yeqi.plugin.bilireward.yml;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import yeqi.plugin.bilireward.BiliReward;
import yeqi.plugin.bilireward.bilibili.abs.BiliUser;
import yeqi.plugin.bilireward.util.Sender;
import yeqi.plugin.bilireward.util.command.Processing;
import yeqi.plugin.bilireward.yml.abs.RewardGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Reward {
    public static List<RewardGroup> rgList = new ArrayList<>();
    public static List<String> rewardName = new ArrayList<>();
    public static List<File> allFileList = new ArrayList<>();
    public static void getAll() {
        File dir = new File(BiliReward.plugin.getDataFolder(), "reward");
        if (!dir.exists()) {
            dir.mkdirs();
            return;
        }
        getAllFile(dir, allFileList);
        if(allFileList.size()==0){
            return;
        }
        new Sender().sendToLogger ("&dBiliReward&7 - 加载了" + allFileList.size() + "个奖励组");
    }

    public static void getAllFile(File fileInput, List<File> allFileList) {
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                getAllFile(file, allFileList);
            } else {
                //收集配置文件数据
                YamlConfiguration ym = YamlConfiguration.loadConfiguration(file);
                String name = ym.getString("Name");
                String display = ym.getString("Display");
                List<String> strCommandList = ym.getStringList("Reward.Commands");
                RewardGroup rg = new RewardGroup();
                rg.name = name;
                rg.display = display;
                rg.fcg = Processing.strCMDListToFormatCMDGroup(name, strCommandList);
                rg.rgYm=ym;
                rgList.add(rg);
                allFileList.add(file);
                rewardName.add(rg.name);
            }
        }
    }

    public static void loadOrReload() {
        new BukkitRunnable() {
            @Override
            public void run() {
                allFileList.clear();
                rewardName.clear();
                rgList.clear();
                getAll();
            }
        }.runTask(BiliReward.plugin);
    }

    public static RewardGroup getRewardGroup(String name) {
        int index = rewardName.indexOf(name);
        if (index >= 0) {
            return rgList.get(index);
        }
        return null;
    }
    public static void getReward(Player player, RewardGroup rg){
        Processing.runFormatCommandGroup(player,rg.fcg);
    }
}
