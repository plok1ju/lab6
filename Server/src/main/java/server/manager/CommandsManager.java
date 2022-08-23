package server.manager;

import org.helper.Response;
import server.commands.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс определяет команду
 */
public class CommandsManager {

    private static final List<Command> commands;
    private static final List<String> descCommands;

    static {
        descCommands = new ArrayList<>();
        commands = new ArrayList<>();
        commands.add(new Help());
        commands.add(new Info());
        descCommands.add("Info");
        descCommands.add("Help");
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static List<String> getDescCommands() {
        return descCommands;
    }

    public Response Execute(String command, List<Object> args) throws Exception {
      for (Command currCommand : commands){
          if(command.startsWith(currCommand.getName())){
              Response response = new Response();
              currCommand.execute(args, response);
              return response;
          }
      }
      return new Response().Add("команды не существует");
    }
}
