package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;

//import com.example.assegnamento.DBHelper;

public class HelloController {

    @FXML
    private Label errorText;

    //@FXML
    //private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void onLoginButtonClick(ActionEvent event) throws SQLException {
        //qui funzione_login()

        ResultSet r = DBHelper.query("SELECT `username` FROM `utenti`WHERE `username` LIKE \""+username.getText()+"\" AND `password` LIKE \""+password.getText()+"\"");

        //in caso di errore
        if (!r.next())
        {
            errorText.setOpacity(1);
        }
        else{
            errorText.setOpacity(0.5);
        }


    }

}