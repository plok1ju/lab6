package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.collection.HashTableCollection;
import server.commands.Save;
import server.manager.file.ReaderXml;
import server.model.Dragon;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static HashTableCollection<Integer, Dragon> collection = new HashTableCollection<>();
    public static List<Integer> freePorts = new ArrayList<>(50);
    public static String greeting = "Hello";

    public static void main(String[] args) throws Exception {
        ReaderXml readerXml = new ReaderXml();
        Logger log = LoggerFactory.getLogger(Main.class);
        try {
            collection = readerXml.returnCollect();
            log.info("Начало работы");
            log.info("Коллекция прочитана");
        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
            log.error("Проблемы с коллекцией или файлом");

        }
        ServerSocket[] serverSockets = new ServerSocket[50];
        ServerRunner[] serverRunners = new ServerRunner[50];
        Thread[] threads = new Thread[50];
        for (int i = 0; i < serverSockets.length; i++) {
            try {
                serverSockets[i] = new ServerSocket(5000 + i);
            } catch (Throwable t){
                System.out.println("Кажется порт " + 5000 + i + " занят");
                for (Thread thread : threads) {
                    try {
                        thread.interrupt();
                    } catch (Throwable ignore){}
                }
            }

            serverRunners[i] = new ServerRunner(serverSockets[i]);
            threads[i] = new Thread(serverRunners[i]);
            threads[i].start();
        }

        System.out.println("All work");

        while (true) {
            String s = new Scanner(System.in).nextLine();
            if (s.equals("exit")) {
                new Save(Main.collection).execute();
                log.info("Коллекция сохранена");
                System.out.println("Коллекция сохранена");
                log.info("Завершение работы");
                for (Thread thread : threads) {
                    thread.interrupt();
                }
                System.exit(0);
            }
            if (s.equals("save")){
                new Save(Main.collection).execute();
                log.info("Коллекция сохранена");
                System.out.println("Коллекция сохранена");
            }
        }
    }
}