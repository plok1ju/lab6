package server.utils;

import java.net.Socket;

public class ConnectionCheck {
    public static boolean isConnected(Socket socket){
        return socket.isConnected();
    }
}
