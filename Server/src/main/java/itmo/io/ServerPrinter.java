package itmo.io;

import itmo.Main;
import itmo.commands.Save;
import itmo.exceptions.ServerException;
import itmo.utils.ConnectionCheck;

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
            new Save(Main.collection).execute();
            throw new ServerException();
        }
        try {
            out.write(line.getBytes(StandardCharsets.UTF_8));
            out.flush();
        } catch (Exception e){
            new Save(Main.collection).execute();
            throw new ServerException();
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
