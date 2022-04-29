package itmo.collection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

/**
 * класс хранит коллекцию и время создания коллекции
 */
public class HashTableCollection<K, T> extends Hashtable<K, T> {

    /**
     * Поле время создания коллекции
     */
    private LocalDateTime dateTime = LocalDateTime.now();

    /**
     * Пустой конструктор класса HashTableCollection
     */
    public HashTableCollection() {
    }

    /**
     * Получение даты создания коллекции
     *
     * @return - значение поля dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Добавление даты создания коллекции
     *
     * @param dateTime - значение поля dateTime
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Получение списка всех ключей для параметров коллекции
     *
     * @return - возвращение коллекции типа ArrayList
     */
    public List<K> getKeysAsList() {
        return new ArrayList<K>(Collections.list(this.keys()));
    }
}
