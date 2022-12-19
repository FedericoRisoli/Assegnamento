package com.example.assegnamento;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.channels.ConnectionPendingException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle("Welcome to Wine Shop!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wineshop","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        launch();
    }
}