package com.example.assegnamento;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 848, 476);
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/168/168570.png"));
        stage.setTitle("Welcome to Wine Shop!");
        stage.setScene(scene);
        stage.setResizable(false); //make scenes Unresizables
        stage.show();

    }


    public static void main(String[] args)
    {
        launch();
    }
}