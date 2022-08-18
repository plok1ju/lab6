package server.commands;

import org.helper.Response;
import server.Main;

import java.util.List;

/**
 * Класс отвечает за вывод суммы полей age драконов
 */
public class SumOfAge extends Command {

    /**
     * Переопределение метода execute
     * Вывод суммы полей age всех элементов коллекции
     */
    int sumAge = 0;
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        List<?> keys = Main.collection.getKeysAsList();

        keys.forEach(key -> sumAge += (Main.collection.get(key).getAge() == null ? 0 : Main.collection.get(key).getAge()));
        response.Add("/Сумма возрастов: " + sumAge);

    }

}
