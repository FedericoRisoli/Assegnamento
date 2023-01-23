package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ModifypswController extends MyController {
    Data data= Data.getInstance();

    @FXML
    private PasswordField confirmpsw;

    @FXML
    private TextField mail;

    @FXML
    private Text mailtext;

    @FXML
    private PasswordField newpsw;

    @FXML
    private PasswordField oldpsw;

    @FXML
    private Text oldpswtext;
    @FXML
    private Button Indietro;

    @FXML
    void OnButtonClickBack(ActionEvent event) {
        Stage stage = (Stage) Indietro.getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnButtonModifyPSWClick(ActionEvent event) throws SQLException {
        Alert alert ;
        ResultSet r,oldpassword;

        if (data.Getrole().equals("admin"))  //nel caso sia admin controlli
        {
            r= DBHelper.query("SELECT `mail` FROM `utenti` WHERE `mail` LIKE \""+mail.getText()+"\"");

            if (!newpsw.getText().equals(confirmpsw.getText()))
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Modify Error");
                    alert.setHeaderText("La nuova password e la password di conferma non coincidono");
                    alert.showAndWait();
                }
            else if (newpsw.getText().isBlank() || mail.getText().isBlank() || confirmpsw.getText().isBlank())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Modify Error");
                    alert.setHeaderText("Compilare Tutti i campi");
                    alert.showAndWait();
                }
            else if(r.next())
                {
                    DBHelper.update("UPDATE `utenti` SET `password` = \"" + confirmpsw.getText() + "\" WHERE `utenti`.`mail` = \"" + mail.getText() + "\"");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Modify SUCCESS");
                    alert.setHeaderText("Password Modificata con Successo!");
                    alert.showAndWait();
                    //chiudi popup
                    Stage stage = (Stage) Indietro.getScene().getWindow();
                    stage.close();
                }
            else
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mail inserita inesistente");
                    alert.setHeaderText("CLútente a cui vuoi modificare la password non esiste");
                    alert.showAndWait();
                }


        }
        else //nel caso in cui sia l' impiegato controlli
        {
            oldpassword=DBHelper.query("SELECT `mail` FROM `utenti` WHERE `password` LIKE \""+oldpsw.getText()+"\"");
            if (!newpsw.getText().equals(confirmpsw.getText()))
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Modify Error");
                    alert.setHeaderText("La nuova password e la password di conferma non coincidono");
                    alert.showAndWait();
                }
            else if (newpsw.getText().isBlank() || oldpsw.getText().isBlank() || confirmpsw.getText().isBlank())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Modify Error");
                    alert.setHeaderText("Compilare Tutti i campi");
                    alert.showAndWait();
                }
            else if(oldpassword.next())
                {
                    DBHelper.update("UPDATE `utenti` SET `password` = \"" + confirmpsw.getText() + "\" WHERE `utenti`.`id` = \"" + data.GetId() + "\"");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Password Modify SUCCESS");
                    alert.setHeaderText("Password Modificata con Successo!");
                    alert.showAndWait();
                    //chiudi popup
                    Stage stage = (Stage) Indietro.getScene().getWindow();
                    stage.close();

                }
            else
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vecchia Password Errata");
                    alert.setHeaderText("La vecchia password (quella attuale) inserita non e' corrette \n Riprovare.");
                    alert.showAndWait();
                }
        }

    }
    @FXML
    private void initialize()
    {
    if (data.Getrole().equals("employee"))
        {
            mail.setVisible(false);
            mailtext.setVisible(false);
        }
    else if(data.Getrole().equals("admin"))
        {
            oldpsw.setVisible(false);
            oldpswtext.setVisible(false);
        }

    }



}

