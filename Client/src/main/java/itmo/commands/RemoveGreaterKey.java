package itmo.commands;

import itmo.collection.HashTableCollection;

import java.util.List;

/**
 * Класс отвечает за удаление элементов по значению ключа
 * Если ключ превышает поле key
 */
public class RemoveGreaterKey implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private  HashTableCollection<? extends Integer, ?> collection;

    /**
     * Поле key
     */
    private  Integer key;

    public RemoveGreaterKey(){}
    /**
     * Конструктор класса RemoveGreaterKey
     *
     * @param collection - Поле collection
     * @param key        - Поле key
     */
    public RemoveGreaterKey(HashTableCollection<? extends Integer, ?> collection, Integer key) {
        this.collection = collection;
        this.key = key;
    }

    /**
     * Переопределение метода execute
     * Удаление элементов из коллекции если их ключ превышает поле key
     */
    @Override
    public void execute() {
        List<? extends Integer> keys = collection.getKeysAsList();
        keys.stream().filter(keyInKeys -> keyInKeys.compareTo(this.key) > 0).forEach(collection::remove);

    }

    public HashTableCollection<? extends Integer, ?> getCollection() {
        return collection;
    }

    public void setCollection(HashTableCollection<? extends Integer, ?> collection) {
        this.collection = collection;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
