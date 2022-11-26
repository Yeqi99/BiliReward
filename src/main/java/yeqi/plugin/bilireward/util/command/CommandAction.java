package yeqi.plugin.bilireward.util.command;

public enum CommandAction {
    SELF, ADMIN, CONSOLE, UNDEFINED,TELL,TELLALL,POINTS,MONEY;

    public static CommandAction fromString(String strAction) {
        if (strAction.equalsIgnoreCase("self")) {
            return CommandAction.SELF;
        } else if (strAction.equalsIgnoreCase("admin")) {
            return CommandAction.ADMIN;
        } else if (strAction.equalsIgnoreCase("console")) {
            return CommandAction.CONSOLE;
        }else if (strAction.equalsIgnoreCase("tell")) {
            return CommandAction.TELL;
        }else if (strAction.equalsIgnoreCase("tellall")) {
            return CommandAction.TELLALL;
        }else if (strAction.equalsIgnoreCase("points")) {
            return CommandAction.POINTS;
        }else if (strAction.equalsIgnoreCase("money")) {
            return CommandAction.MONEY;
        } else {
            return CommandAction.UNDEFINED;
        }
    }
}
