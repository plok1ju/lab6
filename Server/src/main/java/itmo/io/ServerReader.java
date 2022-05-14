package itmo.io;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerReader implements Scannable{

    private BufferedReader bufferedInputStream;
    private int lines = 0;

    public ServerReader(InputStream in) throws IOException {
        bufferedInputStream = new BufferedReader(new InputStreamReader(in));
    }
    @Override
    public String scanString() throws IOException {
        ++lines;
        return bufferedInputStream.readLine();
    }

    @Override
    public boolean hasNextLine() throws IOException {
        return bufferedInputStream.ready();
    }

    @Override
    public int linesCount() {
        return lines;
    }

    @Override
    public void close() throws IOException {
        bufferedInputStream.close();
    }
}
