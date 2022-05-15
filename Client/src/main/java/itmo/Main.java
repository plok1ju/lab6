package itmo;

import itmo.io.ClientPrinter;
import itmo.io.ClientReader;
import itmo.io.ConsoleScan;
import itmo.io.Scannable;
import itmo.manager.CommandsManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        SocketChannel clientSocketChannel = null;
        try {
            clientSocketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8181));
        } catch (Exception e){
            System.out.println("Cannot connect to the server");
            System.exit(1);
        }
        clientSocketChannel.configureBlocking(false);
        int ops = clientSocketChannel.validOps();
        SelectionKey selectionKey = clientSocketChannel.register(selector, ops, null);

        Scannable scannable = new ConsoleScan();
        CommandsManager commandsManager = new CommandsManager();
        ClientPrinter clientPrinter = new ClientPrinter(clientSocketChannel);
        ClientReader clientReader = new ClientReader(clientSocketChannel);
        getCommand(commandsManager, scannable, clientPrinter, clientReader);

        clientSocketChannel.close();
    }

    private static void getCommand(CommandsManager commandsManager, Scannable consoleScan, ClientPrinter clientPrinter, ClientReader clientReader) throws Exception {
        try {
            System.out.println("Введите команду: ");

            commandsManager.getCommand(consoleScan.scanString(), consoleScan, clientReader, clientPrinter, true);
//            commandsManager.receiveCommandInfo(commandString, clientReader, clientPrinter);

        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
        }
        getCommand(commandsManager, consoleScan, clientPrinter, clientReader);
    }

    private static void sendMessage(SocketChannel clientSocketChannel) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ClientPrinter clientPrinter = new ClientPrinter(clientSocketChannel);
        while (true){
            if (!scanner.hasNext()){
                System.exit(1);
            }
            String message = scanner.nextLine();
            clientPrinter.printLine(message);
            if (message.equals("exit"))
                break;
        }
    }
}
