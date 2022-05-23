package itmo.io;

import itmo.exceptions.CollectionException;

import java.io.IOException;

public class StringReader implements Scannable{

    private final String s;
    private String[] lines;
    private int linesCount = 0;

    public StringReader(String s) {
        this.s = s;
        lines = s.split("\n");
    }

    @Override
    public String scanString() throws CollectionException {
        if (!hasNextLine())
            throw new CollectionException("end of string");
        return lines[linesCount++];
    }

    @Override
    public boolean hasNextLine() {
        return linesCount < lines.length;
    }

    @Override
    public int linesCount() {
        return 0;
    }

    @Override
    public void close() throws IOException {
    }
}
