package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminModifypswController {
    Data data = Data.getInstance();


    @FXML
    private PasswordField confirmpsw;

    @FXML
    private TextField mail;


    @FXML
    private PasswordField newpsw;



    @FXML
    void OnButtonClickBack(ActionEvent event) {

    }

    @FXML


    public void OnButtonModifyPSWClick(ActionEvent actionEvent) {
        Alert alert = null;

        //da aggiungere (Controllare se l'utente che e' loggato e' l' admin allora puo modificare le psw altrui altrimenti solo la propria)
        //in questo momento si possono modificare tutte le psw da ***FIXARE***
        int id= data.GetId();

        if (!newpsw.getText().equals(confirmpsw.getText()))
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Modify Error");
            alert.setHeaderText("La nuova password e la password di conferma non coincidono");
            alert.showAndWait();
            return;
        }

        else if (newpsw.getText().isBlank()||mail.getText().isBlank()||confirmpsw.getText().isBlank())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Modify Error");
            alert.setHeaderText("Compilare Tutti i campi");
            alert.showAndWait();
            return;
        }
        else
        {

         DBHelper.update("UPDATE `utenti` SET `password` = \""+confirmpsw.getText()+"\" WHERE `utenti`.`mail` = \""+mail.getText()+"\"" );
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Password Modify Confirmation");
            alert.setHeaderText("Password Modificata con Successo!");
            alert.showAndWait();
            return;

        }

}
private void initialize(){

}
}

