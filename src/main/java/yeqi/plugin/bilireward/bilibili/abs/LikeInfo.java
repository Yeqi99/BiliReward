package yeqi.plugin.bilireward.bilibili.abs;

import com.alibaba.fastjson.JSONObject;
import yeqi.plugin.bilireward.util.Sender;

public class LikeInfo {
    public String bvid;
    public String title;
    public Owner owner=new Owner();
    public Stat stat=new Stat();
    public void jsonToThis(JSONObject json){
        bvid=json.getString("bvid");
        title=json.getString("title");
        owner.jsonToThis(json.getJSONObject("owner"));
        stat.jsonToThis(json.getJSONObject("stat"));
    }
}
