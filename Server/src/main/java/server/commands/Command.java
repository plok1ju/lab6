package server.commands;


import org.helper.Response;

import java.util.List;

/**
 * Интерфейс объекта, который отвечает за выполнение команд
 */
public abstract class Command {
    /**
     * Обеспечивает выполнение команд
     */
    public abstract void execute(List<Object> args, Response response) throws Exception;

    public String getName(){
        return getClass().getSimpleName();
    }
}
