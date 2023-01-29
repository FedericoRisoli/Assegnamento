package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class MyController {

    ServerConnection server=ServerConnection.getInstance();

    //inizializzazione nel controller figlio con createTask
    MessageReceiverTask task;

    Stage stage;
    Scene scene;
    Parent root;

    public void handleMessage(String message) {
        if (message.equals("Chiusura Thread Figlio, arrivederci"))
            System.out.println(message);
        else
            System.out.println("------\nERRORE:\nhandleMessage() non implementato\n guarda README\n Messaggio ricevuto:\n"+message+"\n------");
    }

    //da cancellare
    public void createTask(Socket socket, MyController controller){

        //stage.setOnCloseRequest(windowEvent -> logout(stage));
        this.task=new MessageReceiverTask(socket, controller);
        new Thread(task).start();
    }
    /*public void logout(Stage stage) {
        System.out.println("LOGOUT AVVENUTO SENZA AZIONI");
    }*/

    public void sendMessage(String message){
        server.sendMessage(message);
    }

    public void killChildThread(){
        task.stop();
        server.sendMessage("Chiusura Thread Figlio, arrivederci");
    }

    public void changeScene(String NomeScenaFXML, ActionEvent event){
        killChildThread();
        try {

            root = FXMLLoader.load(getClass().getResource(NomeScenaFXML));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}