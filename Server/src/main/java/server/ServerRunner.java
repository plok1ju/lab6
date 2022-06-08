package server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.exceptions.ServerException;
import server.io.Scannable;
import server.io.ServerPrinter;
import server.io.ServerReader;
import server.manager.CommandsManager;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerRunner implements Runnable{
    private final ServerSocket serverSocket;

    public ServerRunner(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    Logger log = LoggerFactory.getLogger(ServerRunner.class);

    @Override
    public void run() {

        try {
            Socket client = serverSocket.accept();
            System.out.println("Connection");
            log.info("Получение нового подключения");
            Scanner sc = new Scanner(System.in);
            Scannable serverReader = new ServerReader(client);
            ServerPrinter serverPrinter = new ServerPrinter(client);
            serverPrinter.printLine(Main.greeting);

            CommandsManager commandsManager = new CommandsManager(Main.collection);
            while (true) {
                try {
                    commandsManager.sendCommandInfo(serverReader, serverPrinter);
                } catch (ServerException serverException) {
                    client.close();
                    client = serverSocket.accept();
                    System.out.println("Connection");
                    log.info("Получение нового подключения");
                    serverReader = new ServerReader(client);
                    serverPrinter = new ServerPrinter(client);
                    serverPrinter.printLine(Main.greeting);
                } catch (Exception e){
//                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            log.error("Ошибка при подключении клиента");
            throw new RuntimeException();
        }
    }
}
