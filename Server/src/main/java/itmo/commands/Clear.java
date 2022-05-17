package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.io.Printable;

import java.io.IOException;

/**
 * Класс отвечает за очищение коллекции
 */
public class Clear implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ?> collection; // ? - все равно какой тип
    private final Printable printable;

    /**
     * Конструктор класса Clear
     *
     * @param collection - Поле collection
     * @param printable
     */
    public Clear(HashTableCollection<?, ?> collection, Printable printable) {
        this.collection = collection;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Очищение коллекции
     */
    @Override
    public void execute() throws Exception {
        collection.clear();
        printable.printLine("/noresponse/Коллекция очищена");

    }
}
