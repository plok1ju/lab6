package client;

import client.io.ClientPrinter;
import client.io.ClientReader;
import client.io.ConsoleScan;
import client.manager.CommandsManager;
import org.helper.exceptions.ServerException;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {

        SocketChannel clientSocketChannel = null;
        int port;
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
        //clientSocketChannel.configureBlocking(false);


        ClientPrinter clientPrinter = new ClientPrinter(clientSocketChannel);
        ClientReader clientReader = new ClientReader(clientSocketChannel);

        CommandsManager commandsManager = new CommandsManager(clientPrinter, clientReader);

        System.out.println(clientReader.scan());
        ConsoleScan consoleScan = new ConsoleScan();

        while (true){
            try {
                System.out.println("Введите команду: ");
                commandsManager.getCommand(consoleScan.scan());

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
                commandsManager = new CommandsManager(clientPrinter, clientReader);
                System.out.println(clientReader.scan());
            }
            catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());

            }
        }
    }

}
