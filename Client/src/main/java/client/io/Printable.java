package client.io;

import server.exceptions.ServerException;

import java.io.IOException;

public interface Printable {
    void printLine(String line) throws IOException, ServerException, Exception;
    void close() throws IOException;
}
