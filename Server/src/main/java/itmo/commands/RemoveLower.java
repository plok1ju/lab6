package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.io.Printable;
import itmo.io.Scannable;
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
    private final Scannable scannable;
    private final Printable printable;

    /**
     * Конструктор класса RemoveLower
     *
     * @param collection    - Поле collection
     * @param dragonBuilder - Поле dragonBuilder
     * @param scannable
     * @param printable
     */
    public RemoveLower(DragonBuilder dragonBuilder, HashTableCollection<Integer, Dragon> collection, Scannable scannable, Printable printable) {
        this.collection = collection;
        this.dragonBuilder = dragonBuilder;
        this.scannable = scannable;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Удаление элементов если они меньше поля dragon
     */
    @Override
    public void execute() throws Exception {
        List<Integer> keys = collection.getKeysAsList();
        Dragon dragon = dragonBuilder.build(scannable, printable);
        keys.stream().filter(key -> dragon.compareTo(collection.get(key)) > 0).forEach(collection::remove);

    }
}
