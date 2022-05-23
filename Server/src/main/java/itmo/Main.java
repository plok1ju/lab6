package itmo;

import itmo.collection.HashTableCollection;
import itmo.exceptions.ServerException;
import itmo.io.Scannable;
import itmo.io.ServerPrinter;
import itmo.io.ServerReader;
import itmo.manager.CommandsManager;
import itmo.manager.file.ReaderXml;
import itmo.model.Dragon;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static HashTableCollection<Integer, Dragon> collection = new HashTableCollection<>();
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        Socket client = serverSocket.accept();
        System.out.println("Connection");
        Scanner sc = new Scanner(System.in);
        Scannable serverReader = new ServerReader(client);
        ServerPrinter serverPrinter = new ServerPrinter(client);


        ReaderXml readerXml = new ReaderXml();
        try {
            collection = readerXml.returnCollect();
        } catch (Exception e) {
            //serverPrinter.printLine("Что-то пошло не так: " + e.getMessage());

        }

        CommandsManager commandsManager = new CommandsManager(collection);
        while (true){
            try {
                commandsManager.sendCommandInfo(serverReader, serverPrinter);
            } catch (ServerException serverException){
                client.close();
                client = serverSocket.accept();
                System.out.println("Connection");
                serverReader = new ServerReader(client);
                serverPrinter = new ServerPrinter(client);
            }
        }
    }
}