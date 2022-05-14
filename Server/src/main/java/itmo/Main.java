package itmo;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.collection.HashTableCollection;
import itmo.commands.Command;
import itmo.commands.Info;
import itmo.exceptions.CollectionException;
import itmo.io.Scannable;
import itmo.io.ServerPrinter;
import itmo.io.ServerReader;
import itmo.manager.CommandsManager;
import itmo.model.Dragon;
import itmo.utils.CommandInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, CollectionException {
//        Info command = new Info(null);
//        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(command));

        ServerSocket serverSocket = new ServerSocket(8181);
        Socket client = serverSocket.accept();
        System.out.println("Connection");
        Scanner sc = new Scanner(System.in);
        ServerReader serverReader = new ServerReader(client.getInputStream());
        ServerPrinter serverPrinter = new ServerPrinter(client.getOutputStream());
        CommandsManager commandsManager = new CommandsManager(null);
        while (true){
//            System.out.println(serverReader.scanString());
//            System.out.println("Mes: ");
//            String m = sc.nextLine();
//            serverPrinter.printLine(m);
            commandsManager.sendCommandInfo(serverReader, serverPrinter);
        }
    }
}