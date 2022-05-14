package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.model.Ageable;

import java.util.List;

/**
 * Класс отвечает за вывод суммы полей age драконов
 */
public class SumOfAge implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private  HashTableCollection<?, ? extends Ageable> collection;

    /**
     * Поле sumAge
     */
    private Integer sumAge = 0;

    public SumOfAge(){}
    /**
     * Конструктор класса SumOfAge
     *
     * @param collection - Поле collection
     */
    public SumOfAge(HashTableCollection<?, ? extends Ageable> collection) {
        this.collection = collection;
    }

    /**
     * Переопределение метода execute
     * Вывод суммы полей age всех элементов коллекции
     */
    @Override
    public void execute() {
        List<?> keys = collection.getKeysAsList();
        keys.forEach(key -> sumAge += (collection.get(key).getAge() == null ? 0 : collection.get(key).getAge()));
        System.out.println("Сумма возрастов: " + sumAge);

    }

    public HashTableCollection<?, ? extends Ageable> getCollection() {
        return collection;
    }

    public void setCollection(HashTableCollection<?, ? extends Ageable> collection) {
        this.collection = collection;
    }

    public Integer getSumAge() {
        return sumAge;
    }

    public void setSumAge(Integer sumAge) {
        this.sumAge = sumAge;
    }
}
