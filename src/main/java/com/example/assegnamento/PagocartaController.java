package com.example.assegnamento;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.sql.ResultSet;
import java.sql.SQLException;


import java.time.Year;

public class PagocartaController extends MyController {

    Carrello carrello = Carrello.getIstance();

    Data data=Data.getInstance();

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

    String text;

    Alert alert;


    @FXML
    void OnClickIndietro(ActionEvent event) {
        Stage stage = (Stage) indietro.getScene().getWindow();
        stage.close();
    }

    int year = Year.now().getValue();
    int month = Calendar.getInstance().get(Calendar.MONTH)+1;//+1 perchè ha base 0
    @FXML
    void OnClickPaga(ActionEvent event) throws SQLException{

        // create instance of the SimpleDateFormat that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //create instance of the Calendar class and set the date to the given date
        Calendar cal = Calendar.getInstance();

        // use add() method to add the days to the given date
        //Standard se ci sono i vini arrivano in 3 giorni
        cal.add(Calendar.DAY_OF_MONTH, +3);
        String dateAfter = sdf.format(cal.getTime());

        //trasformo dati in carrello.getOrdine() in stringa unica
        text="";
        int i=0;
        for(String item : carrello.getOrdine())
        {
            text=text + item+ " ";
            if (i==2)
            {text=text + "\n"; i=-1;}
            i++;
        }


        if(intestatario.getText().isBlank())
        {errore.setText("Intestatario non valido"); errore.setOpacity(1);}
        else if(ncarta.getText().isBlank()||ncarta.getText().length()!=16)
        {errore.setText("Numero Carta non valido"); errore.setOpacity(1);}
        else if(cvv.getText().isBlank()||cvv.getText().length()!=3)
        {errore.setText("CVV non valido"); errore.setOpacity(1);}
        else if(year>Integer.valueOf(anno.getValue())||(year==Integer.valueOf(anno.getValue()) && month>Integer.valueOf(mese.getValue())))
        {errore.setText("Scadenza non valida"); errore.setOpacity(1);}
        else{
            ResultSet r = DBHelper.query("SELECT * FROM `utenti` WHERE id = "+Integer.toString(data.GetId()));
            try {
                r.next();
            } catch (SQLException e) {
                System.out.println("Wops, sembrerebbe che il tuo utente sia stato cancellato\n\n");
                throw new RuntimeException(e);
            }

            DBHelper.update("INSERT INTO `ordinivendita` (`id`, `nome`, `cognome`, `ordine`, `indirizzo`, `dataconsegna`, `completato`, `clienteCompletato`, `prezzo`) VALUES (NULL, '"+r.getString("nome")+"', '"+r.getString("cognome")+"', '"+text+"', '"+r.getString("indirizzo")+"', '"+dateAfter+"', 1, 1,"+carrello.getTotale()+")");

            //aggiorno valori vini
            int q;
            int v;
            for(Vini item : carrello.getCarrello())
            {
                q = Integer.valueOf(item.getQuantita()) - (int) item.getSpin().getValue();
                v = Integer.valueOf(item.getVendite())  + (int) item.getSpin().getValue();
                DBHelper.update("UPDATE `wines` SET `quantita`="+q+", `vendite`="+v+" WHERE `id`="+item.getId());
            }

            //show info e chiudi
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pagamento andato a buon fine");
            alert.setHeaderText("Pagamento avvenuto e ordine processato!");
            alert.showAndWait();
            Stage stage = (Stage) indietro.getScene().getWindow();
            stage.close();
        }
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
