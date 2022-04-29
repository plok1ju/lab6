package itmo.manager;

import itmo.collection.HashTableCollection;
import itmo.commands.*;
import itmo.exceptions.CollectionException;
import itmo.io.Scannable;
import itmo.model.Color;
import itmo.model.Dragon;
import itmo.model.builders.DragonBuilder;

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
