package com.example.assegnamento;

import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiverTask extends Task<Void> {
    private Socket socket;
    private MyController controller;

    MessageReceiverTask(Socket socket, MyController controller) {
        this.socket = socket;
        this.controller = controller;
    }

    @Override
    protected Void call() throws Exception {
        // Ottieni gli stream di input dal socket
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (true) {
            // Legge il messaggio dal server
            String message = in.readLine();

            // Chiama la funzione handleMessage del controller nel thread principale
            javafx.application.Platform.runLater(() -> {
                controller.handleMessage(message);
            });
        }
    }
}

