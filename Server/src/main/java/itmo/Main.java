package itmo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8181);
        Socket clientSocket = serverSocket.accept();
        System.out.println("duck");
        waitForInput(clientSocket);
    }

    private static void waitForInput(Socket clientSocket) throws IOException {
        while (true) {
            InputStream inputStream = clientSocket.getInputStream();
            int message = inputStream.read();
            System.out.println(message);
//            if (message.equals("exit"))
//                break;
        }
    }
}
