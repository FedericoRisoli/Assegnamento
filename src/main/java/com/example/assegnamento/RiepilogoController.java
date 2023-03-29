package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;


public class RiepilogoController extends MyController {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    Stage stage;
    Scene scene;
    Parent root;

    Carrello carrello = Carrello.getIstance();
    Data data=Data.getInstance();

    @FXML
    private TextField adrrfield;

    @FXML
    private ToggleGroup metodo;

    @FXML
    private ScrollPane recap;

    @FXML
    private TextFlow textflow;

    @FXML
    private Label label_prezzo;

    @FXML
    private Button procedi;

    @FXML
    private Button annulla;

    @FXML
    private RadioButton bonifico;

    @FXML
    private Button ordinanondisponibili;
    @FXML
    private ButtonBar pagbuttonsbar;

    @FXML
    private Label pagtext;
    @FXML
    private Line div1;
    @FXML
    private Label titletext;

    int year = Year.now().getValue();
    int month = Calendar.getInstance().get(Calendar.MONTH)+1;//+1 perchè ha base 0

    @FXML
    void OnClickAnulla(ActionEvent event) throws IOException {
            if(!data.role.equals("client"))
                logout();
            root = FXMLLoader.load(getClass().getResource("personnel.fxml"));
            if (data.Getrole().equals("client"))
                root = FXMLLoader.load(getClass().getResource("usermainpage.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }


    @FXML
    void OnClickProcedi(ActionEvent event) throws IOException  {

        // create instance of the SimpleDateFormat that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //create instance of the Calendar class and set the date to the given date
        Calendar cal = Calendar.getInstance();

        // use add() method to add the days to the given date
        //Standard se ci sono i vini arrivano in 3 giorni
        String dateToday = sdf.format(cal.getTime());
        //cal.add(Calendar.DAY_OF_MONTH, +3);
        //String dateAfter = sdf.format(cal.getTime());


        carrello.setTotale(Double.valueOf(label_prezzo.getText().substring(0,label_prezzo.getText().length()-2)));

        if(bonifico.isSelected()){
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("pagobonifico.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pagamento con Bonifico");
            stage.setScene(new Scene(root));

            //blocca finestra prima
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if(!ordinanondisponibili.isVisible())
            {
                for(Vini vino: carrello.getCarrello())
                {
                    if(Integer.valueOf(vino.getQuantita())<30)
                    {
                        String text= vino.getNome()+" 15 "+15* vino.getPrezzo();
                        DBHelper.update("INSERT INTO `ordinivendita` (`id`, `nome`, `cognome`,`Idcliente`, `ordine`, `indirizzo`,`dataordine`, `dataconsegna`, `completato`, `clienteCompletato`, `prezzo`) VALUES (NULL, \" \", \" \",0 ,\""+text+"\" , \" \",\""+dateToday+"\", NULL, 0, 1, 0)");
                    }
                }
                if(!data.role.equals("client"))
                    logout();
                root = FXMLLoader.load(getClass().getResource("personnel.fxml"));
                if (data.Getrole().equals("client"))
                    root = FXMLLoader.load(getClass().getResource("usermainpage.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(data.GetSuccess()==true) {
                procedi.setVisible(false);
                data.SetSuccess(false);
            }
        }
        else {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("pagocarta.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pagamento con Carta di Credito");
            stage.setScene(new Scene(root));

            //blocca finestra prima
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            for(Vini vino: carrello.getCarrello())
            {
                if(Integer.valueOf(vino.getQuantita())<30)
                {
                    String text= vino.getNome()+" 15 "+15* vino.getPrezzo();
                    DBHelper.update("INSERT INTO `ordinivendita` (`id`, `nome`, `cognome`,`Idcliente`, `ordine`, `indirizzo`,`dataordine`, `dataconsegna`, `completato`, `clienteCompletato`, `prezzo`) VALUES (NULL, \" \", \" \",0 ,\""+text+"\" , \" \",\""+dateToday+"\", NULL, 0, 1, 0)");
                }
            }

            if(!ordinanondisponibili.isVisible()) {
                if (!data.role.equals("client"))
                    logout();
                root = FXMLLoader.load(getClass().getResource("personnel.fxml"));
                if (data.Getrole().equals("client"))
                    root = FXMLLoader.load(getClass().getResource("usermainpage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else if(data.GetSuccess()==true) {
                procedi.setVisible(false);
                data.SetSuccess(false);
            }
        }

    }

    @FXML
    void OnClickOrdinaNonDisponibili(ActionEvent event) throws IOException, SQLException {


        // create instance of the SimpleDateFormat that matches the given date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //create instance of the Calendar class and set the date to the given date
        Calendar cal = Calendar.getInstance();

        // use add() method to add the days to the given date
        //Standard se ci sono i vini arrivano in 3 giorni
        String dateToday = sdf.format(cal.getTime());
        //cal.add(Calendar.DAY_OF_MONTH, +3);
        //String dateAfter = sdf.format(cal.getTime());


        Alert alert ;
        if(adrrfield.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Address Error");
            alert.setHeaderText("Inserire indirizzo di consegna");
            alert.showAndWait();
        }
        else
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Il tuo ordine e' stato ricevuto");
            alert.setHeaderText("Il tuo ordine e'stato inoltrato al personale");
            alert.showAndWait();

            if(!procedi.isVisible())
            {
                if(!data.role.equals("client"))
                    logout();
                root = FXMLLoader.load(getClass().getResource("personnel.fxml"));
                if (data.Getrole().equals("client"))
                    root = FXMLLoader.load(getClass().getResource("usermainpage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
                ordinanondisponibili.setVisible(false);
            int lunghezza = carrello.getOrdineNonDisp().size();
            int i = 0;
            double prezzo=0;
            String ordine="";
            ResultSet r;
            while(lunghezza>i)
            {
                try {
                    r = DBHelper.query("SELECT * FROM `wines` WHERE `id` ="+carrello.getOrdineNonDisp().get(i)+"");
                    r.next();
                    i++;
                    if(r.getString("qualita").equals("Alta"))
                        prezzo=50.00;
                    else if (r.getString("qualita").equals("Media")) {
                        prezzo=30.00;
                    }
                    else
                        prezzo=10.00;

                    prezzo+= r.getInt("vendite")/20;
                    prezzo=Math.round(prezzo * 100.0) / 100.0;

                    ordine+=r.getString("nome")+" "+carrello.getOrdineNonDisp().get(i)+" "+prezzo+"\n";
                    i++;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

            DBHelper.update("INSERT INTO `ordinivendita` (`id`, `nome`, `cognome`,`Idcliente`, `ordine`, `indirizzo`,`dataordine`, `dataconsegna`, `completato`, `clienteCompletato`) VALUES (NULL, \'"+data.Getname()+"\',\'"+data.Getsurname()+"\',"+data.GetId()+",\'"+ordine+"\',\'"+adrrfield.getText()+"\',\""+dateToday+"\",NULL,'0',0)" );
            r=DBHelper.query("SELECT * FROM `ordinivendita` WHERE id = (SELECT MAX(`id`) FROM `ordinivendita`)");
            r.next();
            sendMessage("ADDWORK"+(r.getInt("id")));
        }

    }

    public void logout(){
        System.out.println("Stage is closing");

        //ALTRE FUNZIONI UTILI PER LOGOUT QUI

        //NON chiudere mai socket
        //chiudo thread
        killChildThread();
    }


    @FXML
    private void initialize() throws IOException {
        if (!data.Getrole().equals("client"))
        {
            connect();
            createTask(server.getSocket(), this);
            Stage stage = (Stage) Stage.getWindows().get(0);
            stage.setOnCloseRequest(windowEvent -> logout());
        }

        double prezzo = 0;
        int bottiglie = 0;
        int casse6 = 0;
        int casse12 = 0;
        double subtotale = 0;
        double totale = 0;
        double tmp;

        textflow.getChildren().add(new Text("\t\nVINI NEL CARRELLO: \n\n"));
        for (Vini item : carrello.getCarrello()) {
            textflow.getChildren().add(new Text(item.getNome()));
            carrello.addOrdine(item.getNome());
            textflow.getChildren().add(new Text("\n"));
            //segno numero bottiglie
            bottiglie = (int) item.getSpin().getValue();
            carrello.addOrdine(Integer.toString(bottiglie));
            //segno numero casse da 12 e le tolgo da bottiglie
            //automaticamente viene arrotondato per difetto
            casse12 = bottiglie / 12;
            bottiglie = bottiglie - 12 * casse12;
            //segno numero casse da 6 e le tolgo da bottiglie
            //automaticamente viene arrotondato per difetto
            casse6 = bottiglie / 6;
            bottiglie = bottiglie - 6 * casse6;
            //System.out.println(casse6);

            prezzo = item.getPrezzo();
            subtotale = prezzo * bottiglie;
            textflow.getChildren().add(new Text("Confezione da " + bottiglie + " bottiglie, prezzo " + round(subtotale, 2) + " €\n"));
            //casse 6
            tmp = prezzo * 6 * 0.95 * casse6;
            textflow.getChildren().add(new Text(casse6 + " casse da 6 bottiglie, prezzo " + round(tmp, 2) + " €\n"));
            subtotale = subtotale + tmp;
            //casse12
            tmp = prezzo * 12 * 0.9 * casse12;
            textflow.getChildren().add(new Text(casse12 + " casse da 12 bottiglie, prezzo " + round(tmp,2) + " €\n"));
            subtotale = subtotale + tmp;
            if (casse6 + casse12 >= 3) {
                textflow.getChildren().add(new Text("Sconto casse multiple -3%, prezzo " + round(subtotale * 0.97 - subtotale, 2) + " €\n"));
                subtotale = round(subtotale * 0.97, 2);
            } else if (casse6 + casse12 == 2) {
                textflow.getChildren().add(new Text("Sconto casse multiple -2%, prezzo " + round(subtotale * 0.98 - subtotale, 2) + " €\n"));
                subtotale = round(subtotale * 0.98, 2);
            }
            totale = totale + subtotale;
            textflow.getChildren().add(new Text("\n\n"));
            carrello.addOrdine(Double.toString(subtotale));


        }
        if (!carrello.getNondisp().isEmpty())
            textflow.getChildren().add(new Text("\tVINI NON DISPONIBILI:\n\n"));
        for (Vini item : carrello.getNondisp()) {
            textflow.getChildren().add(new Text("Nome: "+item.getNome() + " Q.ta: "));
            textflow.getChildren().add(new Text("x "+item.getSpinnerValueString()+"\n"));
            //aggiungo nome e q.ta a lista di non disponibili per far l'ordine successivamente
            carrello.addOrdineNonDisp(Integer.toString(item.getId()));
            carrello.addOrdineNonDisp(item.getSpinnerValueString());


        }
        label_prezzo.setText(Double.toString(round(totale, 2)) + " €");


        if (carrello.getOrdine().isEmpty()) {
            procedi.setVisible(false);
            adrrfield.setVisible(false);
        }
        if (carrello.getNondisp().isEmpty()) {
            ordinanondisponibili.setVisible(false);
            adrrfield.setVisible(false);
        }
        else
        {
            //in alternativa si può usare la lista di vini carrello.getNondisp().getnome() e .getquantita()

           // System.out.println("LE INFORMAZIONI NECESSARIE SUI VINI SI TROVANO IN CARRELLO.GETNONDISP" +
             //       "LE INFO SONO REGISTRATE COME NOME VINO AGLI INDICI PARI E Q.TA AI DISPARI così\n" +
               //     carrello.getOrdineNonDisp());



            pagtext.setVisible(false);
            div1.setVisible(false);
            titletext.setText("Proposta di Acquisto:");
            adrrfield.setVisible(true);
        }
    }
}
