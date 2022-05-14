package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.model.Dragon;
import itmo.model.builders.DragonBuilder;

import java.util.List;

/**
 * Класс отвечает за удаление элементов, меньших чем заданный
 */
public class RemoveLower implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<Integer, Dragon> collection;

    /**
     * Поле dragonBuilder
     * {@link DragonBuilder}
     */
    private final DragonBuilder dragonBuilder;

    /**
     * Конструктор класса RemoveLower
     *
     * @param collection    - Поле collection
     * @param dragonBuilder - Поле dragonBuilder
     */
    public RemoveLower(HashTableCollection<Integer, Dragon> collection, DragonBuilder dragonBuilder) {
        this.collection = collection;
        this.dragonBuilder = dragonBuilder;
    }

    /**
     * Переопределение метода execute
     * Удаление элементов если они меньше поля dragon
     */
    @Override
    public void execute() throws Exception {
        List<Integer> keys = collection.getKeysAsList();
        Dragon dragon = dragonBuilder.build();
        keys.stream().filter(key -> dragon.compareTo(collection.get(key)) > 0).forEach(collection::remove);

    }
}
