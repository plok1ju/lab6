package itmo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        SocketChannel clientSocketChannel = SocketChannel.open(new InetSocketAddress(8181));
        clientSocketChannel.write(ByteBuffer.wrap(String.valueOf("helllo").getBytes()));
        clientSocketChannel.close();
    }

    private static void sendMessage(Socket clientSocket) throws IOException {
        Scanner scanner = new Scanner(System.in);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        while (true){
            java.lang.String message = scanner.nextLine();
            out.println(message);
            if (message.equals("exit"))
                break;
        }
    }
}
