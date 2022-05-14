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

    public void sendCommandInfo(Scannable serverReader, Printable serverPrinter) throws IOException, CollectionException {
        String commandName = serverReader.scanString();
        CommandInfo commandInfo = getCommandInfo(commandName);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
        serverPrinter.printLine(json);
    }
    public CommandInfo getCommandInfo(String commandName) throws CollectionException {
        switch (commandName) {
            case "clear": {
                return new CommandInfo(0, 0, commandName);
            }

            case "show": {
                return new CommandInfo(0, 0, commandName);
            }

            case "info": {
                return new CommandInfo(0, 0, commandName);

            }

            case "insert": {
                return new CommandInfo(1, 1, commandName);
            }

            case "exit": {
                return new CommandInfo(0, 0, commandName);
            }

            case "help": {
                return new CommandInfo(0, 0, commandName);
            }

            case "print_descending": {
                return new CommandInfo(0, 0, commandName);
            }

            case "remove_all_by_color": {
                return new CommandInfo(1, 0, commandName);
            }

            case "remove_greater_key": {
                return new CommandInfo(1, 0, commandName);
            }

            case "remove_key": {

                return new CommandInfo(1, 0, commandName);
            }

            case "save": {
                return new CommandInfo(0, 0, commandName);
            }

            case "sum_of_age": {
                return new CommandInfo(0, 0, commandName);
            }


            case "remove_lower": {
                return new CommandInfo(0, 1, commandName);
            }

            case "replace_if_lower": {

                return new CommandInfo(1, 1, commandName);
            }

            case "update": {
                return new CommandInfo(1, 1, commandName);

            }
            case "execute_script": {

                return new CommandInfo(1, 0, commandName);
            }
            default: {
                CommandInfo commandInfo = new CommandInfo(0,0, null);
                commandInfo.setStatus(false);
                return commandInfo;
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
    public Command getCommand(String commandLine, Scannable scannable, boolean isConsole) throws Exception {
        try {
            String[] arrayLine = commandLine.trim().replaceAll("\\s+", " ").split(" ");

            if (arrayLine.length == 0) {
                throw new CollectionException("Нет команд");

            }
            String command = arrayLine[0];
            switch (command) {
                case "clear": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new Clear(collection);
                }

                case "show": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new Show(collection);
                }

                case "info": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new Info(collection);

                }

                case "insert": {

                    if (arrayLine.length < 2) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    return new Insert(collection, key, dragonBuilder);
                }

                case "exit": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new Exit();

                }

                case "help": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new Help();
                }

                case "print_descending": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new PrintDescending(collection);
                }

                case "remove_all_by_color": {

                    if (arrayLine.length < 2) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Color color = Color.parse(arrayLine[1]);
                    return new RemoveAllByColor(collection, color);
                }

                case "remove_greater_key": {

                    if (arrayLine.length < 2) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);
                    return new RemoveGreaterKey(collection, key);
                }

                case "remove_key": {

                    if (arrayLine.length < 2) {
                        throw new CollectionException("Введены не все поля");
                    }
                    Integer key = Integer.parseInt(arrayLine[1]);
                    return new RemoveKey(collection, key);
                }

                case "save": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new Save(collection);
                }

                case "sum_of_age": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    return new SumOfAge(collection);
                }


                case "remove_lower": {
                    if (arrayLine.length > 1) {
                        throw new CollectionException("Команда введена некорректно");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    return new RemoveLower(collection, dragonBuilder);
                }

                case "replace_if_lower": {

                    if (arrayLine.length < 2) {
                        throw new CollectionException("Введены не все поля");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    Integer key = Integer.parseInt(arrayLine[1]);
                    return new ReplaceIfLower(collection, key, dragonBuilder);
                }

                case "update": {
                    if (arrayLine.length < 2) {
                        throw new CollectionException("Введены не все поля");
                    }
                    DragonBuilder dragonBuilder = new DragonBuilder(isConsole, scannable);
                    Long id = Long.parseLong(arrayLine[1]);
                    return new UpdateId(collection, id, dragonBuilder);

                }
                case "execute_script": {

                    if (arrayLine.length < 2) {
                        throw new CollectionException("Введены не все поля");
                    }
                    String nameFile = arrayLine[1];
                    return new ExecuteScript(nameFile, collection);
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
