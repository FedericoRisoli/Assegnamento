package com.example.assegnamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerConnection {
    private static ServerConnection instance;
    private Socket socket;
    private PrintWriter out;

    private ServerConnection() {
        // Crea un nuovo socket che si connette al server sulla porta specificata
        int port = 8080;
        try {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ServerConnection getInstance() {
        return new ServerConnection();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void close(){
        out.close();
    }

    public Socket getSocket() {
        return socket;
    }
}
