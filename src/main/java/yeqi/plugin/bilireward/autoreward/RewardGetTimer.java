package yeqi.plugin.bilireward.autoreward;

import hook.PlaceholderAPIHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import yeqi.plugin.bilireward.BiliReward;
import yeqi.plugin.bilireward.bilibili.abs.PlayerData;
import yeqi.plugin.bilireward.yml.Data;
import yeqi.plugin.bilireward.yml.Video;
import yeqi.plugin.bilireward.yml.abs.VideoInfo;
import yeqi.tools.yeqilib.message.Sender;

import java.util.List;

public class RewardGetTimer {
    static boolean enable;

    public static void run(){
        new BukkitRunnable(){
            @Override
            public void run() {
                enable=BiliReward.plugin.getConfig().getBoolean("auto_reward.enable");
                if(!enable){
                    cancel();
                }
                for (PlayerData pd: Data.pdList){
                    Player player= Bukkit.getPlayer(pd.uuid);
                    if(player==null){
                        continue;
                    }
                    if (!player.isOnline()){
                        continue;
                    }
                    //判断获得奖励
                    List<VideoInfo> viList= Video.videoInfosList;
                    boolean flag=false;
                    //遍历所有视频
                    for(VideoInfo vi:viList){
                        //获取投币数
                        int coin=pd.getCoin(vi.bvid);
                        //判断玩家是否获得过投币奖励
                        boolean coinFlag=false;
                        boolean likeFlag=false;
                        boolean followingFlag=false;
                        //遍历获得过的投币奖励 如果与当前视频bvid重合说明获得过 coinFlag=true
                        for(String s:pd.got_coin_reward){
                            if(s.equals(vi.bvid)){
                                coinFlag=true;
                                break;
                            }
                        }
                        //遍历获得过的点赞奖励 如果与当前视频bvid重合说明获得过 likeFlag=true
                        for(String s:pd.got_like_reward){
                            if(s.equals(vi.bvid)){
                                likeFlag=true;
                                break;
                            }
                        }
                        //遍历获得过的关注奖励 如果与当前视频up_name重合说明获得过 followingFlag=true
                        for(String s:pd.got_followings_reward){
                            if(s.equals(vi.up_name)){
                                followingFlag=true;
                                break;
                            }
                        }
                        //如果没有获得过
                        if(!coinFlag){
                            //获得一个硬币奖励
                            if(coin==1){
                                vi.videoReward.getRewardCoin_1(player);
                                flag=true;
                                //获得两个硬币奖励
                            }else if(coin==2){
                                vi.videoReward.getRewardCoin_2(player);
                                flag=true;
                            }
                            pd.got_coin_reward.add(vi.bvid);
                        }
                        //如果没有获得过
                        if(!likeFlag){
                            if(pd.isLike(vi.bvid)){
                                //获得点赞奖励
                                vi.videoReward.getRewardLike(player);
                                flag=true;
                                pd.got_like_reward.add(vi.bvid);
                            }
                        }
                        //如果没有获得过
                        if(!followingFlag){
                            if(pd.isFollowing(vi.up_name,"uname")){
                                //获得关注奖励
                                vi.videoReward.getRewardLike(player);
                                flag=true;
                                pd.got_followings_reward.add(vi.up_name);
                            }
                        }
                        if(flag){
                            //存入领取信息数据
                            Data.setPlayerData(pd);
                            if(PlaceholderAPIHook.isLoad){
                                String msg=BiliReward.plugin.getConfig().getString("auto_reward.text");
                                new Sender(BiliReward.plugin).sendToPlayer(player,PlaceholderAPIHook.getPlaceholder(player,msg));
                            }else {
                                String msg=BiliReward.plugin.getConfig().getString("auto_reward.text");
                                new Sender(BiliReward.plugin).sendToPlayer(player,msg);
                            }
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(BiliReward.plugin,0,BiliReward.plugin.getConfig().getInt("auto_reward.time")* 20L);
    }
}
