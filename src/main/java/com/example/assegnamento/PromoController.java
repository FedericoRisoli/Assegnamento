package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PromoController extends MyController {
    Carrello carrello =Carrello.getIstance();

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
    private TableColumn<Vini, String> t_vitigno;
    @FXML
    private TableColumn<Vini, String> t_provenienza;
    public Data data =Data.getInstance();

    public void initialize() throws SQLException {
        ResultSet r;
        data.SetPromo(false);
        t_nome.setCellValueFactory(new PropertyValueFactory<Vini, String>("Nome"));
        t_produttore.setCellValueFactory(new PropertyValueFactory<Vini, String>("p1"));
        t_provenienza.setCellValueFactory(new PropertyValueFactory<Vini, String>("p2"));
        t_anno.setCellValueFactory(new PropertyValueFactory<Vini, String>("a"));
        t_vitigno.setCellValueFactory(new PropertyValueFactory<Vini, String>("v"));
        t_note.setCellValueFactory(new PropertyValueFactory<Vini, String>("not"));


        t_prezzo.setCellValueFactory(new PropertyValueFactory<Vini, Double>("prezzo"));

        ObservableList<Vini> tmp = FXCollections.observableArrayList();


        //popolo ListView
        r = DBHelper.query("SELECT * FROM `wines` WHERE promo LIKE '1'");
        while(r.next())
        {
            tmp.add(new Vini(r.getInt("id"), r.getString("nome"), r.getString("produttore"), r.getString("provenienza"), r.getString("anno"), r.getString("vitigno"), r.getString("notetecniche"), r.getString("qualita"), r.getString("vendite"), r.getString("promo"),r.getString("quantita")));
            tabella.setItems(tmp);
        }
    }

    }


