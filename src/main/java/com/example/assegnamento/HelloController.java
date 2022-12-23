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

    Data data=Data.getInstance();

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
        int id=DBHelper.idgetter(username); //questo devo riuscirlo a passare in un altra scena idee?
        data.Setid(id);//setto l 'íd da usare in un altra scena es modifypsw
        data.Setusername(username.getText()); //setto lúsername da usare in una altra scena

        ResultSet r = DBHelper.query("SELECT `username` FROM `utenti`WHERE `username` LIKE \""+username.getText()+"\" AND `password` LIKE \""+password.getText()+"\"");


        //in caso di errore
        if (!r.next())
        {
            errorText.setOpacity(1);
            return;
        }
        ResultSet c = DBHelper.query("SELECT `ruolo` FROM `utenti` WHERE `username` LIKE \""+username.getText()+"\""); //role selection
        //cambio scena
        if(c.next())
        {

            String role = c.getString("ruolo");
            if(role.equals("employee")||role.equals("admin")) //if role is admin or employee go to page with more function
            {
                data.SetRole(role); //setto il ruolo da poter recuperare in un altra scena
                root = FXMLLoader.load(getClass().getResource("employeemainpage.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if (role.equals("client")) {
                data.SetRole(role);
                root = FXMLLoader.load(getClass().getResource("clientmainpage.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }


        }


    }


}