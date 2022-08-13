package server.commands;

import jdk.internal.org.objectweb.asm.util.Printer;
import server.Main;
import server.collection.HashTableCollection;
import server.io.Printable;
import server.manager.file.FileSaver;
import server.model.Dragon;
import server.utils.Response;

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
