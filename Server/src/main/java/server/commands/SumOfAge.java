package server.commands;

import server.collection.HashTableCollection;
import server.io.Printable;
import server.model.Ageable;

import java.util.List;

/**
 * Класс отвечает за вывод суммы полей age драконов
 */
public class SumOfAge implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ? extends Ageable> collection;
    private final Printable printable;

    /**
     * Поле sumAge
     */
    private Integer sumAge = 0;

    /**
     * Конструктор класса SumOfAge
     *
     * @param collection - Поле collection
     * @param printable
     */
    public SumOfAge(HashTableCollection<?, ? extends Ageable> collection, Printable printable) {
        this.collection = collection;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Вывод суммы полей age всех элементов коллекции
     */
    @Override
    public void execute() throws Exception {
        List<?> keys = collection.getKeysAsList();
        keys.forEach(key -> sumAge += (collection.get(key).getAge() == null ? 0 : collection.get(key).getAge()));
        printable.printLine("/Сумма возрастов: " + sumAge);

    }

}
