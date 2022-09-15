package yeqi.plugin.bilireward.bilibili.abs;

import com.alibaba.fastjson.JSONObject;

import static yeqi.plugin.bilireward.BiliReward.plugin;

public class Owner {
    //头像地址
    String face;
    //名字
    String name;
    //uid
    String mid;
    public void jsonToThis(JSONObject json){
        face=json.getString("face");
        mid=json.getString("mid");
        name=json.getString("name");
    }
}
