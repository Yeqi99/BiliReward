package yeqi.plugin.bilireward.bilibili;

import org.bukkit.entity.Player;
import yeqi.plugin.bilireward.bilibili.abs.PlayerData;
import yeqi.plugin.bilireward.util.Sender;
import yeqi.plugin.bilireward.util.Setting;
import yeqi.plugin.bilireward.yml.Data;

public class Controller {
    public static Sender sd=new Sender();
    public static boolean bind(Player player,String buid,boolean isCommand){
        boolean flag=Data.newPlayer(player,buid);
        if(!isCommand){
            return flag;
        }
        if(flag){
            sd.sendToPlayer(player, Setting.bind_success);
        }else {
            sd.sendToPlayer(player,Setting.bind_fail);
        }
        return flag;
    }
    public static int getCoin(Player player,String bvid,boolean isCommand){
        boolean flag= Data.hasPlayer(player);
        if(!isCommand){
            if(!flag){
                return 0;
            }
        }
        if(!flag){
            sd.sendToPlayer(player,Setting.bind_no);
            return 0;
        }
        PlayerData pd= Data.getPlayerData(player);
        int coin=pd.getCoin(bvid);
        if(!isCommand){
            return coin;
        }
        if(coin==0){
            sd.sendToPlayer(player,"你没有给这个视频投币");
        }else {
            sd.sendToPlayer(player,"给视频投了"+pd.getCoin(bvid)+"个硬币");
        }
        return coin;
    }
    public static boolean isLike(Player player,String bvid,boolean isCommand){
        boolean flag= Data.hasPlayer(player);
        if(!isCommand){
            if(!flag){
                return false;
            }
        }
        if(!flag){
            sd.sendToPlayer(player,Setting.bind_no);
            return false;
        }
        PlayerData pd= Data.getPlayerData(player);
        boolean isLike=pd.isLike(bvid);
        if(!isCommand){
            return isLike;
        }
        if(isLike){
            sd.sendToPlayer(player,"你给这个视频点赞了");
            return true;
        }else{
            sd.sendToPlayer(player,"你没给这个视频点赞");
            return false;
        }
    }
    public static boolean isFollowing(Player player,String value,boolean isCommand,String type){
        boolean flag= Data.hasPlayer(player);
        if(!isCommand){
            if(!flag){
                return false;
            }
        }
        if(!flag){
            sd.sendToPlayer(player,Setting.bind_no);
            return false;
        }
        PlayerData pd= Data.getPlayerData(player);
        boolean isFollowing=pd.isFollowing(value,type);
        if (!isCommand){
            return isFollowing;
        }
        if(isFollowing){
            sd.sendToPlayer(player,"你已经关注了这个用户");
        }else{
            sd.sendToPlayer(player,"你没有关注这个用户");
        }
        return isFollowing;
    }
}
