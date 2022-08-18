package server.commands;

import org.helper.Response;
import server.manager.CommandsManager;
import java.util.List;

/**
 * Класс отвечает за выведение информации о доступных командах
 */
public class Help extends Command {

    /**
     * Переопределение метода execute
     * Вывод возможных команд
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        List<Command> commands = CommandsManager.getCommands();
        List<String> commandsDesc = CommandsManager.getDescCommands();
        for (int i = 0; i < commands.size(); i++){
            response.Add(commands.get(i).getName() + ": " + commandsDesc.get(i));
        }
    }

}
