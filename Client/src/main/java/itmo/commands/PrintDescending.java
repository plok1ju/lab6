package itmo.commands;


import itmo.collection.HashTableCollection;
import itmo.comparators.CollectionComparator;
import itmo.model.Dragon;

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

    /**
     * Конструктор класса PrintDescending
     *
     * @param collection - Поле collection
     */
    public PrintDescending(HashTableCollection<Integer, Dragon> collection) {
        this.collection = collection;
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
            System.out.println("=====");
            System.out.println("Ключ элемента " + key + ": " + o);

        });
    }
}
