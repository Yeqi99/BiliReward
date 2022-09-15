package yeqi.plugin.bilireward.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.plugin.bilireward.BiliReward;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Setting {
    public static List<String> find_help=new ArrayList<String>(){{
        add("&c输入/bili(br) help 获得指令帮助");
    }};
    public static List<String> help=new ArrayList<String>(){{
        add("&8| &c/br help");
        add("&7获取帮助信息");
        add("&8| &c/br bind <buid> [name]");
        add("&7绑定bilibili账号");
        add("&8| &c/br getcoin <bvid> [name]");
        add("&7查看玩家对于某个视频的投币数量");
        add("&8| &c/br iscoin <bvid> [name]");
        add("&7查看玩家对于某个视频是否点赞");
        add("&8| &c/br isfollow <bili_name> [name]");
        add("&7查看是否关注");
        add("&8| &c/br get [name]");
        add("&7获取当前所有能获取的奖励");
        add("&8| &c/br reload");
        add("&7重载插件");
    }};
    public static List<String> unknow_cmd=new ArrayList<String>(){{
        add("&c未知指令");
    }};
    public static List<String> unknow_player=new ArrayList<String>(){{
        add("&c未知玩家");
    }};
    public static List<String> none_can_get=new ArrayList<String>(){{
        add("&c没有可获得的奖励");
    }};
    public static List<String> bind_success=new ArrayList<String>(){{
        add("&c绑定成功");
    }};
    public static List<String> bind_fail=new ArrayList<String>(){{
        add("&c已绑定或绑定失败");
    }};
    public static List<String> bind_no=new ArrayList<String>(){{
        add("&c未绑定");
    }};
    public static String title_get_coin="&7【投币奖励&7】";
    public static String title_get_like="&7【点赞奖励&7】";
    public static String title_get_following="&7【关注奖励&7】";
    public static String already_bind="&c这个账号已经被绑定过了";
    public static String get_coin_1_prefix="&c";
    public static String get_coin_2_prefix="&c";
    public static String get_coin_1_msg=" &7投币奖励(硬币*1)";
    public static String get_coin_2_msg=" &7投币奖励(硬币*2)";
    public static String get_like_prefix="&c";
    public static String get_like_msg=" &7点赞奖励";
    public static String get_following_prefix="&c";
    public static String get_following_msg=" &7关注奖励";

    public static void loadLang(){
        BiliReward.plugin.reloadConfig();
        FileConfiguration ym=BiliReward.plugin.getConfig();
        find_help=ym.getStringList("find_help");
        help=ym.getStringList("help");
        unknow_cmd=ym.getStringList("unknow_cmd");
        unknow_player=ym.getStringList("unknow_player");
        none_can_get=ym.getStringList("none_can_get");
        bind_success=ym.getStringList("bind_success");
        bind_fail=ym.getStringList("bind_fail");
        bind_no=ym.getStringList("bind_no");
        title_get_coin=ym.getString("title_get_coin");
        title_get_like=ym.getString("title_get_like");
        title_get_following=ym.getString("title_get_following");
        already_bind=ym.getString("already_bind");
        get_coin_1_prefix=ym.getString("get_coin_1_prefix");
        get_coin_2_prefix=ym.getString("get_coin_2_prefix");
        get_coin_1_msg=ym.getString("get_coin_1_msg");
        get_coin_2_msg=ym.getString("get_coin_2_msg");
        get_like_prefix=ym.getString("get_like_prefix");
        get_like_msg=ym.getString("get_like_msg");
        get_following_prefix=ym.getString("get_following_prefix");
        get_following_msg=ym.getString("get_following_msg");

    }
}
