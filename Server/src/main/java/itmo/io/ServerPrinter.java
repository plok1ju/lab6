package itmo.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ServerPrinter implements Printable{

    private OutputStream out;

    public ServerPrinter(OutputStream out) throws IOException {
        this.out = out;
    }


    @Override
    public void printLine(String line) throws IOException {
        out.write(line.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
