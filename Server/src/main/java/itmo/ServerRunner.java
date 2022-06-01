package itmo;

import itmo.exceptions.ServerException;
import itmo.io.Scannable;
import itmo.io.ServerPrinter;
import itmo.io.ServerReader;
import itmo.manager.CommandsManager;
import itmo.manager.file.ReaderXml;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static itmo.Main.collection;
import static itmo.Main.greeting;

public class ServerRunner implements Runnable{
    private final ServerSocket serverSocket;

    public ServerRunner(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    @Override
    public void run() {

        try {
            Socket client = serverSocket.accept();
            Main.freePorts.remove(Integer.valueOf(serverSocket.getLocalPort()));
            System.out.println("Connection");
            Scanner sc = new Scanner(System.in);
            Scannable serverReader = new ServerReader(client);
            ServerPrinter serverPrinter = new ServerPrinter(client);
            serverPrinter.printLine(greeting);

            CommandsManager commandsManager = new CommandsManager(collection);
            while (true) {
                try {
                    commandsManager.sendCommandInfo(serverReader, serverPrinter);
                } catch (ServerException serverException) {
                    client.close();
                    Main.freePorts.add(serverSocket.getLocalPort());
                    client = serverSocket.accept();
                    Main.freePorts.remove(Integer.valueOf(serverSocket.getLocalPort()));
                    System.out.println("Connection");
                    serverReader = new ServerReader(client);
                    serverPrinter = new ServerPrinter(client);
                    serverPrinter.printLine(greeting);
                } catch (Exception e){
//                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }
}
