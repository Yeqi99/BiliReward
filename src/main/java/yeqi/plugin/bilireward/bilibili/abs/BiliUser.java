package yeqi.plugin.bilireward.bilibili.abs;

import com.alibaba.fastjson.JSONObject;

public class BiliUser {
    String mid;
    String uname;
    public void jsonToThis(JSONObject json){
        mid=json.getString("mid");
        uname=json.getString("uname");
    }
}
