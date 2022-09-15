package yeqi.plugin.bilireward.util.command;

import java.util.List;

public class FormatCommandGroup {
    String name;
    List<FormatCommand> fcList;

    public FormatCommandGroup(String name, List<FormatCommand> fcList) {
        this.name = name;
        this.fcList = fcList;
    }
}

