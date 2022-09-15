package yeqi.plugin.bilireward.yml;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import yeqi.plugin.bilireward.BiliReward;
import yeqi.plugin.bilireward.bilibili.abs.PlayerData;
import yeqi.plugin.bilireward.util.Sender;
import yeqi.plugin.bilireward.yml.abs.VideoInfo;
import yeqi.plugin.bilireward.yml.abs.VideoReward;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static yeqi.plugin.bilireward.BiliReward.plugin;

public class Video {
    public static Sender sd=new Sender();
    public static List<VideoInfo> videoInfosList=new ArrayList<>();
    public static List<File> allFileList = new ArrayList<>();
    public static void getAll() {
        File dir = new File(plugin.getDataFolder(), "video");
        if (!dir.exists()) {
            dir.mkdirs();
            return;
        }
        getAllFile(dir, allFileList);
        if(allFileList.size()==0){
            return;
        }
        sd.sendToLogger("&dBiliReward&7 - &7加载了"+allFileList.size()+"个视频信息");
    }
    public static void getAllFile(File fileInput, List<File> allFileList) {
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                getAllFile(file, allFileList);
            } else {
                YamlConfiguration ym = YamlConfiguration.loadConfiguration(file);
                VideoInfo vi=new VideoInfo();
                VideoReward vr=new VideoReward();
                vi.display=ym.getString("display");
                vi.bvid=ym.getString("bvid");
                vi.author=ym.getString("author");
                vi.up_name=ym.getString("up_name");
                vr.reward_coin_1=ym.getStringList("reward.coin.1");
                vr.reward_coin_2=ym.getStringList("reward.coin.2");
                vr.reward_like=ym.getStringList("reward.like");
                vr.reward_following=ym.getStringList("reward.following");
                vi.videoReward=vr;
                vi.viYm=ym;
                videoInfosList.add(vi);
                allFileList.add(file);
            }
        }
    }
    public static void loadOrReload() {
        new BukkitRunnable() {
            @Override
            public void run() {
                allFileList.clear();
                videoInfosList.clear();
                getAll();
            }
        }.runTask(BiliReward.plugin);
    }

}
