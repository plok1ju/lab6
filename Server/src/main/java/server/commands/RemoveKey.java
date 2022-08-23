package server.commands;

import org.helper.Response;
import org.helper.exceptions.CollectionException;
import server.Main;
import java.util.List;

/**
 * Класс отвечает за удаление элементов по значению ключа
 */
public class RemoveKey extends Command {

    /**
     * Переопределение метода execute
     * Удаление элемента из коллекции по ключу
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        Integer key = (Integer) args.get(0);
        if (!Main.collection.getKeysAsList().contains(key)) {
            throw new CollectionException("Нет такого key");
        }
        Main.collection.remove(key);
    }


}
