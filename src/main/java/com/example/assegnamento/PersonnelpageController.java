package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonnelpageController extends MyController {


    Carrello carrello = Carrello.getIstance();

    Data data =Data.getInstance();
    //guardare https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
    //utilty
    @FXML
    private Button modifypswbutton;
    @FXML
    private Button log_out;
    @FXML
    private Label error_text;
    @FXML
    private Button GestioneDipButton;
    @FXML
    private Button ClearClient;

    @FXML
    private Button ClearOrder;

    @FXML
    private Button ClearWine;


    //tabella per ricerca cliente

    @FXML
    private Button searchclient;

    @FXML
    private TextField surnamefield;
    @FXML
    private TableView<Clienti> ClientTableView;
    @FXML
    private TableColumn<Clienti, String> t_clientmail;

    @FXML
    private TableColumn<Clienti, String> t_clientsurname;

    @FXML
    private TableColumn<Clienti, String> t_codicefiscale;

    @FXML
    private TableColumn<Clienti, String> t_indirizzo;

    @FXML
    private TableColumn<Clienti, String> t_name;
    @FXML
    private TableColumn<Clienti, String> t_telefono;
    @FXML
    private TableColumn<Clienti, String> t_usern;
    @FXML
    private Button ReportMensileButton;



    //tabella ricerca vini
    @FXML
    private TableView<Vini> tabella;
    @FXML
    private TableColumn<Vini, String> t_anno;
    @FXML
    private TableColumn<Vini, String> t_nome;
    @FXML
    private TableColumn<Vini, String> t_note;
    @FXML
    private TableColumn<Vini, Double> t_prezzo;
    @FXML
    private TableColumn<Vini, String> t_produttore;
    @FXML
    private TableColumn<Vini, Spinner<Integer>> t_qta;
    @FXML
    private TableColumn<Vini, CheckBox> t_selected;
    @FXML
    private TableColumn<Vini, String> t_vitigno;
    @FXML
    private TableColumn<Vini, String> t_provenienza;
    @FXML
    private ComboBox<String> annata;
    @FXML
    private TextField nome_vino;
    @FXML
    private Button search;
    @FXML
    private Button compra;


    //tabella ricerca ordini
    @FXML
    private DatePicker finaldate;

    @FXML
    private DatePicker initialdate;
    @FXML
    private TableView<OrdiniVendita> OrderTableView;
    @FXML
    private TableColumn<OrdiniVendita, String> t_dataordine;
    @FXML
    private TableColumn<OrdiniVendita, String> t_orderadd;

    @FXML
    private TableColumn<OrdiniVendita, String> t_ordername;

    @FXML
    private TableColumn<OrdiniVendita, String> t_ordersurname;

    @FXML
    private TableColumn<OrdiniVendita, String> t_ordine;
    @FXML
    private Button searchorder;

    //tabella per il lavoro
    @FXML
    private TableView<OrdiniVendita> lavoro;

    @FXML
    private TableColumn<OrdiniVendita, String> t_i;
    @FXML
    private TableColumn<OrdiniVendita, String>t_c;
    @FXML
    private TableColumn<OrdiniVendita, String> t_n;
    @FXML
    private TableColumn<OrdiniVendita, String> t_o;


    @FXML
    private DatePicker datepick;
    @FXML
    private TextField lavoro_text_prezzo;

    @FXML
    private Label text_lavoro_error;
    @FXML
    private Button skipButton;

    @FXML
    void OnButtonSkipClick(ActionEvent event){
        if(!lavoro.getItems().isEmpty())
        {
            String message="SKIP"+data.GetId()+" "+lavoro.getItems().get(0).getId();
            lavoro.getItems().clear();
            sendMessage(message);
        }
    }

    @FXML
    void OnButtonClickBuy(ActionEvent event) {
        carrello.clear();
        //logout();
        killChildThread();

        //separo disponibili e non
        ObservableList<Vini> lista = FXCollections.observableArrayList();
        ObservableList<Vini> listaNonDisp = FXCollections.observableArrayList();
        for (Vini item : tabella.getItems() )
        {
            if(item.getCheck().isSelected())
                if(item.getDisponibilita())
                    lista.add(item);
                else
                    listaNonDisp.add(item);
        }
        //FINE separo disponibili e non

        //apro nuova pagina per confermare l'ordine e pago
        System.out.println(lista);//console vedo disponibili
        //passo liste al prossimo controller
        carrello.setCarrello(lista);
        carrello.setNondisp(listaNonDisp);
        System.out.println(carrello.getCarrello());

        //se hai selezionato vini
        if ( !carrello.getCarrello().isEmpty() || !carrello.getNondisp().isEmpty() ) {
            //cambio scena
            Stage stage;
            Scene scene;
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("riepilogo.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void OnGestisciDipendentiClick(ActionEvent event)
    {
        try {
            data.SetRole("admin");
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registrazione Cliente");
            stage.setScene(new Scene(root));
            //blocca finestra prima
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void OnButtonClickLogOut(ActionEvent event) {

        logout();


        Stage stage;
        Scene scene;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            data.reset(); //resetta le info passate utile per fixare bug
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OnButtonClickSearch(ActionEvent event) throws SQLException
    {
        ResultSet r = null;
        String anno = "";
        anno=annata.getValue();
        String nome = nome_vino.getText();
        System.out.println(anno);
        System.out.println(nome);
        if(anno==null)
        {
             r= DBHelper.query("SELECT * FROM `wines` WHERE nome LIKE \"%"+nome+"%\""); //dipendenti e admin possono ricercare e/o
        }
        else if (nome.isEmpty())
        {
             r = DBHelper.query("SELECT * FROM `wines` WHERE `anno` LIKE \"%"+anno+"%\""); //dipendenti e admin possono ricercare e/o
        }
        else
        {
            r=DBHelper.query("SELECT * FROM `wines` WHERE anno LIKE \"%"+anno+"%\" AND nome LIKE \"%"+nome+"%\""); //dipendenti e admin possono ricercare e/o
        }

        //clear
        tabella.getItems().clear();
        //popolo
        ObservableList<Vini> tmp = FXCollections.observableArrayList();

        while(r.next())
        {
            tmp.add(new Vini(r.getInt("id"), r.getString("nome"), r.getString("produttore"), r.getString("provenienza"), r.getString("anno"), r.getString("vitigno"), r.getString("notetecniche"), r.getString("qualita"), r.getString("vendite"), r.getString("promo"),r.getString("quantita")));
        }
        tabella.setItems(tmp);


    }
    public void OnButtonClearWineClick(ActionEvent actionEvent) throws SQLException {
        t_nome.setCellValueFactory(new PropertyValueFactory<Vini, String>("Nome"));
        t_produttore.setCellValueFactory(new PropertyValueFactory<Vini, String>("p1"));
        t_provenienza.setCellValueFactory(new PropertyValueFactory<Vini, String>("p2"));
        t_anno.setCellValueFactory(new PropertyValueFactory<Vini, String>("a"));
        t_vitigno.setCellValueFactory(new PropertyValueFactory<Vini, String>("v"));
        t_note.setCellValueFactory(new PropertyValueFactory<Vini, String>("not"));
        t_selected.setCellValueFactory(new PropertyValueFactory<Vini, CheckBox>("check"));
        t_qta.setCellValueFactory(new PropertyValueFactory<Vini, Spinner<Integer>>("spin"));
        t_prezzo.setCellValueFactory(new PropertyValueFactory<Vini, Double>("prezzo"));

        ObservableList<Vini> tmp = FXCollections.observableArrayList();

        //popolo ListView
        ResultSet r = DBHelper.query("SELECT * FROM `wines` ORDER BY `promo` DESC");
        while(r.next())
        {
            tmp.add(new Vini(r.getInt("id"), r.getString("nome"), r.getString("produttore"), r.getString("provenienza"), r.getString("anno"), r.getString("vitigno"), r.getString("notetecniche"), r.getString("qualita"), r.getString("vendite"), r.getString("promo"),r.getString("quantita")));
            tabella.setItems(tmp);
        }
        nome_vino.clear();
        annata.setValue("");
    }
    public void OnButtonClickSearchClient(ActionEvent actionEvent) throws SQLException {
        String surname= surnamefield.getText();
        ResultSet r = DBHelper.query("SELECT * FROM `utenti` WHERE cognome LIKE \"%"+surname+"%\"");
        ClientTableView.getItems().clear();
        ObservableList<Clienti> tmp=FXCollections.observableArrayList();
        while(r.next())
        {
            tmp.add(new Clienti(r.getString("cognome"), r.getString("nome"), r.getString("username"), r.getString("mail"), r.getString("telefono"), r.getString("indirizzo"), r.getString("c_fiscale")));
            ClientTableView.setItems(tmp);
        }
    }
    public void OnClearClientButtonClick(ActionEvent actionEvent) throws SQLException {
        t_name.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Nome"));
        t_clientsurname.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Cognome"));
        t_usern.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Username"));
        t_clientmail.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Mail"));
        t_telefono.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Telefono"));
        t_indirizzo.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Indirizzo"));
        t_codicefiscale.setCellValueFactory(new PropertyValueFactory<Clienti,String>("CodiceFiscale"));

        ObservableList<Clienti> tmp2 = FXCollections.observableArrayList();
        ResultSet r = DBHelper.query("SELECT `cognome`, `nome`, `username`,`mail`,`telefono`,`indirizzo`,`c_fiscale` FROM `utenti` ORDER BY `cognome` DESC");
        while (r.next())
        {
            tmp2.add(new Clienti(r.getString("cognome"),r.getString("nome"),r.getString("username"),r.getString("mail"),r.getString("telefono"),r.getString("indirizzo"),r.getString("c_fiscale")));
            ClientTableView.setItems(tmp2);
        }
        surnamefield.clear();
    }
    @FXML
    void OnModifyPSWButtonClick()
    {
        try {
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("modifypsw.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Modifica password");
                stage.setScene(new Scene(root));
                //blocca finestra prima
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void OnButtonClickSearchOrder(ActionEvent actionEvent) throws SQLException {
        //TODO null?
        if(initialdate.getValue().isBefore(finaldate.getValue()))
        {
            String firstdate=initialdate.getValue().toString();
            String seconddate=finaldate.getValue().toString();
            ResultSet r=DBHelper.query("SELECT * FROM `ordinivendita` WHERE `dataordine` BETWEEN \""+firstdate+"\" AND \""+seconddate+"\"");
            OrderTableView.getItems().clear();
            ObservableList<OrdiniVendita> tmp3 = FXCollections.observableArrayList();

            while (r.next())
            {
                tmp3.add(new OrdiniVendita(r.getString("id"),r.getString("dataordine") ,r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo"),r.getDouble("prezzo"),r.getString("Idcliente")));
                OrderTableView.setItems(tmp3);
            }

        }
        else{
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Date Error");
            alert.setHeaderText("La Data iniziale dev'essere precedente a quella finale.\n Data1 prima di Data2");
            alert.showAndWait();
        }
    }
    @FXML
    void OnClearOrderButtonClick(ActionEvent event) throws SQLException {
        t_dataordine.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("DataOrdine"));
        t_ordername.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Nome"));
        t_ordersurname.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Cognome"));
        t_ordine.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Ordine"));
        t_orderadd.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Indirizzo"));
        ResultSet r = DBHelper.query("SELECT * FROM `ordinivendita` ORDER BY `dataconsegna`;");
        ObservableList<OrdiniVendita> tmp3 = FXCollections.observableArrayList();
        while (r.next())
        {
            tmp3.add(new OrdiniVendita(r.getString("id"),r.getString("dataordine") ,r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo"),r.getDouble("prezzo"),r.getString("Idcliente")));
            OrderTableView.setItems(tmp3);
        }

    }

    @FXML
    void OnClickGenerateReport(ActionEvent event) throws SQLException {
        Alert alert;
        String messaggio="";
        String projectPath = System.getProperty("user.dir");
        try {
            File myObj = new File("Report.txt");
            if (myObj.createNewFile()) {
                messaggio = "File created: " + myObj.getName()+"\nPath: "+projectPath;
            } else {
                messaggio = "File Sovrascritto.\nPath: "+projectPath;
            }
        } catch (IOException e) {
            messaggio = "An error occurred.";
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("Report.txt");

            /** Inizio REPORT*/

            ResultSet r= DBHelper.query("SELECT *, DATEDIFF (`dataconsegna` , NOW() ) AS n FROM `ordinivendita` WHERE `completato` = 1 AND `clienteCompletato` = 1 HAVING n<33");

            //introiti
            double introiti =0;
            while (r.next())
                introiti += r.getDouble("prezzo");
            myWriter.write("Introiti: "+introiti);

            //spese
            r=DBHelper.query("SELECT *, DATEDIFF (`dataconsegna` , NOW() ) AS n FROM `ordinivendita` WHERE `completato` = 1 HAVING n<33");
            double spese = 0;
            String ordine;
            String[] righe;
            String[] parole;
            while (r.next()) {
                ordine = r.getString("ordine");
                righe = ordine.split("/n");
                for(String riga : righe)
                {
                    if(riga.length()!=0)
                    {
                        riga=riga.substring(0,riga.length()-1);
                        parole=riga.split(" ");
                        spese += Double.valueOf(parole[parole.length-1]);
                    }
                }
            }
            //si fa *0.9 supponendo che il di comprare i vini al 90% del prezzo di vendita
            spese=roundToTwoDecimalPlaces(spese*0.9);
            myWriter.write("\nSpese: "+spese);

            //n. bottigle vendute
            int bottiglie_vendute=0;
            r=DBHelper.query("SELECT *, DATEDIFF (`dataconsegna` , NOW() ) AS n FROM `ordinivendita` WHERE `completato` = 1 AND `clienteCompletato` = 1 HAVING n<33");
            while (r.next()) {
                ordine = r.getString("ordine");
                righe = ordine.split("/n");
                for(String riga : righe)
                {
                    if(riga.length()!=0) {
                        riga = riga.substring(0, riga.length() - 1);
                        parole = riga.split(" ");
                        bottiglie_vendute += Integer.valueOf(parole[parole.length - 2]);
                    }
                }
            }
            myWriter.write("\nBottiglie vendute: "+bottiglie_vendute);

            //n. disponibili alla vendita
            r=DBHelper.query("SELECT * FROM `wines`");
            int disponibili=0;
            while(r.next())
                disponibili += r.getInt("vendite");
            myWriter.write("\nBottiglie disponibili: "+disponibili);

            //vendite per vino
            myWriter.write("\n\n---Vendite per vini---");
            r=DBHelper.query("SELECT * FROM `wines`");
            while(r.next())
                myWriter.write("\n"+r.getString("nome")+": "+r.getInt("vendite"));
            myWriter.write("\n---Fine Vendite per vini---\n");

            //valutazione dipendenti
            r=DBHelper.query("SELECT * FROM `utenti` WHERE `ruolo` LIKE \"employee\"");
            myWriter.write("\n\n---Valutazione Dipendenti---");

            while(r.next())
                myWriter.write("\n"+r.getString("username")+" job completati: "+r.getInt("job_completati")+", job falliti: "+r.getInt("job_falliti"));

            myWriter.write("\n---Fine Valutazione Dipendenti---");

            /** Fine REPORT*/
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        if (messaggio.equals("An error occurred"))
            alert = new Alert(Alert.AlertType.ERROR);
        else
            alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info Report");
        alert.setHeaderText(messaggio);
        alert.showAndWait();
    }

    public static double roundToTwoDecimalPlaces(double num) {
        return Math.round(num * 100.0) / 100.0;
    }


    @FXML
    public void OnButtonClickOrderComplete(ActionEvent actionEvent) {

        LocalDate data_consegna=datepick.getValue();
        LocalDate current_date = LocalDate.now();
        int comparason = current_date.compareTo(data_consegna);
        if(lavoro_text_prezzo.getText().endsWith(".")){
            lavoro_text_prezzo.setText(lavoro_text_prezzo.getText()+"00");
        }


        //check
        if((data_consegna==null)||(comparason>0))
            {text_lavoro_error.setText("Data scelta non valida"); text_lavoro_error.setVisible(true);}
        else if(lavoro_text_prezzo.getText().isEmpty())
            {text_lavoro_error.setText("Prezzo non valido"); text_lavoro_error.setVisible(true);}
        else
        {
            /**
             * aggiornare bottiglie
             */
            if(!lavoro.getItems().isEmpty()) {
                String ordine = lavoro.getItems().get(0).getOrdine();
                String[] righeMessaggio = ordine.split("\n");
                for (String riga : righeMessaggio) {
                    riga = riga.substring(0, riga.length() - 1);
                    String[] variabiliRiga = riga.split(" ");
                    int lunghezza = variabiliRiga.length;
                    String vino = "";

                    for (int n = 0; n < lunghezza - 2; n++)
                        vino = vino + " " + variabiliRiga[n];

                    vino = vino.substring(1);
                    System.out.println(vino);
                    ResultSet r = DBHelper.query("SELECT * FROM `wines` WHERE `nome` LIKE \"" + vino + "\"");
                    try {
                        r.next();
                        int qta = r.getInt("quantita");
                        int id = r.getInt("id");
                        qta += Integer.valueOf(variabiliRiga[lunghezza - 2]);
                        DBHelper.update("UPDATE `wines` SET `quantita` =\"" + qta + "\" WHERE id =\"" + id + "\"");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }


                text_lavoro_error.setVisible(false);
                DBHelper.update("UPDATE `ordinivendita` SET `dataconsegna` = \"" + datepick.getValue() + "\", `completato` = '1' , `prezzo` = " + lavoro_text_prezzo.getText() + " WHERE `id` = " + lavoro.getItems().get(0).getId());
                lavoro.getItems().clear();
                sendMessage("COMPLETATO" + data.GetId());
            }
        }

    }

    @Override
    public void handleMessage(String message) {
        System.out.println("IL MESSAGGIO ERA "+message);
        if (message.startsWith(String.valueOf(data.id)))
        {
            message = message.replace(data.id +" ","");
            System.out.println("IL MESSAGGIO è "+message);
            lavoro.getItems().clear();

            ResultSet r = DBHelper.query("SELECT * FROM `ordinivendita` WHERE `completato` LIKE 0 AND `id` LIKE \""+message+"\" ;");
            ObservableList<OrdiniVendita> tmp4 = FXCollections.observableArrayList();
            try {
                if(r.next())
                {
                    tmp4.add(new OrdiniVendita(r.getString("id"),r.getString("dataordine") ,r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo"),r.getDouble("prezzo"),r.getString("Idcliente")));
                    lavoro.setItems(tmp4);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (message.equals("TIMEOUT"))
        {
            String idlavoro;
            if (lavoro.getItems().isEmpty())
                idlavoro="-1";
            else
                idlavoro=lavoro.getItems().get(0).getId();
            lavoro.getItems().clear();
            sendMessage("TIMEDOUT"+data.GetId()+" "+idlavoro);
        }
    }


    public void logout(){
        System.out.println("Stage is closing");
        String message="LOGOUT_EMP"+data.GetId()+" ";
        System.out.println(message);
        //chiamo funzione sul server e dico chi sono
        //invio al server il lavoro da rimettere in coda
        if (lavoro.getItems().isEmpty())
            //nessun lavoro
            message = message+"-1";

        else
            //segno lavoro
            message = message+lavoro.getItems().get(0).getId();
        lavoro.getItems().clear();
        System.out.println(message);
        //mando messaggio
        sendMessage(message);
        System.out.println(message);

        //NON CHIUDERE SOCKET
        //chiudo thread
        killChildThread();
    }

    @FXML
    private void initialize() throws SQLException {
        connect();
        createTask(server.getSocket(),this);
        Stage stage = (Stage) Stage.getWindows().get(0);
        stage.setOnCloseRequest(windowEvent -> logout());

        GestioneDipButton.setVisible(false);



        //funziona, bisogna allargare abbastanza la finestra altrimenti non si vede
        //get annate from DB
        ResultSet r = DBHelper.query("SELECT `anno` FROM `wines` ORDER BY `anno` DESC");

        //tolgo duplicati
        List<Integer> anni = new ArrayList<>();
        int a;
        while (r.next()) {
            a = r.getInt("anno");
            if (!anni.contains(a)) {
                anni.add(a);
                annata.getItems().add(Integer.toString(a));
            }
        }
        if (data.Getrole().equals("admin")) {
            GestioneDipButton.setVisible(true);
            ReportMensileButton.setVisible(true);
        }

        t_nome.setCellValueFactory(new PropertyValueFactory<Vini, String>("Nome"));
        t_produttore.setCellValueFactory(new PropertyValueFactory<Vini, String>("p1"));
        t_provenienza.setCellValueFactory(new PropertyValueFactory<Vini, String>("p2"));
        t_anno.setCellValueFactory(new PropertyValueFactory<Vini, String>("a"));
        t_vitigno.setCellValueFactory(new PropertyValueFactory<Vini, String>("v"));
        t_note.setCellValueFactory(new PropertyValueFactory<Vini, String>("not"));
        t_selected.setCellValueFactory(new PropertyValueFactory<Vini, CheckBox>("check"));
        t_qta.setCellValueFactory(new PropertyValueFactory<Vini, Spinner<Integer>>("spin"));
        t_prezzo.setCellValueFactory(new PropertyValueFactory<Vini, Double>("prezzo"));

        ObservableList<Vini> tmp = FXCollections.observableArrayList();

        //popolo ListView
        r = DBHelper.query("SELECT * FROM `wines` ORDER BY `promo` DESC");
        while (r.next()) {
            tmp.add(new Vini(r.getInt("id"), r.getString("nome"), r.getString("produttore"), r.getString("provenienza"), r.getString("anno"), r.getString("vitigno"), r.getString("notetecniche"), r.getString("qualita"), r.getString("vendite"), r.getString("promo"), r.getString("quantita")));
            tabella.setItems(tmp);
        }

        t_name.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Nome"));
        t_clientsurname.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Cognome"));
        t_usern.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Username"));
        t_clientmail.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Mail"));
        t_telefono.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Telefono"));
        t_indirizzo.setCellValueFactory(new PropertyValueFactory<Clienti,String>("Indirizzo"));
        t_codicefiscale.setCellValueFactory(new PropertyValueFactory<Clienti,String>("CodiceFiscale"));

        ObservableList<Clienti> tmp2 = FXCollections.observableArrayList();
        r = DBHelper.query("SELECT * FROM `utenti` ORDER BY `cognome` DESC");
        while (r.next())
        {
            tmp2.add(new Clienti(r.getString("cognome"),r.getString("nome"),r.getString("username"),r.getString("mail"),r.getString("telefono"),r.getString("indirizzo"),r.getString("c_fiscale")));
            ClientTableView.setItems(tmp2);
        }

        t_dataordine.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("DataOrdine"));
        t_ordername.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Nome"));
        t_ordersurname.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Cognome"));
        t_ordine.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Ordine"));
        t_orderadd.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Indirizzo"));
        r = DBHelper.query("SELECT * FROM `ordinivendita` ORDER BY `dataconsegna`;");
        ObservableList<OrdiniVendita> tmp3 = FXCollections.observableArrayList();

        while (r.next())
        {
            tmp3.add(new OrdiniVendita(r.getString("id"),r.getString("dataordine"),r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo"),r.getDouble("prezzo"),r.getString("Idcliente")));
            OrderTableView.setItems(tmp3);
        }

        //mando messaggio
        //NON TOGLIERE QUESTO IF, FIXA UN BUG
        if(!data.role.equals("client"))
            sendMessage(data.role+data.GetId());


        t_n.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Nome"));
        t_c.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Cognome"));
        t_o.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Ordine"));
        t_i.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Indirizzo"));


        //add listener
        lavoro_text_prezzo.textProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica se il testo immesso dall'utente contiene solo caratteri numerici
            // Verifica se il testo immesso dall'utente contiene solo numeri e un solo punto decimale
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                lavoro_text_prezzo.setText(oldValue);
            }
        });

    }

    public void OnNotificheClick(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("notifiche.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Notifiche");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
