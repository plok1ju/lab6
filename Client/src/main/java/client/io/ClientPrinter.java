package client.io;

import org.helper.CommandInfo;
import org.helper.Printable;
import org.helper.exceptions.ServerException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;

public class ClientPrinter implements Printable<CommandInfo> {

    private final SocketChannel socketChannel;
    private final ObjectOutputStream objectOutputStream;

    public ClientPrinter(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        objectOutputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
    }


    @Override
    public void print(CommandInfo commandInfo) throws Exception {
        try {
            objectOutputStream.writeObject(commandInfo);
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
