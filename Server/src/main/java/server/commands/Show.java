package server.commands;

import server.Main;
import server.exceptions.CollectionException;
import server.exceptions.ServerException;
import server.utils.Response;

import java.util.List;

/**
 * Класс отвечает за показ коллекции
 */
public class Show extends Command {


    /**
     * Переопределение метода execute
     * Вывод коллекции
     */
    @Override
    public void execute(List<Object> args, Response response) throws ServerException, CollectionException {
        List<?> keys = Main.collection.getKeysAsList();
        for (Object key : keys) {
            response.Add("=====");
            response.Add("Ключ элемента: " + key + "\n" + Main.collection.get(key).toString());
            response.Add("=====");
        }
    }

}
