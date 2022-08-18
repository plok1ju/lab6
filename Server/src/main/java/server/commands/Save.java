package server.commands;

import org.helper.Response;
import server.Main;
import server.manager.file.FileSaver;

import java.util.List;

/**
 * Класс отвечает за сохранение коллекции в файл xml
 */
public class Save extends Command {
    private final FileSaver fileSaver = new FileSaver();
    /**
     * Переопределение метода execute
     * Сохранение коллекции в файл
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        fileSaver.saveInfo(Main.collection);

    }

}
