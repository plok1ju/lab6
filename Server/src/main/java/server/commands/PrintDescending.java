package server.commands;


import server.Main;
import server.comparators.CollectionComparator;
import server.exceptions.ServerException;
import server.io.Printable;
import server.utils.Response;

import java.util.List;

/**
 * Класс отвечает за вывод коллекции в порядке убывания
 */
public class PrintDescending extends Command {

    /**
     * Переопределение метода execute
     * Вывод элементов коллекции
     */
    @Override
    public void execute(List<Object> args, Response response) throws ServerException {
        List<Integer> keys = Main.collection.getKeysAsList();
        keys.sort(new CollectionComparator(Main.collection).reversed()); // реверс ключей
        for (Integer key : keys) {
            Object o = Main.collection.get(key);
            response.Add("=====\n");
            response.Add("Ключ элемента " + key + ": " + o + "\n");
        }
    }
}
