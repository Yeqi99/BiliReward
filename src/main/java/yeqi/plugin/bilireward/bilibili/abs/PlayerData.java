package yeqi.plugin.bilireward.bilibili.abs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.plugin.bilireward.bilibili.DataGet;
import yeqi.plugin.bilireward.util.Sender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static yeqi.plugin.bilireward.BiliReward.plugin;

//玩家数据类
public class PlayerData {
    public  YamlConfiguration dataYm = new YamlConfiguration();
    public List<String> got_coin_reward=new ArrayList<>();
    public List<String> got_like_reward=new ArrayList<>();
    public List<String> got_followings_reward=new ArrayList<>();
    //玩家
    public UUID uuid;
    //bilibili uid
    public String buid;
    //投币信息
    public List<CoinInfo> coinInfoList=new ArrayList<>();
    public List<LikeInfo> likeInfoList=new ArrayList<>();
    public List<BiliUser> biliUserList=new ArrayList<>();
    public PlayerData( String buid){
        this.buid=buid;
        getCoinInfo();
        getLikeInfo();
        getBiliUser();
    }
    public PlayerData(UUID uuid, String buid){
        this.uuid=uuid;
        this.buid=buid;
        getCoinInfo();
        getLikeInfo();
        getBiliUser();
    }
    public void getCoinInfo(){
        coinInfoList.clear();
        //获取玩家最近投币数据
        JSONObject jsonObject= DataGet.getPlayerCoinInfo(buid);
        //将每个视频分割存入列表
        List<JSONObject> jsonObjectList= (List<JSONObject>) jsonObject.get("data");
        if(jsonObjectList==null){
            return;
        }
        //遍历视频 存入数据
        for(JSONObject json:jsonObjectList){
            CoinInfo coinInfo=new CoinInfo();
            coinInfo.jsonToThis(json);
            coinInfoList.add(coinInfo);
        }
    }
    public void getLikeInfo(){
        likeInfoList.clear();
        //获取玩家最近点赞数据
        JSONObject jsonObject= DataGet.getPlayerLikeInfo(buid);
        //将每个视频分割存入列表
        JSONObject jsonObjectArr=  jsonObject.getJSONObject("data");
        if(jsonObjectArr==null){
            return;
        }
        JSONArray jsonArr=jsonObjectArr.getJSONArray("list");
        //遍历视频 存入数据
        for(Object json:jsonArr){
            LikeInfo likeInfo=new LikeInfo();
            likeInfo.jsonToThis((JSONObject) json);
            likeInfoList.add(likeInfo);
        }
    }
    public void getBiliUser(){
        biliUserList.clear();
        //获取玩家最近点赞数据
        JSONObject jsonObject= DataGet.getPlayerFollowingsInfo(buid);
        //将每个视频分割存入列表
        JSONObject jsonObjectArr=  jsonObject.getJSONObject("data");
        if(jsonObjectArr==null){
            return;
        }
        JSONArray jsonArr=jsonObjectArr.getJSONArray("list");
        //遍历视频 存入数据
        for(Object json:jsonArr){
            BiliUser biliUser=new BiliUser();
            biliUser.jsonToThis((JSONObject) json);
            biliUserList.add(biliUser);
        }
    }
    public int getCoin(String bvid){
        getCoinInfo();
        for(CoinInfo coinInfo:coinInfoList){
            if(coinInfo.bvid.equals(bvid)){
                return coinInfo.coins;
            }
        }
        return 0;
    }
    public boolean isLike(String bvid){
        getLikeInfo();
        for(LikeInfo likeInfo:likeInfoList){
            if(likeInfo.bvid.equals(bvid)){
                return true;
            }
        }
        return false;
    }
    public boolean isFollowing(String value,String type){
        getBiliUser();
        for(BiliUser bu:biliUserList){
            if(type.equalsIgnoreCase("buid")){
                if(bu.mid.equals(value)){
                    return true;
                }
            }else if(type.equalsIgnoreCase("uname")){
                if(bu.uname.equals(value)){
                    return true;
                }
            }else {
                return false;
            }

        }
        return false;
    }
    public String isTrueBuid(){
        JSONObject jsonObject= DataGet.getPlayerCoinInfo(buid);
        String msg= (String) jsonObject.get("message");
        if(msg.equals("0")){
            return msg;
        }else{
            if(msg.equals("请求错误")){
                return "&e错误的用户";
            }else {
                return "&e隐私设置为设置为未公开";
            }
        }
    }
    public void savePlayerData(){
        File dir = new File(plugin.getDataFolder(), "data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(plugin.getDataFolder(), "data/" + uuid + ".yml");
        if(file.exists()){
            YamlConfiguration ym = YamlConfiguration.loadConfiguration(file);
            ym.set("buid",buid);
            ym.set("got_coin_reward",got_coin_reward);
            ym.set("got_followings_reward",got_followings_reward);
            ym.set("got_like_reward",got_like_reward);
            try {
                ym.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            YamlConfiguration ym = YamlConfiguration.loadConfiguration(file);
            ym.set("buid",buid);
            ym.set("got_coin_reward",got_coin_reward);
            ym.set("got_followings_reward",got_followings_reward);
            ym.set("got_like_reward",got_like_reward);
            try {
                ym.save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
