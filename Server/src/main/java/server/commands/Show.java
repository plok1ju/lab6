package server.commands;

import org.helper.Response;
import org.helper.exceptions.CollectionException;
import org.helper.exceptions.ServerException;
import server.Main;
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
