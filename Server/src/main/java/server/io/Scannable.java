package server.io;

import server.utils.CommandInfo;

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
    CommandInfo scan() throws Exception;

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
    void close() throws IOException;
}
