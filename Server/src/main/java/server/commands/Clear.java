package server.commands;

import server.Main;
import server.collection.HashTableCollection;
import server.io.Printable;
import server.utils.Response;

import java.awt.image.RescaleOp;
import java.util.List;

/**
 * Класс отвечает за очищение коллекции
 */
public class Clear extends Command {
    /**
     * Переопределение метода execute
     * Очищение коллекции
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        Main.collection.clear();
        response.Add("Коллекция очищена");
    }
}
