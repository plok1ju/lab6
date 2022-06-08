package server.io;

import java.io.IOException;

public class SelfPrint implements Printable{
    private String s = "";

    @Override
    public void printLine(String s) throws Exception {
        this.s = this.s.concat(s.concat("\n"));
    }

    @Override
    public void close() throws IOException {

    }

    public String getString() {
        return s;
    }
}
