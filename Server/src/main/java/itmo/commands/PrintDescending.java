package itmo.commands;


import itmo.collection.HashTableCollection;
import itmo.comparators.CollectionComparator;
import itmo.io.Printable;
import itmo.model.Dragon;

import java.io.IOException;
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
    public void execute() {
        List<Integer> keys = collection.getKeysAsList();
        keys.sort(new CollectionComparator(collection).reversed()); // реверс ключей
        keys.forEach(key -> {
            Object o = collection.get(key);
            try {
                printable.printLine("/noresponse/=====\n");
                printable.printLine("/noresponse/Ключ элемента " + key + ": " + o + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
