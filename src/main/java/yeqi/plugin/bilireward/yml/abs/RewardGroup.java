package yeqi.plugin.bilireward.yml.abs;

import org.bukkit.configuration.file.YamlConfiguration;
import yeqi.plugin.bilireward.util.command.FormatCommandGroup;

public class RewardGroup {
    public String name;
    public String display;
    //指令组
    public FormatCommandGroup fcg;
    public YamlConfiguration rgYm = new YamlConfiguration();
}
