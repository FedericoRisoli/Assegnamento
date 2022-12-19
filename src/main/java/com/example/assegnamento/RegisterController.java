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
    private TextField cogmone;

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
    void OnClickRegister(ActionEvent event) {
        //ci sono campi bianchi? o la password è vuota?
        if ((usr.getText().isBlank())||(telefono.getText().isBlank())||(pass.getText().isEmpty())||(nome.getText().isBlank())||(indirizzo.getText().isBlank())||(cogmone.getText().isBlank())||(cfiscale.getText().isBlank())||(mail.getText().isBlank()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registrazione non completa");
            alert.setHeaderText("Compilare tutti i campi");
            alert.showAndWait();
            return;
        }
        

    }

}
