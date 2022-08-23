package org.helper;

import java.net.Socket;

public class ConnectionCheck {
    public static boolean isConnected(Socket socket){
        return socket.isConnected();
    }
}
