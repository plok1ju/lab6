package client.manager;
import org.helper.CommandInfo;
import org.helper.Printable;
import org.helper.Response;
import org.helper.Scannable;
import org.helper.exceptions.CollectionException;
import org.helper.model.User;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Этот класс определяет команду
 */
public class CommandsManager {
    /**
     * Конструктор класса CommandsManager
     */

    String login = "", password = "";
    final Printable<CommandInfo> clientPrinter;
    final Scannable<Response> clientReader;
    public CommandsManager(Printable<CommandInfo> clientPrinter, Scannable<Response> clientReader) {
        this.clientPrinter = clientPrinter;
        this.clientReader = clientReader;
    }

    public void getCommand(String commandLine) throws Exception {
        String[] arrayLine = commandLine.trim().replaceAll("\\s+", " ").split(" ");

        if (arrayLine.length == 0) {
            throw new CollectionException("Нет команд");
        }
        String command = arrayLine[0];
        if (command.equals("exit"))
            System.exit(0);
        if(command.equals("login")){
            if(arrayLine.length >= 3){
                login = arrayLine[1];
                password = arrayLine[2];
            }
            else{
                System.out.println("недостаточно аргументов");
            }
            return;
        }
        CommandInfo commandInfo = new CommandInfo(command, Arrays.stream(arrayLine).skip(1).collect(Collectors.toList()), new User(login, password));


        clientPrinter.print(commandInfo);
        System.out.println(clientReader.scan());
    }
}
