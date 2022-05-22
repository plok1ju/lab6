package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.exceptions.ServerException;
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
    public void execute() throws ServerException {
        List<?> keys = collection.getKeysAsList();
        for (Object key : keys) {
            try {
                printable.printLine("/noresponse/=====");
                printable.printLine("/noresponse/Ключ элемента: " + key + "\n" + collection.get(key).toString());
                printable.printLine("/noresponse/=====");
            } catch (ServerException serverException) {
                throw serverException;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
