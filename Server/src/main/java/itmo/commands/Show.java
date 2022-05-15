package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.io.Printable;

import java.io.IOException;
import java.util.List;

/**
 * Класс отвечает за показ коллекции
 */
public class Show implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ?> collection;
    private final Printable printable;

    /**
     * Конструктор класса Show
     *
     * @param collection - Поле collection
     * @param printable
     */
    public Show(HashTableCollection<?, ?> collection, Printable printable) {
        this.collection = collection;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Вывод коллекции
     */
    @Override
    public void execute() {
        List<?> keys = collection.getKeysAsList();
        keys.forEach(key -> {
            try {
                printable.printLine("/noresponse/=====");
                printable.printLine("/noresponse/Ключ элемента: " + key + "\n" + collection.get(key).toString());
                printable.printLine("/noresponse/=====");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
