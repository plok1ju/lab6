package itmo.io;

import java.io.IOException;

public class ConsolePrint implements Printable{
    @Override
    public void printLine(String line) throws Exception {
        System.out.println(line);
    }

    @Override
    public void close() throws IOException {

    }
}
