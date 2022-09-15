package yeqi.plugin.bilireward.bilibili.abs;

import com.alibaba.fastjson.JSONObject;

public class CoinInfo {
    public String bvid;
    public String title;
    public Owner owner=new Owner();
    public Stat stat=new Stat();
    public int coins;
    public void jsonToThis(JSONObject json){
        bvid=json.getString("bvid");
        title=json.getString("title");
        coins=json.getInteger("coins");
        owner.jsonToThis(json.getJSONObject("owner"));
        stat.jsonToThis(json.getJSONObject("stat"));
    }
}
