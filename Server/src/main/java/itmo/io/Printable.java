package itmo.io;

import java.io.IOException;

public interface Printable {
    void printLine(String line) throws Exception;
    void close() throws IOException;
}
