package itmo.io;

import itmo.Main;
import itmo.commands.Save;
import itmo.exceptions.ServerException;
import itmo.utils.ConnectionCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerReader implements Scannable{

    private BufferedReader bufferedInputStream;
    private Socket clientSocket;
    private int lines = 0;

    public ServerReader(Socket clientSocket) throws Exception {
        bufferedInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.clientSocket = clientSocket;
    }
    @Override
    public String scanString() throws Exception {
        if (!ConnectionCheck.isConnected(clientSocket)){
            new Save(Main.collection).execute();
            throw new ServerException();
        }
        ++lines;
        try {
            String string = bufferedInputStream.readLine();
            if (string == null){
                throw new ServerException();
            }
            return string;
        } catch (Exception e){
            new Save(Main.collection).execute();
            throw new ServerException();
        }
    }

    @Override
    public boolean hasNextLine() throws IOException {
        return bufferedInputStream.ready();
    }

    @Override
    public int linesCount() {
        return lines;
    }

    @Override
    public void close() throws IOException {
        bufferedInputStream.close();
    }
}
