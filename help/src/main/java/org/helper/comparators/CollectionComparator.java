package org.helper.comparators;

import org.helper.HashTableCollection;
import org.helper.model.Dragon;

import java.util.Comparator;

/**
 * Класс отвечающий за сравнение объектов коллекции
 */
public class CollectionComparator implements Comparator<Integer> {


    private final HashTableCollection<Integer, Dragon> collection;

    /**
     * Конструктор класса CollectionComparator
     *
     * @param collection - поле collection
     */
    public CollectionComparator(HashTableCollection<Integer, Dragon> collection) {
        this.collection = collection;
    }

    /**
     * Переопределение метода compare
     *
     * @return - одинаковые ли два дракона или нет
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return new DragonComparator().compare(collection.get(o1), collection.get(o2));
    }
}
