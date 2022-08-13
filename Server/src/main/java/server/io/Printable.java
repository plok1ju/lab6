package server.io;

import server.utils.Response;

import java.io.IOException;

public interface Printable {
    void print(Response response) throws Exception;
    void close() throws IOException;
}
