package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.model.Dragon;
import itmo.model.builders.DragonBuilder;

/**
 * Класс отвечает за замену элемента по ключу
 * Если значение нового элемента меньше старого
 */
public class ReplaceIfLower implements Command {

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
     * Поле dragonBuilder
     * {@link DragonBuilder}
     */
    private final DragonBuilder dragonBuilder;
    private final Scannable scannable;
    private final Printable printable;

    /**
     * Конструктор класса ReplaceIfLowe
     *
     * @param collection    - Поле collection
     * @param key           - Поле key
     * @param dragonBuilder - Поле dragonBuilder
     * @param scannable
     * @param printable
     */
    public ReplaceIfLower(Integer key, DragonBuilder dragonBuilder, HashTableCollection<Integer, Dragon> collection, Scannable scannable, Printable printable) {
        this.collection = collection;
        this.key = key;
        this.dragonBuilder = dragonBuilder;
        this.scannable = scannable;
        this.printable = printable;
    }


    /**
     * Переопределение метода execute
     * Замена элемента, если значение нового элемента меньше старого
     */
    @Override
    public void execute() throws Exception {
        Dragon dragon = dragonBuilder.build(scannable, printable);
        if (dragon.compareTo(collection.get(key)) < 0) {
            collection.remove(key);
            collection.put(key, dragon);
        }

    }
}
