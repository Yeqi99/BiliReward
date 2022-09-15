package yeqi.plugin.bilireward.util.command;

public enum CommandAction {
    SELF, ADMIN, CONSOLE, UNDEFINED;

    public static CommandAction fromString(String strAction) {
        if (strAction.equalsIgnoreCase("self")) {
            return CommandAction.SELF;
        } else if (strAction.equalsIgnoreCase("admin")) {
            return CommandAction.ADMIN;
        } else if (strAction.equalsIgnoreCase("console")) {
            return CommandAction.CONSOLE;
        } else {
            return CommandAction.UNDEFINED;
        }
    }
}
