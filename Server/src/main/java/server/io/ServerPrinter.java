package server.io;

import org.helper.ConnectionCheck;
import org.helper.Printable;
import org.helper.Response;
import org.helper.exceptions.ServerException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerPrinter implements Printable<Response> {

    private final ObjectOutputStream out;
    private final Socket clientSocket;

    public ServerPrinter(Socket clientSocket) throws IOException {
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.clientSocket = clientSocket;
    }


    @Override
    public void print(Response response) throws Exception {
        if (!ConnectionCheck.isConnected(clientSocket)){
            throw new ServerException();
        }
        out.writeObject(response);
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
