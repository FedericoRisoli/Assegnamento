package com.example.assegnamento;

public class MyController {
    ServerConnection canale =ServerConnection.getInstance();

    public void handleMessage(String message) {
        System.out.println("Ho ricevuto questo messaggio, ma non so che farne, usa override per implementare un metodo adeguato.\n Messaggio: "+message);
    }
}