package itmo;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.collection.HashTableCollection;
import itmo.exceptions.CollectionException;
import itmo.io.Scannable;
import itmo.io.ServerPrinter;
import itmo.io.ServerReader;
import itmo.manager.CommandsManager;
import itmo.manager.file.ReaderXml;
import itmo.model.Dragon;

import java.io.IOException;
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
//            System.out.println(serverReader.scanString());
//            System.out.println("Mes: ");
//            String m = sc.nextLine();
//            serverPrinter.printLine(m);
            commandsManager.sendCommandInfo(serverReader, serverPrinter, true);
        }
    }
}