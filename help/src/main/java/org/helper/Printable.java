package org.helper;


import java.io.IOException;

public interface Printable<T> {
    void print(T data) throws Exception;
    void close() throws IOException;
}
