package yeqi.plugin.bilireward.bilibili;



import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import yeqi.plugin.bilireward.bilibili.abs.PlayerData;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataGet {
    //根据玩家UID获取数据
    public static JSONObject getPlayerCoinInfo(String buid) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = "https://api.bilibili.com/x/space/coin/video?vmid="+buid+"&jsonp=jsonp";
        HttpGet httpGet=new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(httpGet);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject getPlayerLikeInfo(String buid) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = "https://api.bilibili.com/x/space/like/video?vmid="+buid+"&jsonp=jsonp";
        HttpGet httpGet=new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(httpGet);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static JSONObject getPlayerFollowingsInfo(String buid) {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法
        String url = "http://api.bilibili.com/x/relation/followings?vmid="+buid+"&ps=20";
        HttpGet httpGet=new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse res = client.execute(httpGet);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 返回json格式：
                jsonObject = JSONObject.parseObject(EntityUtils.toString(res.getEntity()));
            }
        } catch (Exception e) {
            System.out.println("服务间接口调用出错！");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
