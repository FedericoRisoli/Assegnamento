package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;




public class RegisterController {

    @FXML
    private Button Indietro;

    @FXML
    private Button Registrati;

    @FXML
    private TextField cfiscale;

    @FXML
    private TextField cognome;

    @FXML
    private TextField indirizzo;

    @FXML
    private TextField mail;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField telefono;

    @FXML
    private TextField usr;

    @FXML
    void OnClickClose(ActionEvent event) {
        Stage stage = (Stage) Indietro.getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnClickRegister(ActionEvent event) throws SQLException {

        ResultSet r = DBHelper.query("SELECT `username` FROM `utenti`WHERE `username` LIKE \""+usr.getText()+"\""); //query per controllo d username nel db
        Alert alert = null;
        //ci sono campi bianchi? o la password è vuota?
        if ((usr.getText().isBlank()) || (telefono.getText().isBlank()) || (pass.getText().isEmpty()) || (nome.getText().isBlank()) || (indirizzo.getText().isBlank()) || (cognome.getText().isBlank()) || (cfiscale.getText().isBlank()) || (mail.getText().isBlank())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registrazione non completa");
            alert.setHeaderText("Compilare tutti i campi");
            alert.showAndWait();
            return;
        }
        if (r.next())//controllo che l'username non sia gia' nel DB
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Username gia' Presente ");
            alert.setHeaderText("User name gia' in uso (Cambiare Username)");
            alert.showAndWait();
            return;

        }
        //Registro
        DBHelper.update("INSERT INTO `utenti` VALUES (NULL,\"client\", \""+usr.getText()+"\" , \""+pass.getText()+"\", \""+nome.getText()+"\", \""+cognome.getText()+"\" , \""+cfiscale.getText()+"\", \""+mail.getText()+"\", \""+telefono.getText()+"\", \""+indirizzo.getText()+"\") ");

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resitrazione avvenuta ");
        alert.setHeaderText("Registrazione avvenuta con Successo");
        alert.showAndWait();
        Stage stage = (Stage) Indietro.getScene().getWindow();
        stage.close();

    }

}
