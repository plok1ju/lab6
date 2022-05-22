package itmo.io;

import itmo.Main;
import itmo.commands.Command;
import itmo.commands.Save;
import itmo.exceptions.CollectionException;
import itmo.exceptions.ServerException;
import itmo.utils.ConnectionCheck;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

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
            return bufferedInputStream.readLine();
        } catch (SocketException e){
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
