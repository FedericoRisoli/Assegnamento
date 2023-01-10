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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonnelpageController extends MyController {

    //ottengo istance
    ServerConnection connection = ServerConnection.getInstance();


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
    private TableColumn<OrdiniVendita, String> t_dataconsegna;
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




    //non finito
    @FXML
    void OnButtonClickBuy(ActionEvent event) {
        ObservableList<Vini> lista = FXCollections.observableArrayList();
        for (Vini item : tabella.getItems() )
        {
            if(item.getCheck().isSelected())
                lista.add(item);
        }
        //apro nuova pagina per confermare l'ordine e pago
        System.out.println(lista);
        //passo lista al prossimo controller
        carrello.setCarrello(lista);
        System.out.println(carrello.getCarrello());

        //se il carrello non è vuoto
        if (!carrello.getCarrello().isEmpty()) {
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
        String anno = annata.getValue();
        String nome = nome_vino.getText();

        ResultSet r = DBHelper.query("SELECT * FROM `wines` WHERE nome LIKE \"%"+nome+"%\" OR anno = "+anno); //dipendenti e admin possono ricercare e/o

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
        if(initialdate.getValue().isBefore(finaldate.getValue()))
        {
            String firstdate=initialdate.getValue().toString();
            String seconddate=finaldate.getValue().toString();
            ResultSet r=DBHelper.query("SELECT `dataconsegna`,`nome`,`cognome`,`ordine`,`indirizzo` FROM `ordinivendita` WHERE `dataconsegna` BETWEEN \""+firstdate+"\" AND \""+seconddate+"\"");
            OrderTableView.getItems().clear();
            ObservableList<OrdiniVendita> tmp3 = FXCollections.observableArrayList();

            while (r.next())
            {
                tmp3.add(new OrdiniVendita(r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo")));
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
        t_dataconsegna.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Dataconsegna"));
        t_ordername.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Nome"));
        t_ordersurname.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Cognome"));
        t_ordine.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Ordine"));
        t_orderadd.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Indirizzo"));
        ResultSet r = DBHelper.query("SELECT `dataconsegna`,`nome`,`cognome`,`ordine`,`indirizzo` FROM `ordinivendita` ORDER BY `dataconsegna`;");
        ObservableList<OrdiniVendita> tmp3 = FXCollections.observableArrayList();
        while (r.next())
        {
            tmp3.add(new OrdiniVendita(r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo")));
            OrderTableView.setItems(tmp3);
        }
    }
    @FXML
    private void initialize() throws SQLException {

        //mando messagggio di prova
        connection.sendMessage("Nuovo lavoro: " + "TEST");

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
        r = DBHelper.query("SELECT `cognome`, `nome`, `username`,`mail`,`telefono`,`indirizzo`,`c_fiscale` FROM `utenti` ORDER BY `cognome` DESC");
        while (r.next())
        {
            tmp2.add(new Clienti(r.getString("cognome"),r.getString("nome"),r.getString("username"),r.getString("mail"),r.getString("telefono"),r.getString("indirizzo"),r.getString("c_fiscale")));
            ClientTableView.setItems(tmp2);
        }

        t_dataconsegna.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Dataconsegna"));
        t_ordername.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Nome"));
        t_ordersurname.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Cognome"));
        t_ordine.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Ordine"));
        t_orderadd.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Indirizzo"));
        r = DBHelper.query("SELECT `dataconsegna`,`nome`,`cognome`,`ordine`,`indirizzo` FROM `ordinivendita` ORDER BY `dataconsegna`;");
        ObservableList<OrdiniVendita> tmp3 = FXCollections.observableArrayList();

        while (r.next())
        {
            tmp3.add(new OrdiniVendita(r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo")));
            OrderTableView.setItems(tmp3);
        }

    }





}
