package org.helper;

import org.helper.model.User;

import java.io.Serializable;
import java.util.List;

public class CommandInfo implements Serializable {

    private static final long serialVersionUID = 12;

    private final String commandName;
    private final List<Object> args;

    private final User user;

    public CommandInfo(String commandName, List<Object> args, User user) {
        this.commandName = commandName;
        this.args = args;
        this.user = user;
    }

    public List<Object> getArgs() {
        return args;
    }

    public String getCommandName() {
        return commandName;
    }
}
