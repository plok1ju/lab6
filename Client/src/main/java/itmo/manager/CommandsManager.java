package itmo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.exceptions.CollectionException;
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
public class CommandsManager {
    /**
     * Конструктор класса CommandsManager
     */
    public CommandsManager() {
    }

    private CommandInfo receiveCommandInfo(String commandName, Scannable clientReader, Printable clientPrinter) throws IOException, CollectionException {
        clientPrinter.printLine(commandName);
        String json = clientReader.scanString();
        ObjectMapper objectMapper = new ObjectMapper();
        CommandInfo commandInfo = objectMapper.readValue(json, CommandInfo.class);
        if (!commandInfo.isStatus())
            throw new CollectionException("Incorrect command");
        return commandInfo;
    }

    private void waitResponseFromServer(Scannable scannable, Scannable clientReader, Printable clientPrinter) throws IOException {
        while (true){
            String response = "resp: ".concat(clientReader.scanString());
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

    /**
     * Метод определяющий команду
     *
     * @param commandLine - значение поля commandLine
     * @param scannable   - значение поля scannable
     * @param isConsole   - значение поля isConsole
     * @return - введенная команда
     */
    public void getCommand(String commandLine, Scannable scannable, Scannable clientReader, Printable clientPrinter, boolean isConsole) throws Exception {
        try {
            String[] arrayLine = commandLine.trim().replaceAll("\\s+", " ").split(" ");

            if (arrayLine.length == 0) {
                throw new CollectionException("Нет команд");

            }
            String command = arrayLine[0];
            CommandInfo commandInfo = receiveCommandInfo(command, clientReader, clientPrinter);
//            System.out.println("CI: " + commandInfo.getName());
            ObjectMapper objectMapper = new ObjectMapper();
            CommandArguments commandArguments = new CommandArguments();
            switch (commandInfo.getName()) {
                case "clear": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
//                    commandInfo.setArguments();

                    String json = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(json);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "show": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
//                    commandInfo.setArguments();
                    String json = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(json);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "info": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }

                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(jsonArgs);

                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "insert": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole);

                    Class[] types = {Integer.class, DragonBuilder.class};
                    Object[] args = {key, dragonBuilder};

                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);

                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "exit": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }

                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);

                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "help": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }

                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);

                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "print_descending": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }

                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);

                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "remove_all_by_color": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Color color = Color.parse(arrayLine[1]);

                    Object[] args = {color};
                    Class[] types = {Color.class};

                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);

                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);

                    break;
                }

                case "remove_greater_key": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);

                    Object[] args = {key};
                    Class[] types = {key.getClass()};

                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);

                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "remove_key": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);

                    Object[] args = {key};
                    Class[] types = {key.getClass()};

                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);

                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "save": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }

                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "sum_of_age": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }

                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }


                case "remove_lower": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole);

                    Object[] args = {dragonBuilder};
                    Class[] types = {dragonBuilder.getClass()};

                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "replace_if_lower": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole);
                    Integer key = Integer.parseInt(arrayLine[1]);
                    Object[] args = {key,dragonBuilder};
                    Class[] types = {key.getClass(), dragonBuilder.getClass()};

                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;
                }

                case "update": {
                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole);
                    Long id = Long.parseLong(arrayLine[1]);
                    Object[] args = {id, dragonBuilder};
                    Class[] types = {id.getClass(), dragonBuilder.getClass()};

                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(jsonArgs);
                    waitResponseFromServer(scannable, clientReader, clientPrinter);
                    break;

                }
                case "execute_script": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    String nameFile = arrayLine[1];
                    Object[] args = {nameFile};
                    Class[] types = {String.class};
                    commandArguments.arguments = args;
                    commandArguments.types = types;
                    String jsonArgs = objectMapper.writeValueAsString(commandArguments);
                    clientPrinter.printLine(jsonArgs);

                    Thread.sleep(1000);
                    Scannable fileScan = new FileScan(nameFile);
                    while (fileScan.hasNextLine()) {
                        String commandString = fileScan.scanString();
                        this.getCommand(commandString, fileScan, clientReader, clientPrinter, false);

                    }


                    break;
                }
                default: {
                    throw new CollectionException("Такой команды нет :(");
                }

            }
        } catch (Exception e) {
            throw new CollectionException("Ошибка на строчке " + scannable.linesCount() + ": " + e.getMessage());
        }
    }
}
