package itmo.commands;

import itmo.collection.HashTableCollection;
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

    /**
     * Конструктор класса ReplaceIfLowe
     *
     * @param collection    - Поле collection
     * @param key           - Поле key
     * @param dragonBuilder - Поле dragonBuilder
     */
    public ReplaceIfLower(HashTableCollection<Integer, Dragon> collection, Integer key, DragonBuilder dragonBuilder) {
        this.collection = collection;
        this.key = key;
        this.dragonBuilder = dragonBuilder;
    }


    /**
     * Переопределение метода execute
     * Замена элемента, если значение нового элемента меньше старого
     */
    @Override
    public void execute() throws Exception {
        Dragon dragon = dragonBuilder.build();
        if (dragon.compareTo(collection.get(key)) < 0) {
            collection.remove(key);
            collection.put(key, dragon);
        }

    }
}
