package client.io;

import org.helper.Response;
import org.helper.Scannable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

public class ClientReader implements Scannable<Response> {

    private final SocketChannel socketChannel;
    private final ObjectInputStream inputStream;
    public ClientReader(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        inputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
    }
    @Override
    public Response scan() throws IOException, ClassNotFoundException {
        return (Response) inputStream.readObject();
    }
    @Override
    public void close() throws IOException {
        socketChannel.close();
    }
}
