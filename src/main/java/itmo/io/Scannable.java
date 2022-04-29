package itmo.io;

import java.io.IOException;

/**
 * Интерфейс объекта, который возвращает строку
 */
public interface Scannable {

    /**
     * Возвращает считанную строку
     *
     * @return scanString
     */
    String scanString() throws IOException;

    /**
     * Возвращает наличие строки
     *
     * @return booleanStringExist
     */
    boolean hasNextLine() throws IOException;

    /**
     * Возвращает число строк
     *
     * @return countLines
     */
    int linesCount();
}
