package itmo.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Этот класс помогает читать данные из файла
 */
public class FileScan implements Scannable {

    /**
     * Поле reader
     */
    private final BufferedReader reader;

    private int count;

    /**
     * Конструктор класса FileScan
     *
     * @param nameFile - значение поля nameFile
     */
    public FileScan(String nameFile) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(nameFile));
    }

    /**
     * Переопределение метода scanString
     *
     * @return - считанная строка из файла
     */
    @Override
    public String scanString() throws IOException {
        ++count;
        return reader.readLine();
    }

    /**
     * Метод проверяет читается ли файл
     *
     * @return - true - если читается, false - если нет
     */
    @Override
    public boolean hasNextLine() throws IOException {
        return reader.ready();
    }

    /**
     * Возвращает число строк
     *
     * @return count - возвращает count
     */
    @Override
    public int linesCount() {
        return count;
    }

}
