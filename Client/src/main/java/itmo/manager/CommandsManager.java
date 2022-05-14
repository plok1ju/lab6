package itmo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.collection.HashTableCollection;
import itmo.commands.*;
import itmo.exceptions.CollectionException;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.model.Color;
import itmo.model.Dragon;
import itmo.model.builders.DragonBuilder;
import itmo.utils.CommandInfo;

import java.io.IOException;

/**
 * Этот класс определяет команду
 */
public class CommandsManager {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<Integer, Dragon> collection;
    /**
     * Конструктор класса CommandsManager
     *
     * @param collection - значение поля collection
     */
    public CommandsManager(HashTableCollection<Integer, Dragon> collection) {
        this.collection = collection;
    }

    public CommandInfo receiveCommandInfo(String commandName, Scannable clientReader, Printable clientPrinter) throws IOException, CollectionException {
        clientPrinter.printLine(commandName);
        String json = clientReader.scanString();
        ObjectMapper objectMapper = new ObjectMapper();
        CommandInfo commandInfo = objectMapper.readValue(json, CommandInfo.class);
        if (!commandInfo.isStatus())
            throw new CollectionException("Incorrect command");
        return commandInfo;
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
            CommandInfo commandInfo = receiveCommandInfo(commandLine, clientReader, clientPrinter);
            ObjectMapper objectMapper = new ObjectMapper();
            switch (commandInfo.getName()) {
                case "clear": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new Clear(collection));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "show": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new Show(collection));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "info": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new Info(collection));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    //clientPrinter.printLine(json);
                    System.out.println(json);
                    break;
                }

                case "insert": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    commandInfo.setCommand(new Insert(collection, key, dragonBuilder));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                }

                case "exit": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new Exit());
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "help": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new Help());
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "print_descending": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new PrintDescending(collection));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "remove_all_by_color": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Color color = Color.parse(arrayLine[1]);
                    commandInfo.setCommand(new RemoveAllByColor(collection, color));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "remove_greater_key": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);
                    commandInfo.setCommand(new RemoveGreaterKey(collection, key));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "remove_key": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);
                    commandInfo.setCommand(new RemoveKey(collection, key));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "save": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new Save(collection));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "sum_of_age": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    commandInfo.setCommand(new SumOfAge(collection));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }


                case "remove_lower": {
                    if (arrayLine.length > commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    commandInfo.setCommand(new RemoveLower(collection, dragonBuilder));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "replace_if_lower": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    Integer key = Integer.parseInt(arrayLine[1]);
                    commandInfo.setCommand(new ReplaceIfLower(collection, key, dragonBuilder));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;
                }

                case "update": {
                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    Long id = Long.parseLong(arrayLine[1]);
                    commandInfo.setCommand(new UpdateId(collection, id, dragonBuilder));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
                    break;

                }
                case "execute_script": {

                    if (arrayLine.length < commandInfo.getSimpleArguments() + 1) {
                        throw new CollectionException("Введены не все поля");
                    }
                    String nameFile = arrayLine[1];
                    commandInfo.setCommand(new ExecuteScript(nameFile, collection));
                    String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
                    clientPrinter.printLine(json);
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
