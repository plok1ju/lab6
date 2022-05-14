package itmo.utils;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import itmo.commands.Command;

public class CommandInfo {

    private boolean status = true;
    private int simpleArguments;
    private int complexArguments;
    private String name;

    private Command command;

    public CommandInfo(){}
    public CommandInfo(int simpleArguments, int complexArguments, String name) {
        this.simpleArguments = simpleArguments;
        this.complexArguments = complexArguments;
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSimpleArguments() {
        return simpleArguments;
    }

    public void setSimpleArguments(int simpleArguments) {
        this.simpleArguments = simpleArguments;
    }

    public int getComplexArguments() {
        return complexArguments;
    }

    public void setComplexArguments(int complexArguments) {
        this.complexArguments = complexArguments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
