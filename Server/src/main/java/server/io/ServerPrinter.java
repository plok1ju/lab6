package server.io;

import server.exceptions.ServerException;
import server.utils.ConnectionCheck;
import server.utils.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerPrinter implements Printable{

    private final OutputStream out;
    private final Socket clientSocket;

    public ServerPrinter(Socket clientSocket) throws IOException {
        this.out = clientSocket.getOutputStream();
        this.clientSocket = clientSocket;
    }


    @Override
    public void print(Response response) throws Exception {
        if (!ConnectionCheck.isConnected(clientSocket)){
            throw new ServerException();
        }
        try {
        } catch (Exception e){
            throw new ServerException();
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
