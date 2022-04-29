package itmo.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import itmo.collection.HashTableCollection;
import itmo.model.Dragon;
import itmo.model.KeyDragonPair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Этот класс записывает коллекцию в файл
 */
public class HashTableCollectSerializer {

    /**
     * Поле ключ и дракон
     * {@link KeyDragonPair}
     */
    private final KeyDragonPair[] keyDragonPairs;

    /**
     * Поле время создания коллекции
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime localDateTime;

    /**
     * Конструктор класса HashTableCollectSerializer
     *
     * @param hashTableCollection - значение поля hashTableCollection
     */
    public HashTableCollectSerializer(HashTableCollection<Integer, Dragon> hashTableCollection) {
        ArrayList<Integer> keys = Collections.list(hashTableCollection.keys());
        keyDragonPairs = new KeyDragonPair[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            keyDragonPairs[i] = new KeyDragonPair(keys.get(i), hashTableCollection.get(keys.get(i)));

        }
        this.localDateTime = hashTableCollection.getDateTime();
    }

    /**
     * Получение keyDragonPairs
     *
     * @return - значение поля keyDragonPairs
     */
    public KeyDragonPair[] getKeyDragonPairs() {
        return keyDragonPairs;
    }

    /**
     * Получение даты создания
     *
     * @return - значение поля localDateTime
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
