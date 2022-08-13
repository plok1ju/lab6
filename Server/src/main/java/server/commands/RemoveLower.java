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
 * Класс отвечает за удаление элементов, меньших чем заданный
 */
public class RemoveLower extends Command {
    /**
     * Переопределение метода execute
     * Удаление элементов если они меньше поля dragon
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        List<Integer> keys = Main.collection.getKeysAsList();
        Dragon dragon = (Dragon) args.get(0);
        keys.stream().filter(key -> dragon.compareTo(Main.collection.get(key)) > 0).forEach(Main.collection::remove);

    }
}
