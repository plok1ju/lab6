package server.commands;


import org.helper.Response;
import org.helper.comparators.CollectionComparator;
import org.helper.exceptions.ServerException;
import server.Main;
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
