package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.*;




public class RegisterController {
    Data data=Data.getInstance();
    @FXML
    private ToggleGroup action;

    @FXML
    private RadioButton cancradio;
    @FXML
    private RadioButton regradio;

    @FXML
    private RadioButton resetradio;


    @FXML
    private ButtonBar ActionBar;

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
    void OnClickRegister(ActionEvent event) throws SQLException
    {

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
        if(data.Getrole().equals("admin"))
        {
            if(regradio.isSelected())
                {
                    //Registro Impiegato
                    DBHelper.update("INSERT INTO `utenti` VALUES (NULL,\"employee\", \"" + usr.getText() + "\" , \"" + pass.getText() + "\", \"" + nome.getText() + "\", \"" + cognome.getText() + "\" , \"" + cfiscale.getText() + "\", \"" + mail.getText() + "\", \"" + telefono.getText() + "\", \"" + indirizzo.getText() + "\") ");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Resitrazione Impiegato avvenuta ");
                    alert.setHeaderText("Registrazione Impiegato avvenuta con Successo");
                    alert.showAndWait();
                    Stage stage = (Stage) Indietro.getScene().getWindow();
                    stage.close();
                }
            else if (cancradio.isSelected())
                {
                  //aggiungere combobox/username selector
                }
            else if (resetradio.isSelected())
                {
                    //aggiungere combobox/username selector
                }
        }
        else
        {
            //Registrazione normale
            DBHelper.update("INSERT INTO `utenti` VALUES (NULL,\"client\", \"" + usr.getText() + "\" , \"" + pass.getText() + "\", \"" + nome.getText() + "\", \"" + cognome.getText() + "\" , \"" + cfiscale.getText() + "\", \"" + mail.getText() + "\", \"" + telefono.getText() + "\", \"" + indirizzo.getText() + "\") ");
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Resitrazione avvenuta ");
            alert.setHeaderText("Registrazione avvenuta con Successo");
            alert.showAndWait();
            Stage stage = (Stage) Indietro.getScene().getWindow();
            stage.close();
        }

    }


    public void initialize()
    {
        ActionBar.setVisible(false);
        if (data.Getrole()=="admin")
        {
            ActionBar.setVisible(true);
        }


    }



}