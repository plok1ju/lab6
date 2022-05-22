package itmo.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import itmo.collection.HashTableCollection;
import itmo.commands.*;
import itmo.deserializers.ArgumentsDeserializer;
import itmo.exceptions.CollectionException;
import itmo.exceptions.ServerException;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.model.Dragon;
import itmo.utils.CommandArguments;
import itmo.utils.CommandInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void sendCommandInfo(Scannable serverReader, Printable serverPrinter, boolean isConsole) throws Exception {
        String commandName = serverReader.scanString();
        CommandInfo commandInfo = getCommandInfo(commandName);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(commandInfo);
        serverPrinter.printLine(json);
        if (!commandInfo.isStatus())
            return;
//        String jsonTypes = serverReader.scanString();
//        String jsonArgs = serverReader.scanString();
//        CommandArguments commandArguments = ArgumentsDeserializer.deserialize(jsonTypes, jsonArgs);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(CommandArguments.class, new ArgumentsDeserializer());
        objectMapper.registerModule(module);

        String args = serverReader.scanString();
        System.out.println("Args: " + args);
        CommandArguments commandArguments = objectMapper.readValue(args, CommandArguments.class);
        try {
            getCommand(commandInfo, commandArguments, serverReader, serverPrinter, isConsole).execute();
        } catch (ServerException serverException){
            throw serverException;
        }
        catch (Exception e){
            serverPrinter.printLine("/noresponse/Что-то не так: " + e.getMessage());
        }
        serverPrinter.printLine("/end/");
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

            /*case "save": {
                return new CommandInfo(0, 0, commandName);
            }*/

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
     * @param scannable   - значение поля scannable
     * @param isConsole   - значение поля isConsole
     * @return - введенная команда
     */
    public Command getCommand(CommandInfo commandInfo, CommandArguments commandArguments, Scannable scannable, Printable printable, boolean isConsole) throws Exception {
        try {
            switch (commandInfo.getName()) {
                case "clear": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(printable);
                    return (Command) Clear.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "show": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(printable);
                    return (Command) Show.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "info": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(printable);
                    return (Command) Info.class.getConstructors()[0].newInstance(args.toArray());

                }

                case "insert": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(scannable);
                    args.add(printable);
                    return (Command) Insert.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "exit": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(printable);
                    return (Command) Exit.class.getConstructors()[0].newInstance(args.toArray());

                }

                case "help": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(printable);
                    return (Command) Help.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "print_descending": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(printable);
                    return (Command) PrintDescending.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "remove_all_by_color": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    return (Command) RemoveAllByColor.class.getConstructors()[0].newInstance(args.toArray());

                }

                case "remove_greater_key": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    return (Command) RemoveGreaterKey.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "remove_key": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    return (Command) RemoveKey.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "save": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    return (Command) Save.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "sum_of_age": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(printable);
                    return (Command) SumOfAge.class.getConstructors()[0].newInstance(args.toArray());
                }


                case "remove_lower": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(scannable);
                    args.add(printable);
                    return (Command) RemoveLower.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "replace_if_lower": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(scannable);
                    args.add(printable);
                    return (Command) ReplaceIfLower.class.getConstructors()[0].newInstance(args.toArray());
                }

                case "update": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(scannable);
                    args.add(printable);
                    return (Command) UpdateId.class.getConstructors()[0].newInstance(args.toArray());
                }
                case "execute_script": {
                    List<Object> args = new ArrayList<>(Arrays.asList(commandArguments.arguments));
                    args.add(collection);
                    args.add(scannable);
                    args.add(printable);
                    return (Command) ExecuteScript.class.getConstructors()[0].newInstance(args.toArray());
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
