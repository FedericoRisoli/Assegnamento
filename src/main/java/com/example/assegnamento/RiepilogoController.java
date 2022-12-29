package com.example.assegnamento;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class RiepilogoController {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



    Carrello carrello = Carrello.getIstance();

    @FXML
    private ToggleGroup metodo;

    @FXML
    private ScrollPane recap;

    @FXML
    private TextFlow textflow;

    @FXML
    private Label label_prezzo;


    @FXML
    private void initialize() {

        double prezzo=0;
        int bottiglie = 0;
        int casse6 = 0;
        int casse12 = 0;
        double subtotale = 0;
        double totale = 0;
        double tmp;


        textflow.getChildren().add(new Text("VINI NEL CARRELLO: \n\n"));
        for (Vini item : carrello.getCarrello()) {
            textflow.getChildren().add(new Text(item.getNome()));
            textflow.getChildren().add(new Text("\n"));
            //segno numero bottiglie
            bottiglie = (int) item.getSpin().getValue();
            //segno numero casse da 12 e le tolgo da bottiglie
            //automaticamente viene arrotondato per difetto
            casse12 = bottiglie/12;
            bottiglie=bottiglie-12*casse12;
            //segno numero casse da 6 e le tolgo da bottiglie
            //automaticamente viene arrotondato per difetto
            casse6 = bottiglie/6;
            bottiglie=bottiglie-6*casse6;
            System.out.println(casse6);

            prezzo=item.getPrezzo();
            subtotale = prezzo*bottiglie;
            textflow.getChildren().add(new Text("Confezione da "+bottiglie+" bottiglie, prezzo "+round(subtotale,2)+" €\n"));
            //casse 6
            tmp=prezzo*6*0.95*casse6;
            textflow.getChildren().add(new Text(casse6+" casse da 6 bottiglie, prezzo "+round(tmp,2)+" €\n"));
            subtotale=subtotale+tmp;
            //casse12
            tmp=prezzo*12*0.9*casse12;
            textflow.getChildren().add(new Text(casse12+" casse da 12 bottiglie, prezzo "+tmp+" €\n"));
            subtotale=subtotale+tmp;
            if(casse6+casse12>=3)
            {
                textflow.getChildren().add(new Text("Sconto casse multiple -3%, prezzo "+round(subtotale*0.97-subtotale,2)+" €\n"));
                subtotale=round(subtotale*0.97,2);
            } else if (casse6+casse12==2) {
                textflow.getChildren().add(new Text("Sconto casse multiple -2%, prezzo "+round(subtotale*0.98-subtotale, 2)+" €\n"));
                subtotale=round(subtotale*0.98,2);
            }
            totale=totale+subtotale;
            textflow.getChildren().add(new Text("\n\n"));

        }
        label_prezzo.setText(Double.toString(round(totale,2))+" €");
    }
}
