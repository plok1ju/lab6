package itmo.io;

import itmo.exceptions.ServerException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientPrinter implements Printable{

    private SocketChannel socketChannel;

    public ClientPrinter(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
    }


    @Override
    public void printLine(String line) throws Exception {
        try {
            socketChannel.write(ByteBuffer.wrap(line.concat("\n").getBytes(StandardCharsets.UTF_8)));
            socketChannel.socket().getOutputStream().flush();
        } catch (Exception e){
            System.out.println("Server is broken");
            throw new ServerException();
        }
    }

    @Override
    public void close() throws IOException {
        socketChannel.close();
    }
}
