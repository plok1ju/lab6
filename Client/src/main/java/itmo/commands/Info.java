package itmo.commands;

import itmo.collection.HashTableCollection;

/**
 * Класс отвечает за вывод информации о коллекции
 */
public class Info implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<?, ?> collection;

    /**
     * Конструктор класса Info
     *
     * @param collection - Поле collection
     */
    public Info(HashTableCollection<?, ?> collection) {
        this.collection = collection;
    }

    /**
     * Переопределение метода execute
     * Вывод информации о коллекции
     */
    public void execute() {
        System.out.println("Тип элемента коллекции: " + collection.getClass().getSimpleName()); // getSimpleName() имя класса в простом представлении
        System.out.println("Дата создания коллекции: " + collection.getDateTime());
        System.out.println("Количество элементов: " + collection.size());

    }
}
