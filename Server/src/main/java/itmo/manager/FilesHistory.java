package itmo.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс хранит файлы
 */
public class FilesHistory {

    /**
     * Объект класса FilesHistory
     */
    private static FilesHistory instance = null;

    /**
     * Список файлов
     */
    private final List<File> filePaths = new ArrayList<>();

    /**
     * Пустой конструктор FilesHistory
     */
    private FilesHistory() {
    }

    /**
     * Метод возвращает единственный объект класса FilesHistory
     *
     * @return instance - поле instance
     */
    public static FilesHistory getInstance() {
        if (instance == null) {
            instance = new FilesHistory();
        }
        return instance;
    }

    /**
     * Метод добавляет файл в список с файлами
     *
     * @param file - поле file
     */
    public void addHistory(File file) {
        filePaths.add(file);
    }

    /**
     * Метод проверяет есть ли файл в списке файлов
     *
     * @param file - поле file
     * @return existFile - есть ли файл в списке
     */
    public boolean containsFile(File file) {
        boolean existFile = filePaths.stream().anyMatch(file1 -> file1.getAbsolutePath().equals(file.getAbsolutePath()));
        return existFile;
    }

    /**
     * Метод удаляет файл из списка файлов
     *
     * @param file - поле file
     */
    public void removeFile(File file) {
        filePaths.removeIf(file1 -> file1.getAbsolutePath().equals(file.getAbsolutePath()));
    }
}
