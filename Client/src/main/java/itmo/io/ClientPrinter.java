package itmo.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientPrinter implements Printable{

    private SocketChannel socketChannel;

    public ClientPrinter(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
    }


    @Override
    public void printLine(String line) throws IOException {
        try {
            socketChannel.write(ByteBuffer.wrap(line.concat("\n").getBytes(StandardCharsets.UTF_8)));
            socketChannel.socket().getOutputStream().flush();
        } catch (Exception e){
            System.out.println("СЕРВЕР ЕБНУЛСЯ!!!!");
            System.exit(1);
        }
    }

    @Override
    public void close() throws IOException {
        socketChannel.close();
    }
}
