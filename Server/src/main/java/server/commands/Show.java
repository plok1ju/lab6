package server.commands;

import server.collection.HashTableCollection;
import server.exceptions.CollectionException;
import server.exceptions.ServerException;
import server.io.Printable;

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
    public void execute() throws ServerException, CollectionException {
        List<?> keys = collection.getKeysAsList();
        for (Object key : keys) {
            try {
                printable.printLine("=====");
                printable.printLine("Ключ элемента: " + key + "\n" + collection.get(key).toString());
                printable.printLine("=====");
            } catch (ServerException serverException) {
                throw serverException;
            } catch (Exception e) {
                throw new CollectionException("Что-то не так: " + e.getMessage());
            }
        }
    }

}
