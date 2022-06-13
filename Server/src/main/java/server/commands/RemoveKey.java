package server.commands;

import server.collection.HashTableCollection;
import server.exceptions.CollectionException;

/**
 * Класс отвечает за удаление элементов по значению ключа
 */
public class RemoveKey implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<? extends Integer, ?> collection;

    /**
     * Поле key
     */
    private final Integer key;

    /**
     * Конструктор класса RemoveKey
     *
     * @param collection - Поле collection
     * @param key        - Поле key
     */
    public RemoveKey(Integer key, HashTableCollection<? extends Integer, ?> collection) {
        this.collection = collection;
        this.key = key;
    }

    /**
     * Переопределение метода execute
     * Удаление элемента из коллекции по ключу
     */
    @Override
    public void execute() throws Exception {
        if (!collection.getKeysAsList().contains(key)) {
            throw new CollectionException("Нет такого key");
        }
        collection.remove(key);
    }


}
