package server.commands;


import server.collection.HashTableCollection;
import server.comparators.CollectionComparator;
import server.exceptions.ServerException;
import server.io.Printable;
import server.model.Dragon;

import java.util.List;

/**
 * Класс отвечает за вывод коллекции в порядке убывания
 */
public class PrintDescending implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<Integer, Dragon> collection;
    private final Printable printable;

    /**
     * Конструктор класса PrintDescending
     *
     * @param collection - Поле collection
     * @param printable
     */
    public PrintDescending(HashTableCollection<Integer, Dragon> collection, Printable printable) {
        this.collection = collection;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Вывод элементов коллекции
     */
    @Override
    public void execute() throws ServerException {
        List<Integer> keys = collection.getKeysAsList();
        keys.sort(new CollectionComparator(collection).reversed()); // реверс ключей
        for (Integer key : keys) {
            Object o = collection.get(key);
            try {
                printable.printLine("=====\n");
                printable.printLine("Ключ элемента " + key + ": " + o + "\n");
            } catch (ServerException serverException) {
                throw serverException;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
