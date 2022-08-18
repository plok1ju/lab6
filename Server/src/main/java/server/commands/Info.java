package server.commands;

import org.helper.Response;
import server.Main;
import java.util.List;

/**
 * Класс отвечает за вывод информации о коллекции
 */
public class Info extends Command {

    /**
     * Переопределение метода execute
     * Вывод информации о коллекции
     */
    public void execute(List<Object> objects, Response response) throws Exception {
        try {
            response.Add("Тип элемента коллекции: " + Main.collection.getClass().getSimpleName() + "\n"
                    + "Дата создания коллекции: " + Main.collection.getDateTime() + "\n"
                    + "Количество элементов: " + Main.collection.size());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
