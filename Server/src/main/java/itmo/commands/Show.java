package itmo.commands;

import itmo.collection.HashTableCollection;

import java.util.List;

/**
 * Класс отвечает за показ коллекции
 */
public class Show implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ?> collection;

    /**
     * Конструктор класса Show
     *
     * @param collection - Поле collection
     */
    public Show(HashTableCollection<?, ?> collection) {
        this.collection = collection;
    }

    /**
     * Переопределение метода execute
     * Вывод коллекции
     */
    @Override
    public void execute() {
        List<?> keys = collection.getKeysAsList();
        keys.forEach(key -> {
            System.out.println("=====");
            System.out.println("Ключ элемента: " + key + "\n" + collection.get(key).toString());
            System.out.println("=====");
        });


    }
}
