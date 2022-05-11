package itmo.io;

import java.io.IOException;

public interface Printable {
    void printLine(String line) throws IOException;
    void close() throws IOException;
}
