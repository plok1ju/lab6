package org.helper;

import java.io.IOException;

/**
 * Интерфейс объекта, который возвращает строку
 */
public interface Scannable<T> {

    /**
     * Возвращает считанную строку
     *
     * @return scanString
     */
    T scan() throws Exception;

    /**
     * Возвращает наличие строки
     *
     * @return booleanStringExist
     */

    /**
     * Возвращает число строк
     *
     * @return countLines
     */
    void close() throws IOException;
}
