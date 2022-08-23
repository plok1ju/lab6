package server.io;

import org.helper.CommandInfo;
import org.helper.ConnectionCheck;
import org.helper.Scannable;
import org.helper.exceptions.ServerException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerReader implements Scannable<CommandInfo> {

    private final ObjectInputStream inputStream;
    private final Socket clientSocket;

    public ServerReader(Socket clientSocket) throws Exception {
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
        this.clientSocket = clientSocket;
    }
    @Override
    public CommandInfo scan() throws Exception {
        if (!ConnectionCheck.isConnected(clientSocket)){
            throw new ServerException();
        }
        return (CommandInfo) inputStream.readObject();
    }


    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
