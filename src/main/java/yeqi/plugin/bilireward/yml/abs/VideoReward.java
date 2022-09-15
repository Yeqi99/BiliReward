package yeqi.plugin.bilireward.yml.abs;

import org.bukkit.entity.Player;
import yeqi.plugin.bilireward.yml.Reward;

import java.util.ArrayList;
import java.util.List;

public class VideoReward {
    public List<String> reward_coin_1=new ArrayList<>();
    public List<String> reward_coin_2=new ArrayList<>();
    public List<String> reward_like=new ArrayList<>();
    public List<String> reward_following=new ArrayList<>();
    public boolean getRewardCoin_1(Player player){
        if(player==null){
            return false;
        }
        int i=0;
        for(String s:reward_coin_1){
            RewardGroup rg=Reward.getRewardGroup(s);
            if(rg==null){
                continue;
            }
            Reward.getReward(player,rg);
            i++;
        }
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean getRewardCoin_2(Player player){
        if(player==null){
            return false;
        }
        int i=0;
        for(String s:reward_coin_2){
            RewardGroup rg=Reward.getRewardGroup(s);
            if(rg==null){
                continue;
            }
            Reward.getReward(player,rg);
            i++;
        }
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean getRewardLike(Player player){
        if(player==null){
            return false;
        }
        int i=0;
        for(String s:reward_like){
            RewardGroup rg=Reward.getRewardGroup(s);
            if(rg==null){
                continue;
            }
            Reward.getReward(player,rg);
            i++;
        }
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
    public boolean getRewardFollowing(Player player){
        if(player==null){
            return false;
        }
        int i=0;
        for(String s:reward_following){
            RewardGroup rg=Reward.getRewardGroup(s);
            if(rg==null){
                continue;
            }
            Reward.getReward(player,rg);
            i++;
        }
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
}
