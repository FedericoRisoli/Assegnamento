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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UsermainpageController {


    Carrello carrello = Carrello.getIstance();

    Data data = Data.getInstance();
    //guardare https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
    @FXML
    private ComboBox<String> annata;
    @FXML
    private TextField nome_vino;
    @FXML
    private Button search;
    @FXML
    private Button modifypswbutton;

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
    private Button log_out;

    @FXML
    private Label error_text;

    @FXML
    private Button compra;

    @FXML
    private Button GestioneDipButton;

    @FXML
    private Button RicercaclientiButton;

    //non finito
    @FXML
    void OnButtonClickBuy(ActionEvent event) {

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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void OnButtonClickSearch(ActionEvent event) throws SQLException {
        String anno = annata.getValue();
        String nome = nome_vino.getText();

        ResultSet r = DBHelper.query("SELECT * FROM `wines` WHERE nome LIKE \"%" + nome + "%\" AND anno = " + anno);

        //clear
        tabella.getItems().clear();
        //popolo
        ObservableList<Vini> tmp = FXCollections.observableArrayList();

        while(r.next())
        {
            tmp.add(new Vini(r.getInt("id"), r.getString("nome"),r.getString("produttore"),r.getString("provenienza"), r.getString("anno"), r.getString("vitigno"), r.getString("notetecniche"),r.getString("qualita"), r.getString("vendite"), r.getString("promo"),r.getString("quantita")));
        }
        tabella.setItems(tmp);
    }


    @FXML
    private void initialize() throws SQLException {


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

        if (data.GetPromo() == true) {
            try {
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("promo.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Promo");
                stage.setScene(new Scene(root));
                stage.setAlwaysOnTop(true);
                stage.show();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
    r =DBHelper.query("SELECT * FROM `wines` ORDER BY `promo` DESC");
        while(r.next())
        {
            tmp.add(new Vini(r.getInt("id"), r.getString("nome"),r.getString("produttore"),r.getString("provenienza"), r.getString("anno"), r.getString("vitigno"), r.getString("notetecniche"),r.getString("qualita"), r.getString("vendite"), r.getString("promo"),r.getString("quantita")));
            tabella.setItems(tmp);
        }

    }

    public void OnRicercaClientiClick(ActionEvent actionEvent) {
    }
}
