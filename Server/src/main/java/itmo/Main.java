package itmo;
import itmo.io.ServerPrinter;
import itmo.io.ServerReader;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8181));
        serverSocketChannel.configureBlocking(false);
        int ops = serverSocketChannel.validOps();
        SelectionKey selectionKey = serverSocketChannel.register(selector, ops, null);
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()){
                SelectionKey key = selectionKeyIterator.next();
                if (key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("Connection!");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                else if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    socketChannel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array()).trim());
                    socketChannel.write(ByteBuffer.wrap(new String("Response").getBytes()));
                }
                selectionKeyIterator.remove();
            }
        }
    }

    private static void waitForInput(SocketChannel clientSocket) throws IOException {
        ServerReader serverReader = new ServerReader(clientSocket);
        ServerPrinter serverPrinter = new ServerPrinter(clientSocket);
        //clientSocket.configureBlocking(false);
        while (true) {
            if (serverReader.hasNextLine()) {
                String message = serverReader.scanString();
                System.out.println(message);
                serverPrinter.printLine(message.concat("_from_server"));
                if (message.equals("exit"))
                    break;
            }
        }
        serverReader.close();
    }
}