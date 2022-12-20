package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

import javafx.scene.Node;


//import com.example.assegnamento.DBHelper; ??????? ci va o no?

public class HelloController {

    Stage stage;
    Scene scene;
    Parent root;

    @FXML
    private Label errorText;

    //@FXML
    //private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void OnClickOpenRegister(ActionEvent event) {


        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registrazione Cliente");
            stage.setScene(new Scene(root));
            //blocca finestra prima
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) throws SQLException, IOException {
        //qui funzione_login()

        ResultSet r = DBHelper.query("SELECT `username` FROM `utenti`WHERE `username` LIKE \""+username.getText()+"\" AND `password` LIKE \""+password.getText()+"\"");

        //in caso di errore
        if (!r.next())
        {
            errorText.setOpacity(1);
            return;
        }
        //cambio scena
        root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}