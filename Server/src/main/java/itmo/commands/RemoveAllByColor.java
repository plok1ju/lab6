package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.model.Color;
import itmo.model.Colorable;

import java.util.List;

/**
 * Класс отвечает за удаление элементов по цвету
 */
public class RemoveAllByColor implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ? extends Colorable> collection;

    /**
     * Поле collection
     * {@link Color}
     */
    private final Color color;

    /**
     * Конструктор класса RemoveAllByColor
     *
     * @param collection - Поле collection
     * @param color      - Поле color
     */
    public RemoveAllByColor(HashTableCollection<?, ? extends Colorable> collection, Color color) {
        this.collection = collection;
        this.color = color;
    }

    /**
     * Переопределение метода execute
     * Удаление элементов из коллекции по цвету
     */
    @Override
    public void execute() {
        List<?> keys = collection.getKeysAsList();
        keys.stream().filter(key -> collection.get(key).getColor().equals(color)).forEach(collection::remove); // берет поток всех ключей, оставляет только нужные, и пробегается и удаляет
    }
}
