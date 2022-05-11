package itmo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientReader implements Scannable{

    private SocketChannel socketChannel;
    private Selector selector;
    private int lines = 0;

    public ClientReader(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        selector = Selector.open();
        socketChannel.register(selector, socketChannel.validOps(), null);

    }
    @Override
    public String scanString() throws IOException {
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()){
                SelectionKey key = selectionKeyIterator.next();

                if (key.isReadable()){
                    System.out.println("ready");
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    ++lines;
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int size = socketChannel.read(byteBuffer);
                    if (size == -1 || size == 0)
                        return "";
                    return new String(byteBuffer.array()).trim();

                }
                selectionKeyIterator.remove();
            }
        }
    }

    @Override
    public boolean hasNextLine() throws IOException {
        return true;
    }

    @Override
    public int linesCount() {
        return lines;
    }

    @Override
    public void close() throws IOException {
        socketChannel.close();
    }
}
