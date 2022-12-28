package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ModifypswController {
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
    void OnButtonClickBack(ActionEvent event) {

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
                    return;
                }
            else if (newpsw.getText().isBlank() || mail.getText().isBlank() || confirmpsw.getText().isBlank())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Modify Error");
                    alert.setHeaderText("Compilare Tutti i campi");
                    alert.showAndWait();
                    return;
                }
            else if(r.next())
                {
                    DBHelper.update("UPDATE `utenti` SET `password` = \"" + confirmpsw.getText() + "\" WHERE `utenti`.`mail` = \"" + mail.getText() + "\"");
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Password Modify Confirmation");
                    alert.setHeaderText("Password Modificata con Successo!");
                    alert.showAndWait();
                    return;
                }
            else
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mail inserita inesistente");
                    alert.setHeaderText("CLútente a cui vuoi modificare la password non esiste");
                    alert.showAndWait();
                    return;
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
                    return;
                }
            else if (newpsw.getText().isBlank() || oldpsw.getText().isBlank() || confirmpsw.getText().isBlank())
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Password Modify Error");
                    alert.setHeaderText("Compilare Tutti i campi");
                    alert.showAndWait();
                    return;
                }
            else if(oldpassword.next())
                {

                    DBHelper.update("UPDATE `utenti` SET `password` = \"" + confirmpsw.getText() + "\" WHERE `utenti`.`id` = \"" + data.GetId() + "\"");
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Password Modify Confirmation");
                    alert.setHeaderText("Password Modificata con Successo!");
                    alert.showAndWait();
                    return;

                }
            else
                {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vecchia Password Errata");
                    alert.setHeaderText("La vecchia password (quella attuale) inserita non e' corrette \n Riprovare.");
                    alert.showAndWait();
                    return;
                }
        }

    }
public void initialize()
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
