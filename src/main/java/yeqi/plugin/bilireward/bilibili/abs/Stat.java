package yeqi.plugin.bilireward.bilibili.abs;

import com.alibaba.fastjson.JSONObject;

import static yeqi.plugin.bilireward.BiliReward.plugin;

public class Stat {
    //播放量
    long view;
    //点赞
    long like;
    //不喜欢
    long dislike;
    //弹幕
    long danmaku;
    //分享
    long share;
    //回复
    long replay;
    //收藏
    long favorite;
    //总投币
    long coin;
    public void jsonToThis(JSONObject json){
        view=json.getLong("view");
        like=json.getLong("like");
        dislike=json.getLong("dislike");
        danmaku=json.getLong("danmaku");
        share=json.getLong("share");
        replay=json.getLong("reply");
        favorite=json.getLong("favorite");
        coin=json.getLong("coin");
    }
}
