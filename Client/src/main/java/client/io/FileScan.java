package client.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Этот класс помогает читать данные из файла
 */
public class FileScan implements Scannable<String> {

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
    public String scan() throws IOException {
        ++count;
        return reader.readLine();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
