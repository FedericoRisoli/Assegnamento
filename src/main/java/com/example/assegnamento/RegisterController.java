package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RegisterController extends MyController {
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
    private ComboBox<String> userselector;


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
        if (cfiscale.getLength()>16||cfiscale.getLength()<16)//controllo che l'username non sia gia' nel DB
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Codice Fiscale non Valido");
            alert.setHeaderText("Codice fiscale di lunghezza errata");
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
        r = DBHelper.query("SELECT `c_fiscale` FROM `utenti` WHERE `c_fiscale` LIKE \""+cfiscale.getText()+"\""); //query per controllo del cod fisc nel db
        if (r.next())//controllo che l'username non sia gia' nel DB
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Codice Fiscale gia' Presente ");
            alert.setHeaderText("Codice fiscale gia' presente nel Database (Controllare correttezza)");
            alert.showAndWait();
            return;

        }
        if(data.Getrole()!=null)
        {
            if (data.Getrole().equals("admin"))
            {
                if (regradio.isSelected())
                {
                    if ((usr.getText().isBlank()) || (telefono.getText().isBlank()) || (pass.getText().isEmpty()) || (nome.getText().isBlank()) || (indirizzo.getText().isBlank()) || (cognome.getText().isBlank()) || (cfiscale.getText().isBlank()) || (mail.getText().isBlank()) )
                        {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Registrazione non completa");
                            alert.setHeaderText("Compilare tutti i campi");
                            alert.showAndWait();
                            return;
                        }
                    else
                        {
                            //Registro Impiegato
                            DBHelper.update("INSERT INTO `utenti` VALUES (NULL,\"employee\", \"" + usr.getText() + "\" , \"" + pass.getText() + "\", \"" + nome.getText() + "\", \"" + cognome.getText() + "\" , \"" + cfiscale.getText() + "\", \"" + mail.getText() + "\", \"" + telefono.getText() + "\", \"" + indirizzo.getText() + "\",0,0) ");
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Resitrazione Impiegato avvenuta ");
                            alert.setHeaderText("Registrazione Impiegato avvenuta con Successo");
                            alert.showAndWait();
                            Stage stage = (Stage) Indietro.getScene().getWindow();
                            stage.close();
                        }
                } else if (cancradio.isSelected())
                    {
                        DBHelper.execute("DELETE FROM `utenti` WHERE `username` LIKE \"" + userselector.getValue() + "\"");
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Cancellazione Impiegato avvenuta ");
                        alert.setHeaderText("Cancellazione Impiegato avvenuta con Successo");
                        alert.showAndWait();
                        Stage stage = (Stage) Indietro.getScene().getWindow();
                        stage.close();
                    }
                else if (resetradio.isSelected())
                {
                    //capire cosa fa reset
                    DBHelper.update("UPDATE `utenti` SET `password` = 'emp'  WHERE `utenti`.`username` = \"" + userselector.getValue() + "\"");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reset Credenziali");
                    alert.setHeaderText("Reset Credenziali avvenuto con Successo!");
                    alert.showAndWait();
                    Stage stage = (Stage) Indietro.getScene().getWindow();
                    stage.close();
                }
            }
        }
        else  //Registrazione normale
        {
            stage = (Stage) Indietro.getScene().getWindow();
            this.stage.setTitle("Registrazione Cliente");
            if ((usr.getText().isBlank()) || (telefono.getText().isBlank()) || (pass.getText().isEmpty()) || (nome.getText().isBlank()) || (indirizzo.getText().isBlank()) || (cognome.getText().isBlank()) || (cfiscale.getText().isBlank()) || (mail.getText().isBlank()))
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Registrazione non completa");
                    alert.setHeaderText("Compilare tutti i campi");
                    alert.showAndWait();
                }
            else
                {
                    DBHelper.update("INSERT INTO `utenti` VALUES (NULL,\"client\", \"" + usr.getText() + "\" , \"" + pass.getText() + "\", \"" + nome.getText() + "\", \"" + cognome.getText() + "\" , \"" + cfiscale.getText() + "\", \"" + mail.getText() + "\", \"" + telefono.getText() + "\", \"" + indirizzo.getText() + "\",0,0) ");
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resitrazione avvenuta ");
                alert.setHeaderText("Registrazione avvenuta con Successo");
                alert.showAndWait();
                Stage stage = (Stage) Indietro.getScene().getWindow();
                stage.close();
                }
        }

    }


    public void initialize() throws SQLException {
        ActionBar.setVisible(false);
        userselector.setVisible(false);
        if (data.Getrole()=="admin")
        {
            Registrati.setText("Registra");
            ActionBar.setVisible(true);
            regradio.setSelected(true);
        }
        ResultSet r = DBHelper.query("SELECT `username` FROM `utenti` WHERE ruolo LIKE 'employee' ORDER BY 'username' ASC");

        //tolgo duplicati
        List<String> usernames = new ArrayList<>();
        String a;
        while(r.next())
        {
            a = r.getString("username");
            if(!usernames.contains(a))
            {
                usernames.add(a);
                userselector.getItems().add(a);
            }
        }


    }


    public void Cancel(javafx.scene.input.MouseEvent mouseEvent)
    {
        userselector.setVisible(true);
        usr.setVisible(false);
        telefono.setVisible(false);
        nome.setVisible(false);
        cognome.setVisible(false);
        cfiscale.setVisible(false);
        pass.setVisible(false);
        mail.setVisible(false);
        indirizzo.setVisible(false);
        Registrati.setText("Cancella");

        usr.setDisable(true);
        telefono.setDisable(true);
        nome.setDisable(true);
        cognome.setDisable(true);
        cfiscale.setDisable(true);
        pass.setDisable(true);
        mail.setDisable(true);
        indirizzo.setDisable(true);
    }

    public void Reset(MouseEvent mouseEvent)
    {
        userselector.setVisible(true);
        indirizzo.setVisible(false);
        usr.setVisible(false);
        telefono.setVisible(false);
        nome.setVisible(false);
        cognome.setVisible(false);
        cfiscale.setVisible(false);
        pass.setVisible(false);
        mail.setVisible(false);
        Registrati.setText("Resetta");
    }

    public void registersel(MouseEvent mouseEvent) {
        userselector.setVisible(false);
        usr.setVisible(true);
        telefono.setVisible(true);
        nome.setVisible(true);
        cognome.setVisible(true);
        cfiscale.setVisible(true);
        pass.setVisible(true);
        mail.setVisible(true);
        indirizzo.setVisible(true);
        Registrati.setText("Registra");
        usr.setDisable(false);
        telefono.setDisable(false);
        nome.setDisable(false);
        cognome.setDisable(false);
        cfiscale.setDisable(false);
        pass.setDisable(false);
        mail.setDisable(false);
        indirizzo.setDisable(false);
    }
}