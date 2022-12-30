package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PagoBonificoController {

    Data data=Data.getInstance();

    @FXML
    private Button indietro;

    @FXML
    private TextField intestatario;

    @FXML
    private TextField iban;

    @FXML
    private Button paga;

    @FXML
    private Label errore;


    @FXML
    void OnClickIndietro(ActionEvent event) {
        Stage stage = (Stage) indietro.getScene().getWindow();
        stage.close();
    }

    @FXML
    void OnClickPaga(ActionEvent event) {
        if (intestatario.getText().isBlank()) {
            errore.setText("Intestatario non valido");
            errore.setOpacity(1);
        } else if (iban.getLength()<27) {
            errore.setText("IBAN non valido");
            errore.setOpacity(1);
        } else {
            //TODO segnare ordini vendita
            //DBHelper.query("SELECT * FROM `utenti` WHERE id = "+Integer.toString(data.GetId()));
            //DBHelper.update("INSERT INTO `ordinivendita` (`id`, `nome`, `cognome`, `ordine`, `indirizzo`, `dataconsegna`) VALUES (NULL, '', '', '', '', '')"); //fixato cognome
            System.out.println("TUTTO OK");
        }
    }
}
