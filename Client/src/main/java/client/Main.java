package client;

import client.io.ClientPrinter;
import client.io.ClientReader;
import client.manager.ClientCommandsManager;
import server.exceptions.ServerException;
import server.io.ConsoleScan;
import server.io.Scannable;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        SocketChannel clientSocketChannel = null;
        int port = 0;
        while (true) {
            System.out.println("Введите порт:");
            try {
                port = new Scanner(System.in).nextInt();

            } catch (Exception e) {
                System.out.println("Введите число");

                continue;
            }
            break;
        }
        try {
            clientSocketChannel = SocketChannel.open(new InetSocketAddress("localhost", port));
        } catch (Exception e){
            System.out.println("Cannot connect to the server");

            System.exit(1);
        }
        clientSocketChannel.configureBlocking(false);
        int ops = clientSocketChannel.validOps();
        SelectionKey selectionKey = clientSocketChannel.register(selector, ops, null);

        Scannable scannable = new ConsoleScan();
        ClientCommandsManager clientCommandsManager = new ClientCommandsManager();
        ClientPrinter clientPrinter = new ClientPrinter(clientSocketChannel);
        ClientReader clientReader = new ClientReader(clientSocketChannel);
        System.out.println(clientReader.scanString());
        ConsoleScan consoleScan = new ConsoleScan();

        while (true){
            try {
                System.out.println("Введите команду: ");
                clientCommandsManager.getCommand(consoleScan.scanString(), consoleScan, clientReader, clientPrinter, true);

            } catch (ServerException serverException){
                clientSocketChannel.close();
                while (true) {
                    try {
                        Thread.sleep(10000);
                        clientSocketChannel = SocketChannel.open(new InetSocketAddress("localhost", port));
                    } catch (Exception e) {
                        System.out.println("Cannot connect to the server");

                        continue;
                    }
                    break;
                }
                System.out.println("Connection restored!");

                clientSocketChannel.configureBlocking(false);
                clientPrinter = new ClientPrinter(clientSocketChannel);
                clientReader = new ClientReader(clientSocketChannel);
                System.out.println(clientReader.scanString());

            }
            catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());

            }
        }
    }

}
