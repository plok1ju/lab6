package server.commands;

import org.helper.Response;
import org.helper.exceptions.CollectionException;
import org.helper.model.Dragon;
import server.Main;
import java.util.List;

/**
 * Класс отвечает за добавление нового элемента в коллекцию
 */
public class Insert extends Command {
    /**
     * Переопределение метода execute
     * Добавление элемента в коллекцию
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        Integer key = (Integer) args.get(0);
        if (Main.collection.getKeysAsList().contains(key)) {
            throw new CollectionException("Элемент с таким key уже есть");
        }
        Main.collection.put(key, (Dragon) args.get(1));

    }
}
