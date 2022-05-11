package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.exceptions.CollectionException;
import itmo.model.Dragon;
import itmo.model.builders.DragonBuilder;

/**
 * Класс отвечает за добавление нового элемента в коллекцию
 */
public class Insert implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<Integer, Dragon> collection;

    /**
     * Поле key
     */
    private final Integer key;

    /**
     * Поле dragon
     * {@link Dragon}
     */
    private final DragonBuilder dragonBuilder;

    /**
     * Конструктор класса Insert
     *
     * @param collection    - Поле collection
     * @param key           - Поле key
     * @param dragonBuilder - Поле dragonBuilder
     */
    public Insert(HashTableCollection<Integer, Dragon> collection, Integer key, DragonBuilder dragonBuilder) {
        this.collection = collection;
        this.key = key;
        this.dragonBuilder = dragonBuilder;
    }

    /**
     * Переопределение метода execute
     * Добавление элемента в коллекцию
     */
    @Override
    public void execute() throws Exception {
        if (collection.getKeysAsList().contains(key)) {
            throw new CollectionException("Элемент с таким key уже есть");
        }
        Dragon dragon = this.dragonBuilder.build();
        collection.put(key, dragon);

    }
}
