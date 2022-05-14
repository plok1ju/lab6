package itmo.comparators;

import itmo.model.Dragon;

import java.util.Comparator;

/**
 * Класс отвечающий за сравнение двух драконов
 */
public class DragonComparator implements Comparator<Dragon> {

    /**
     * Переопределение метода compare
     *
     * @return - одинаковые ли два дракона или нет
     */
    @Override
    public int compare(Dragon o1, Dragon o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    }
}
