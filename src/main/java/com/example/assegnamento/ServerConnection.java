package com.example.assegnamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//TODO VEDERE SE ELIMINARLO O NO, al momento non serve, lo si potrebbe usare per mandare i messaggi

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
        if (instance == null) {
            instance = new ServerConnection();
        }
        return instance;
    }

    //per riceverlo devo modificare il server
    /*
    public void handleMessage(String message) {
        // Gestisci il messaggio inviato dal server
        // ...
    }
    */

    public void sendMessage(String message) {
        out.println(message);
    }
}
