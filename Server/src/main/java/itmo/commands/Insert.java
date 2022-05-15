package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.exceptions.CollectionException;
import itmo.io.Printable;
import itmo.io.Scannable;
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
    private final Scannable scannable;
    private final Printable printable;

    /**
     * Конструктор класса Insert
     *
     * @param key           - Поле key
     * @param dragonBuilder - Поле dragonBuilder
     * @param collection    - Поле collection
     */
    public Insert(Integer key, DragonBuilder dragonBuilder, HashTableCollection<Integer, Dragon> collection, Scannable scannable, Printable printable) {
        this.collection = collection;
        this.key = key;
        this.dragonBuilder = dragonBuilder;
        this.scannable = scannable;
        this.printable = printable;
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
        Dragon dragon = this.dragonBuilder.build(scannable, printable);
        collection.put(key, dragon);

    }
}
