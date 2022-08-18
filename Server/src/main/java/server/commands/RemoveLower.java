package server.commands;

import org.helper.Response;
import org.helper.model.Dragon;
import server.Main;
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
