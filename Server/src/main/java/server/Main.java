package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.collection.HashTableCollection;
import server.commands.Save;
import server.io.Scannable;
import server.io.ServerPrinter;
import server.io.ServerReader;
import server.manager.CommandsManager;
import server.manager.file.FileSaver;
import server.manager.file.ReaderXml;
import server.model.Dragon;
import server.utils.CommandInfo;
import server.utils.Response;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static HashTableCollection<Integer, Dragon> collection = new HashTableCollection<>();
    public static String greeting = "Hello";
    public static Logger log = LoggerFactory.getLogger(Main.class);
    public static CommandsManager commandsManager;

    public static FileSaver fileSaver;

    public static void main(String[] args) throws Exception {
        ReaderXml readerXml = new ReaderXml();
        try {
            collection = readerXml.returnCollect();
            log.info("Начало работы");
            log.info("Коллекция прочитана");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
            log.error("Проблемы с коллекцией или файлом");
        }

        ServerSocket server = new ServerSocket(50);
        commandsManager = new CommandsManager();

        Thread recieveusersThread = new Thread(() -> {
            while (true){
                try {
                    Socket socket = server.accept();
                    new Thread(() -> ReadQuery(socket)).start();
                } catch (IOException e) {
                    System.out.println("юзер умер");
                }

            }
        });

        recieveusersThread.start();

        System.out.println("All work");

        while (true) {
            String s = new Scanner(System.in).nextLine();
            if (s.equals("exit")) {
                fileSaver.saveInfo(collection);
                log.info("Коллекция сохранена");
                System.out.println("Коллекция сохранена");
                log.info("Завершение работы");
                recieveusersThread.interrupt();
                System.exit(0);
            }
            if (s.equals("save")){
                fileSaver.saveInfo(collection);
                log.info("Коллекция сохранена");
                System.out.println("Коллекция сохранена");
            }
        }
    }


    public static void ReadQuery(Socket socket) {
        try {
            while (true) {
                System.out.println("Connection");
                log.info("Получение нового подключения");
                Scannable serverReader = new ServerReader(socket);
                ServerPrinter serverPrinter = new ServerPrinter(socket);
                serverPrinter.print(new Response().Add(Main.greeting));
                CommandInfo commandInfo = serverReader.scan();
                Response response = commandsManager.Execute(commandInfo.getCommandName(), commandInfo.getArgs());
                serverPrinter.print(response);
            }
        }
        catch (Exception e){

        }
    }
}