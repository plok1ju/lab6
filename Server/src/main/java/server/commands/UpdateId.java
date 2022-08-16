package server.commands;

import server.Main;
import server.exceptions.CollectionException;
import server.model.Dragon;
import server.utils.Response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Класс отвечает за обновление полей элемента коллекции по id
 */
public class UpdateId extends Command {

    /**
     * Переопределение метода execute
     * Обновление элемента по id
     */
    @Override
    public void execute(List<Object> args, Response response) throws Exception {
        Long id = (Long) args.get(0);
        List<Integer> keys = Main.collection.getKeysAsList();
        Stream<Integer> integerStream = keys.stream().filter(key -> id.equals(Main.collection.get(key).getId()));
        Optional<Integer> optionalKey = integerStream.findAny();
        if (!optionalKey.isPresent()) {
            throw new CollectionException("Нет такого id");
        }
        Dragon dragon = (Dragon) args.get(1);
        dragon.setId(id);
        Integer dragonKey = optionalKey.get();
        keys.stream().filter(key -> id.equals(Main.collection.get(key).getId())).forEach(Main.collection::remove);

        Main.collection.put(dragonKey, dragon);

    }
}
