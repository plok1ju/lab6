package server.utils;

import java.util.List;

public class CommandInfo {
    private final String commandName;
    private final List<Object> args;

    public CommandInfo(String commandName, List<Object> args) {
        this.commandName = commandName;
        this.args = args;
    }

    public List<Object> getArgs() {
        return args;
    }

    public String getCommandName() {
        return commandName;
    }
}
