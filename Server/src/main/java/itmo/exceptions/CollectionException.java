package itmo.exceptions;

/**
 * Класс ошибки
 */
public class CollectionException extends Exception {

    /**
     * Пустой конструктор класса CollectionException
     */
    public CollectionException() {
    }

    /**
     * Конструктор класса CollectionException
     *
     * @param message - поле message
     */
    public CollectionException(String message) {
        super(message);
    }


}
