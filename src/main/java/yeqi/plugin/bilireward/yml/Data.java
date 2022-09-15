package yeqi.plugin.bilireward.yml;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import yeqi.plugin.bilireward.bilibili.abs.PlayerData;
import yeqi.plugin.bilireward.util.Sender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static yeqi.plugin.bilireward.BiliReward.plugin;

public class Data {
    public static Sender sd=new Sender();
    public static List<PlayerData> pdList=new ArrayList<>();
    public static List<File> allFileList = new ArrayList<>();
    public static void getAll() {
        File dir = new File(plugin.getDataFolder(), "data");
        if (!dir.exists()) {
            dir.mkdirs();
            return;
        }
        getAllFile(dir, allFileList);
        if(allFileList.size()==0){
            return;
        }
        sd.sendToLogger("&dBiliReward&7 - &7加载了"+allFileList.size()+"个玩家记录");
    }
    public static void getAllFile(File fileInput, List<File> allFileList) {
        File[] fileList = fileInput.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                getAllFile(file, allFileList);
            } else {
                YamlConfiguration ym = YamlConfiguration.loadConfiguration(file);
                UUID uuid=UUID.fromString(file.getName().replace(".yml", ""));
                //获取玩家uuid和bilibili uuid
                PlayerData pd=new PlayerData(uuid,ym.getString("buid"));
                pd.got_coin_reward=ym.getStringList("got_coin_reward");
                pd.got_like_reward=ym.getStringList("got_like_reward");
                pd.got_followings_reward=ym.getStringList("got_followings_reward");
                pd.dataYm=ym;
                pdList.add(pd);
                allFileList.add(file);
            }
        }
    }
    public static void loadOrReload() {
        new BukkitRunnable() {
            @Override
            public void run() {
                pdList.clear();
                allFileList.clear();
                getAll();
            }
        }.runTask(plugin);
    }
    public static boolean newPlayer(Player player,String buid){

        if(player==null){
            return false;
        }
        if(hasPlayer(player)){
            return false;
        }
        PlayerData pd=new PlayerData(player.getUniqueId(),buid);
        //判断buid可用性
        String msg=pd.isTrueBuid();
        if(!msg.equals("0")){
            sd.sendToPlayer(player,msg);
            return false;
        }
        pd.savePlayerData();
        pdList.add(pd);
        return true;
    }
    public static PlayerData getPlayerData(Player player){
        for(PlayerData pd:pdList){
            if(pd.uuid.equals(player.getUniqueId())){
                return pd;
            }
        }
        return null;
    }
    public static boolean hasPlayer(Player player){
        for(PlayerData pd:pdList){
            if(pd.uuid.equals(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }
    public static boolean setPlayerData(PlayerData pd){
        for(PlayerData pld:pdList){
            if(pld.uuid.equals(pd.uuid)){
                pld.got_coin_reward=pd.got_coin_reward;
                pld.got_followings_reward=pd.got_followings_reward;
                pld.got_like_reward=pd.got_like_reward;
                pld.buid=pd.buid;
                pld.biliUserList=pd.biliUserList;
                pld.coinInfoList=pd.coinInfoList;
                pld.likeInfoList=pd.likeInfoList;
                pld.dataYm=pd.dataYm;
                pd.savePlayerData();
                return true;
            }
        }
        return false;
    }
    public static boolean isAlreadyBind(String buid){
        for(PlayerData pd:pdList){
            if(pd.buid.equals(buid)){
                return true;
            }
        }
        return false;
    }
}
