package yeqi.plugin.bilireward.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yeqi.plugin.bilireward.BiliReward;
import yeqi.plugin.bilireward.autoreward.RewardGetTimer;
import yeqi.plugin.bilireward.bilibili.Controller;
import yeqi.plugin.bilireward.bilibili.abs.PlayerData;
import yeqi.plugin.bilireward.util.Sender;
import yeqi.plugin.bilireward.util.Setting;
import yeqi.plugin.bilireward.yml.Data;
import yeqi.plugin.bilireward.yml.Reward;
import yeqi.plugin.bilireward.yml.Video;
import yeqi.plugin.bilireward.yml.abs.VideoInfo;
import yeqi.plugin.bilireward.yml.abs.VideoReward;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class BiliRewardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Sender sd=new Sender();
        if(args.length==0){
            sd.sendToSender(sender, Setting.find_help);
            return true;
        }
        if(args[0].equalsIgnoreCase("help")){
            sd.sendToSender(sender,Setting.help);
            return true;
        }else if(args[0].equalsIgnoreCase("bind")){
            if(sender instanceof Player){
                Player player=(Player)sender;
                if(!player.hasPermission("bili.bind")){
                    if(!player.isOp()){
                        return true;
                    }
                }
            }
            if(args.length<2){
                sd.sendToSender(sender,Setting.unknow_cmd);
                return true;
            }
            String buid=args[1];
            if(args.length==2){
                Player player=(Player) sender;
                if(Data.isAlreadyBind(buid)){
                    sd.sendToPlayer(player,Setting.already_bind);
                    return true;
                }
                Controller.bind(player,buid,true);
                return true;
            }else if(args.length==3){
                if(sender instanceof Player){
                    Player player=(Player)sender;
                    if(!player.hasPermission("bili.admin")){
                        if(!player.isOp()){
                            return true;
                        }
                    }
                }
                Player player= Bukkit.getPlayer(args[2]);
                if(player==null){
                    sd.sendToSender(sender,Setting.unknow_player);
                    return true;
                }
                if(Data.isAlreadyBind(buid)){
                    sd.sendToPlayer(player,Setting.already_bind);
                }
                Controller.bind(player,buid,true);
                return true;
            }
        }else if(args[0].equalsIgnoreCase("getcoin")){
            if(sender instanceof Player){
                Player player=(Player)sender;
                if(!player.hasPermission("bili.admin")){
                    if(!player.isOp()){
                        return true;
                    }
                }
            }
            if(args.length<2){
                sd.sendToSender(sender,Setting.unknow_cmd);
                return true;
            }
            String bvid=args[1];
            if(args.length==2){
                Player player=(Player) sender;
                Controller.getCoin(player,bvid,true);
                return true;
            }else if(args.length==3){
                Player player= Bukkit.getPlayer(args[2]);
                if(player==null){
                    sd.sendToSender(sender,Setting.unknow_player);
                    return true;
                }
                Controller.getCoin(player,bvid,true);
                return true;
            }
        }else if(args[0].equalsIgnoreCase("islike")){
            if(sender instanceof Player){
                Player player=(Player)sender;
                if(!player.hasPermission("bili.admin")){
                    if(!player.isOp()){
                        return true;
                    }
                }
            }
            if(args.length<2){
                sd.sendToSender(sender,Setting.unknow_cmd);
                return true;
            }
            String bvid= args[1];
            if(args.length==2){
                Player player=(Player) sender;
                Controller.isLike(player,bvid,true);
                return true;
            }else if(args.length==3){
                Player player= Bukkit.getPlayer(args[2]);
                if(player==null){
                    sd.sendToSender(sender,Setting.unknow_player);
                    return true;
                }
                Controller.isLike(player,bvid,true);
                return true;
            }
        }else if(args[0].equalsIgnoreCase("isfollow")){
            if(sender instanceof Player){
                Player player=(Player)sender;
                if(!player.hasPermission("bili.admin")){
                    if(!player.isOp()){
                        return true;
                    }
                }
            }
            if(args.length<2){
                sd.sendToSender(sender,Setting.unknow_cmd);
                return true;
            }
            String mid= args[1];
            if(args.length==2){
                Player player=(Player) sender;
                Controller.isFollowing(player,mid,true,"uname");
                return true;
            }else if(args.length==3){
                Player player= Bukkit.getPlayer(args[2]);
                if(player==null){
                    sd.sendToSender(sender,Setting.unknow_player);
                    return true;
                }
                Controller.isFollowing(player,mid,true,"uname");
                return true;
            }
        }else if(args[0].equalsIgnoreCase("get")){
            if(sender instanceof Player){
                Player player=(Player)sender;
                if(!player.hasPermission("bili.get")){
                    if(!player.isOp()){
                        return true;
                    }
                }
            }
            if(!(sender instanceof Player)){
                sd.sendToLogger("&c这个指令只能由玩家输入");
                return true;
            }
            List<String> coinMsgList=new ArrayList<>();
            coinMsgList.add(Setting.title_get_coin);
            List<String> likeMsgList=new ArrayList<>();
            likeMsgList.add(Setting.title_get_like);
            List<String> followingList=new ArrayList<>();
            followingList.add(Setting.title_get_following);
            if (args.length==1){
                Player player=(Player) sender;
                PlayerData pd=Data.getPlayerData(player);
                if(pd==null){
                    sd.sendToPlayer(player,Setting.none_can_get);
                    return true;
                }
                //判断获得奖励
                List<VideoInfo> viList= Video.videoInfosList;
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
                            coinMsgList.add(Setting.get_coin_1_prefix+vi.display+Setting.get_coin_1_msg);
                        //获得两个硬币奖励
                        }else if(coin==2){
                            vi.videoReward.getRewardCoin_2(player);
                            coinMsgList.add(Setting.get_coin_2_prefix+vi.display+Setting.get_coin_2_msg);
                        }
                        pd.got_coin_reward.add(vi.bvid);
                    }
                    //如果没有获得过
                    if(!likeFlag){
                        if(pd.isLike(vi.bvid)){
                            //获得点赞奖励
                            vi.videoReward.getRewardLike(player);
                            pd.got_like_reward.add(vi.bvid);
                            likeMsgList.add(Setting.get_like_prefix+vi.display+Setting.get_like_msg);
                        }
                    }
                    //如果没有获得过
                    if(!followingFlag){
                        if(pd.isFollowing(vi.up_name,"uname")){
                            //获得关注奖励
                            vi.videoReward.getRewardLike(player);
                            pd.got_followings_reward.add(vi.up_name);
                            followingList.add(Setting.get_following_prefix+vi.up_name+Setting.get_following_msg);
                        }
                    }
                    //存入领取信息数据
                    Data.setPlayerData(pd);
                }
                if(coinMsgList.size()>1){
                    sd.sendToPlayer(player,coinMsgList);
                }
                if(likeMsgList.size()>1){
                    sd.sendToPlayer(player,likeMsgList);
                }
                if(followingList.size()>1){
                    sd.sendToPlayer(player,followingList);
                }
                if(followingList.size()+likeMsgList.size()+coinMsgList.size()==3){
                    sd.sendToPlayer(player,Setting.none_can_get);
                }
                return true;
            }else if(args.length==2){
                Player player=Bukkit.getPlayer(args[1]);
                if(player==null){
                    sd.sendToSender(sender,Setting.unknow_player);
                    return true;
                }
                PlayerData pd=Data.getPlayerData(player);
                if(pd==null){
                    sd.sendToPlayer(player,Setting.none_can_get);
                    return true;
                }
                //判断获得奖励
                List<VideoInfo> viList= Video.videoInfosList;
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
                    //遍历获得过的关注奖励 如果与当前视频bvid重合说明获得过 followingFlag=true
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
                            coinMsgList.add(Setting.get_coin_1_prefix+vi.display+Setting.get_coin_1_msg);
                            //获得两个硬币奖励
                        }else if(coin==2){
                            vi.videoReward.getRewardCoin_2(player);
                            coinMsgList.add(Setting.get_coin_2_prefix+vi.display+Setting.get_coin_2_msg);
                        }
                        pd.got_coin_reward.add(vi.bvid);
                    }
                    //如果没有获得过
                    if(!likeFlag){
                        if(pd.isLike(vi.bvid)){
                            //获得点赞奖励
                            vi.videoReward.getRewardLike(player);
                            pd.got_like_reward.add(vi.bvid);
                            likeMsgList.add(Setting.get_like_prefix+vi.display+Setting.get_like_msg);
                        }
                    }
                    //如果没有获得过
                    if(!followingFlag){
                        if(pd.isFollowing(vi.up_name,"uname")){
                            //获得关注奖励
                            vi.videoReward.getRewardLike(player);
                            pd.got_followings_reward.add(vi.up_name);
                            followingList.add(Setting.get_following_prefix+vi.up_name+Setting.get_following_msg);
                        }
                    }
                    //存入领取信息数据
                    Data.setPlayerData(pd);
                }
                if(coinMsgList.size()>1){
                    sd.sendToPlayer(player,coinMsgList);
                }
                if(likeMsgList.size()>1){
                    sd.sendToPlayer(player,likeMsgList);
                }
                if(followingList.size()>1){
                    sd.sendToPlayer(player,followingList);
                }
                if(followingList.size()+likeMsgList.size()+coinMsgList.size()==3){
                    sd.sendToPlayer(player,Setting.none_can_get);
                }
                return true;
            }else {
                sd.sendToSender(sender,Setting.unknow_cmd);
                return true;
            }
        }else if(args[0].equalsIgnoreCase("reload")){
            if(sender instanceof Player){
                Player player=(Player)sender;
                if(!player.hasPermission("bili.admin")){
                    if(!player.isOp()){
                        return true;
                    }
                }
            }
            if(args.length!=1){
                sd.sendToSender(sender,Setting.unknow_cmd);
                return true;
            }
            Setting.loadLang();
            Reward.loadOrReload();
            Data.loadOrReload();
            Video.loadOrReload();
            sd.sendToSender(sender,"&a重载成功");
        } else{
            sd.sendToSender(sender,Setting.unknow_cmd);
            return true;
        }
        return true;
    }
}
