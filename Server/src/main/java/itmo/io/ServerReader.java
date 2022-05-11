package itmo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerReader implements Scannable{

    private SocketChannel socketChannel;
    private int lines = 0;

    public ServerReader(SocketChannel clientSocket) throws IOException {
        this.socketChannel = clientSocket;
    }
    @Override
    public String scanString() throws IOException {
        ++lines;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int size = socketChannel.read(byteBuffer);
        return new String(byteBuffer.array()).substring(0, size - 1);
    }

    @Override
    public boolean hasNextLine() throws IOException {
        return socketChannel.isOpen();
    }

    @Override
    public int linesCount() {
        return lines;
    }

    @Override
    public void close() throws IOException {
        socketChannel.close();
    }
}
