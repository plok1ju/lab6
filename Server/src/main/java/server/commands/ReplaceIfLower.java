package server.commands;

import server.Main;
import server.collection.HashTableCollection;
import server.io.Printable;
import server.io.Scannable;
import server.model.Dragon;
import server.model.builders.DragonBuilder;
import server.utils.Response;

import java.util.List;

/**
 * Класс отвечает за замену элемента по ключу
 * Если значение нового элемента меньше старого
 */
public class ReplaceIfLower extends Command {


    /**
     * Переопределение метода execute
     * Замена элемента, если значение нового элемента меньше старого
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        Dragon dragon = (Dragon) args.get(1);
        Integer key = (Integer) args.get(0);
        if (dragon.compareTo(Main.collection.get(key)) < 0) {
            Main.collection.replace(key, dragon);
        }

    }
}
