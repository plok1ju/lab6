package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.manager.file.FileSaver;
import itmo.model.Dragon;

/**
 * Класс отвечает за сохранение коллекции в файл xml
 */
public class Save implements Command {

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<Integer, Dragon> collection;

    /**
     * Конструктор класса Save
     *
     * @param collection - Поле collection
     */
    public Save(HashTableCollection<Integer, Dragon> collection) {
        this.collection = collection;
    }

    /**
     * Переопределение метода execute
     * Сохранение коллекции в файл
     */
    @Override
    public void execute() throws Exception {
        FileSaver fileSaver = new FileSaver();
        fileSaver.saveInfo(collection);

    }
}
