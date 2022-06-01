package itmo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.exceptions.CollectionException;
import itmo.exceptions.ServerException;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.utils.CommandArguments;
import itmo.utils.CommandInfo;

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

    private void waitResponseFromServer(Scannable clientReader) throws Exception {
        String response = clientReader.scanString();
        System.out.println(response);
    }

    private void sendArgumentsToServer(CommandArguments commandArguments, Printable clientPrinter, Scannable clientReader) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(commandArguments);
        clientPrinter.printLine(json);
        waitResponseFromServer(clientReader);
    }

    public void getCommand(String commandLine, Scannable scannable, Scannable clientReader, Printable clientPrinter, boolean isConsole) throws Exception {
        String[] arrayLine = commandLine.trim().replaceAll("\\s+", " ").split(" ");

        if (arrayLine.length == 0) {
            throw new CollectionException("Нет команд");

        }
        String command = arrayLine[0];
        if (command.equals("exit"))
            System.exit(0);
        CommandInfo commandInfo = receiveCommandInfo(command, clientReader, clientPrinter);

        CommandArguments commandArguments = null;
        try {
            commandArguments = CommandsManager.getCommandArguments(commandLine, commandInfo, scannable, isConsole);
        } catch (ServerException serverException){
            throw serverException;
        } catch (Exception e){
            clientPrinter.printLine("/error/");
            throw e;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(commandArguments);
        clientPrinter.printLine(json);
        waitResponseFromServer(clientReader);
    }
}
