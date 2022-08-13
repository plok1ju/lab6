package server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.Main;
import server.exceptions.CollectionException;

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
            Main.log.error("Не введена переменная окружения");
            throw new CollectionException("Не введена переменная окружения");
        }
        File file = new File(System.getenv("VAR"));
        return file;
    }


}
