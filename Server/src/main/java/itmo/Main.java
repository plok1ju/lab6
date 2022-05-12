package itmo;
import itmo.io.ServerPrinter;
import itmo.io.ServerReader;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8181);
        Socket client = serverSocket.accept();
        System.out.println("Connection");
        InputStream in = client.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        OutputStream out = client.getOutputStream();
        PrintWriter printWriter = new PrintWriter(out);
        Scanner sc = new Scanner(System.in);
        while (true){
            /*ByteBuffer bb1 = ByteBuffer.allocate(10000);
            byte[] array1 = new byte[bb1.limit()];*/

            System.out.println(bufferedReader.readLine());
            System.out.println("Mes: ");
            String m = sc.nextLine();
            out.write(m.getBytes(StandardCharsets.UTF_8));
            //printWriter.println(m.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static void waitForInput(SocketChannel clientSocket) throws IOException {
        ServerReader serverReader = new ServerReader(clientSocket);
        ServerPrinter serverPrinter = new ServerPrinter(clientSocket);
        //clientSocket.configureBlocking(false);
        while (true) {
            if (serverReader.hasNextLine()) {
                String message = serverReader.scanString();
                System.out.println(message);
                serverPrinter.printLine(message.concat("_from_server"));
                if (message.equals("exit"))
                    break;
            }
        }
        serverReader.close();
    }
}