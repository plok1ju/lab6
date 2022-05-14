package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.exceptions.CollectionException;
import itmo.model.Dragon;
import itmo.model.builders.DragonBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Класс отвечает за обновление полей элемента коллекции по id
 */
public class UpdateId implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<Integer, Dragon> collection;

    /**
     * Поле id
     */
    private final Long id;

    /**
     * Поле dragon
     * {@link DragonBuilder}
     */
    private final DragonBuilder dragonBuilder;

    /**
     * Конструктор класса UpdateId
     *
     * @param collection    - Поле collection
     * @param id            - Поле id
     * @param dragonBuilder - Поле dragonBuilder
     */
    public UpdateId(HashTableCollection<Integer, Dragon> collection, Long id, DragonBuilder dragonBuilder) {
        this.collection = collection;
        this.id = id;
        this.dragonBuilder = dragonBuilder;
    }

    /**
     * Переопределение метода execute
     * Обновление элемента по id
     */
    @Override
    public void execute() throws Exception {
        List<Integer> keys = collection.getKeysAsList();
        Stream<Integer> integerStream = keys.stream().filter(key -> id.equals(collection.get(key).getId()));
        Optional<Integer> optionalKey = integerStream.findAny();
        if (!optionalKey.isPresent()) {
            throw new CollectionException("Нет такого id");
        }
        Dragon dragon = dragonBuilder.build();
        dragon.setId(this.id);
        Integer dragonKey = optionalKey.get();
        keys.stream().filter(key -> id.equals(collection.get(key).getId())).forEach(collection::remove);

        collection.put(dragonKey, dragon);

    }
}
