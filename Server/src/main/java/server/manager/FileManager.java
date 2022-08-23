package server.manager;

import org.helper.exceptions.CollectionException;
import server.Main;
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
