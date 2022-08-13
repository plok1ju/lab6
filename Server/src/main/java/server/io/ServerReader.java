package server.io;

import server.exceptions.ServerException;
import server.utils.CommandInfo;
import server.utils.ConnectionCheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerReader implements Scannable{

    private final BufferedReader bufferedInputStream;
    private final Socket clientSocket;
    private int lines = 0;

    public ServerReader(Socket clientSocket) throws Exception {
        bufferedInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.clientSocket = clientSocket;
    }
    @Override
    public CommandInfo scan() throws Exception {
        if (!ConnectionCheck.isConnected(clientSocket)){
            throw new ServerException();
        }
        ++lines;
        try {
            String string = bufferedInputStream.readLine();
            if (string == null){
                throw new ServerException();
            }
            return null;
        } catch (Exception e){

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
