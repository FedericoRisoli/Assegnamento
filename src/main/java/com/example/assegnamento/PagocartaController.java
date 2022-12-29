package com.example.assegnamento;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Year;
import java.util.Calendar;


public class PagocartaController {

    @FXML
    private ChoiceBox<String> anno;

    @FXML
    private TextField cvv;

    @FXML
    private Button indietro;

    @FXML
    private TextField intestatario;

    @FXML
    private ChoiceBox<String> mese;

    @FXML
    private TextField ncarta;

    @FXML
    private Button paga;

    @FXML
    private Label errore;


    @FXML
    void OnClickIndietro(ActionEvent event) {
        Stage stage = (Stage) indietro.getScene().getWindow();
        stage.close();
    }

    int year = Year.now().getValue();
    int month = Calendar.getInstance().get(Calendar.MONTH)+1;//+1 perchè ha base 0
    @FXML
    void OnClickPaga(ActionEvent event) {
        if(intestatario.getText().isBlank())
        {errore.setText("Intestatario non valido"); errore.setOpacity(1);}
        else if(ncarta.getText().isBlank()||ncarta.getText().length()!=16)
        {errore.setText("Numero Carta non valido"); errore.setOpacity(1);}
        else if(cvv.getText().isBlank()||cvv.getText().length()!=3)
        {errore.setText("CVV non valido"); errore.setOpacity(1);}
        else if(year>Integer.valueOf(anno.getValue())||(year==Integer.valueOf(anno.getValue()) && month>Integer.valueOf(mese.getValue())))
        {errore.setText("Scadenza non valida"); errore.setOpacity(1);}
        else
        {
            //TODO segnare ordini vendita
            System.out.println("TUTTO OK");}


    }


    @FXML
    private void initialize() {
        // force the field to be numeric only
        cvv.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cvv.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (cvv.getText().length() > 3) {
                    cvv.setText(cvv.getText().substring(0, 3));
                }
            }
        });
        // force the field to be numeric only
        ncarta.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    ncarta.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if (ncarta.getText().length() > 16) {
                    ncarta.setText(ncarta.getText().substring(0, 16));
                }
            }
        });

        mese.getItems().addAll("01","02","03","04","05","06","07","08","09","10","11","12");
        anno.getItems().addAll("2023","2024","2025","2026","2027","2028","2029","2030","2031","2032");
        mese.setValue("01");
        anno.setValue("2024");

    }

}
