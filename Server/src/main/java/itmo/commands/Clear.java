package itmo.commands;

import itmo.collection.HashTableCollection;

/**
 * Класс отвечает за очищение коллекции
 */
public class Clear implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ?> collection; // ? - все равно какой тип

    /**
     * Конструктор класса Clear
     *
     * @param collection - Поле collection
     */
    public Clear(HashTableCollection<?, ?> collection) {
        this.collection = collection;
    }

    /**
     * Переопределение метода execute
     * Очищение коллекции
     */
    @Override
    public void execute() {
        collection.clear();
        System.out.println("Коллекция очищена");

    }


}
