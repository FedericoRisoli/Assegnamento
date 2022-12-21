package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ModifypswController {

    @FXML
    private PasswordField confirmpsw;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField newpsw;

    @FXML
    private PasswordField oldpsw;

    @FXML
    void OnButtonClickBack(ActionEvent event) {

    }

    @FXML


    public void OnButtonModifyPSWClick(ActionEvent actionEvent) {
        Alert alert = null;
        //ce'un errore nella query da correggere ora devo andare a lavoro vedi se riesci a fixarlo (dobbiamo trovare un modo di salvarci líd di un utente loggato)

       /* if (newpsw != confirmpsw||newpsw.equals(oldpsw)||newpsw.getText().isBlank()||confirmpsw.getText().isBlank()||mail.getText().isBlank()||oldpsw.getText().isBlank())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Modify Error");
            alert.setHeaderText("Devi Compilare tutti i campi, la nuova mail e quella vecchia devono essere differente \n Controlla di aver scritto correttamente tutti i campi");
            alert.showAndWait();
            return;
        }
        else
        {
*/
        //    DBHelper.update("UPDATE `utenti` SET `password` = \""+newpsw.getText()+"\" WHERE `utenti`.`mail` = \""+mail.getText()+"\"" );
         //   System.out.println("modificafatta");
        }

}

