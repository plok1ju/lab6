package server.commands;

import org.helper.Response;
import server.Main;
import java.util.List;

/**
 * Класс отвечает за удаление элементов по значению ключа
 * Если ключ превышает поле key
 */
public class RemoveGreaterKey extends Command {
    /**
     * Переопределение метода execute
     * Удаление элементов из коллекции если их ключ превышает поле key
     */
    @Override
    public void execute(List<Object> args, Response response) {
        Integer key = (Integer) args.get(0);
        List<? extends Integer> keys = Main.collection.getKeysAsList();
        keys.stream().filter(keyInKeys -> keyInKeys.compareTo(key) > 0).forEach(Main.collection::remove);

    }

}
