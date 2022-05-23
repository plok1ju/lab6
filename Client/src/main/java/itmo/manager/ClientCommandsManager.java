package itmo.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.exceptions.CollectionException;
import itmo.exceptions.ServerException;
import itmo.io.FileScan;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.model.Color;
import itmo.model.builders.DragonBuilder;
import itmo.utils.CommandArguments;
import itmo.utils.CommandInfo;

import java.io.IOException;

/**
 * Этот класс определяет команду
 */
public class ClientCommandsManager {
    /**
     * Конструктор класса CommandsManager
     */
    public ClientCommandsManager() {
    }

    private CommandInfo receiveCommandInfo(String commandName, Scannable clientReader, Printable clientPrinter) throws Exception {
        clientPrinter.printLine(commandName);
        String json = clientReader.scanString();
        ObjectMapper objectMapper = new ObjectMapper();
        CommandInfo commandInfo = objectMapper.readValue(json, CommandInfo.class);
        if (!commandInfo.isStatus())
            throw new CollectionException("Incorrect command");
        return commandInfo;
    }

    private void waitResponseFromServer(Scannable scannable, Scannable clientReader, Printable clientPrinter) throws Exception {
        while (true){
            String response = clientReader.scanString();
            if (response.contains("/exit/"))
                System.exit(0);
            if (response.endsWith("/end/")){
                System.out.println(response.replace("/end/", "").replace("/noresponse/", ""));
                break;
            }
            boolean needSending = !response.contains("/noresponse/");
            System.out.println(response.replace("/noresponse/", ""));
            if (needSending) {
                String send = scannable.scanString();
                clientPrinter.printLine(send);
            }
        }
    }

    private void sendArgumentsToServer(CommandArguments commandArguments, Printable clientPrinter, Scannable clientReader, Scannable scannable) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(commandArguments);
        clientPrinter.printLine(json);
        waitResponseFromServer(scannable, clientReader, clientPrinter);
    }

    public void getCommand(String commandLine, Scannable scannable, Scannable clientReader, Printable clientPrinter, boolean isConsole) throws Exception {
        String[] arrayLine = commandLine.trim().replaceAll("\\s+", " ").split(" ");

        if (arrayLine.length == 0) {
            throw new CollectionException("Нет команд");

        }
        String command = arrayLine[0];
        CommandInfo commandInfo = receiveCommandInfo(command, clientReader, clientPrinter);
        CommandArguments commandArguments = CommandsManager.getCommandArguments(commandLine, commandInfo, scannable, isConsole);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(commandArguments);
        clientPrinter.printLine(json);
        waitResponseFromServer(scannable, clientReader, clientPrinter);
    }
}
