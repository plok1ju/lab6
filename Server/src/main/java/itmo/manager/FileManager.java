package itmo.manager;

import itmo.exceptions.CollectionException;

import java.io.File;

/**
 * Этот класс отвечает за работу с файлом xml
 */
public class FileManager {

    /**
     * Отдает файл xml для записи/чтения
     *
     * @return - переменная окружения
     */
    public File getFile() throws CollectionException {
        // VAR - переменная окружения
        if (System.getenv("VAR") == null) {
            throw new CollectionException("Не введена переменная окружения");
        }
        File file = new File(System.getenv("VAR"));
        return file;
    }


}
