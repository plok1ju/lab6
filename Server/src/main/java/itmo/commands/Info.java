package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.io.Printable;

import java.io.IOException;

/**
 * Класс отвечает за вывод информации о коллекции
 */
public class Info implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ?> collection;
    private final Printable printable;


    /**
     * Конструктор класса Info
     *
     * @param collection - Поле collection
     * @param printable
     */
    public Info(HashTableCollection<?, ?> collection, Printable printable) {
        this.collection = collection;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Вывод информации о коллекции
     */
    public void execute() throws Exception {
        printable.printLine("/noresponse/Тип элемента коллекции: " + collection.getClass().getSimpleName() + "\n"
                + "Дата создания коллекции: " + collection.getDateTime() + "\n"
                + "Количество элементов: " + collection.size());
    }
}
