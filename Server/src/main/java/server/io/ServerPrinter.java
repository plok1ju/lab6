package server.io;

import server.exceptions.ServerException;
import server.utils.ConnectionCheck;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerPrinter implements Printable{

    private OutputStream out;
    private Socket clientSocket;

    public ServerPrinter(Socket clientSocket) throws IOException {
        this.out = clientSocket.getOutputStream();
        this.clientSocket = clientSocket;
    }


    @Override
    public void printLine(String line) throws Exception {
        if (!ConnectionCheck.isConnected(clientSocket)){
            throw new ServerException();
        }
        try {
            out.write(line.concat("\n").getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (Exception e){
            throw new ServerException();
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
