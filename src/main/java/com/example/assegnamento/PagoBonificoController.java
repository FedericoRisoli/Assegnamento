package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoBonificoController extends MyController {

    Data data=Data.getInstance();
    Carrello carrello = Carrello.getIstance();

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

    Alert alert;

    @FXML
    void OnClickIndietro(ActionEvent event) {
        Stage stage = (Stage) indietro.getScene().getWindow();
        stage.close();
    }

    String text;

    @FXML
    void OnClickPaga(ActionEvent event) throws SQLException{

        // create instance of the SimpleDateFormat that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //create instance of the Calendar class and set the date to the given date
        Calendar cal = Calendar.getInstance();

        // use add() method to add the days to the given date
        //Standard se ci sono i vini arrivano in 3 giorni
        String dateToday = sdf.format(cal.getTime());
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


        if (intestatario.getText().isBlank()) {
            errore.setText("Intestatario non valido");
            errore.setOpacity(1);
        } else if (iban.getLength()<27) {
            errore.setText("IBAN non valido");
            errore.setOpacity(1);
        } else {
            ResultSet r = DBHelper.query("SELECT * FROM `utenti` WHERE id = "+Integer.toString(data.GetId()));
            try {
                r.next();
            } catch (SQLException e) {
                //System.out.println("Wops, sembrerebbe che il tuo utente sia stato cancellato\n\n");
                //throw new RuntimeException(e);
            }

            DBHelper.update("INSERT INTO `ordinivendita` (`id`, `nome`, `cognome`,`Idcliente`, `ordine`, `indirizzo`,`dataordine`, `dataconsegna`, `completato`, `clienteCompletato`, `prezzo`) VALUES (NULL, '"+r.getString("nome")+"', '"+r.getString("cognome")+"','"+data.GetId()+"', '"+text+"', '"+r.getString("indirizzo")+"','"+dateToday+"' , '"+dateAfter+"', 1, 1,"+carrello.getTotale()+")");

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
            data.SetSuccess(true);
        }

    }
}
