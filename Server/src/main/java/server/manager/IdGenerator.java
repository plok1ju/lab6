package server.manager;

import java.util.Random;

/**
 * Этот класс генерирует id для объектов
 */
public class IdGenerator {

    /**
     * Поле random
     */
    private static final Random random = new Random();

    /**
     * Метод генерирует новый id
     *
     * @return - число типа Long
     */
    public static Long getId() {
        long randomNextLong = random.nextLong();
        return (randomNextLong <= 0 ? getId() : randomNextLong);
    }
}
