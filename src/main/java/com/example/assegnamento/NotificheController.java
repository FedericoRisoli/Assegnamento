package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificheController {
    Data data = Data.getInstance();
    @FXML
    private TableColumn<OrdiniVendita, CheckBox> select;

    @FXML
    private TableColumn<OrdiniVendita, String> consprev;

    @FXML
    private TableColumn<OrdiniVendita, String> ord;

    @FXML
    private TableColumn<OrdiniVendita, String> prezzo;

    @FXML
    private TableView<OrdiniVendita> notifiche;
    @FXML
    private void initialize() throws SQLException {
        select.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,CheckBox>("check"));
        ord.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Ordine"));
        consprev.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Dataconsegna"));
        prezzo.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Prezzo"));
        ResultSet r = DBHelper.query("SELECT * FROM `ordinivendita` WHERE `clienteCompletato`='0' AND `completato`='1' AND `Idcliente`=\""+data.GetId()+"\"");
        ObservableList<OrdiniVendita> tmp = FXCollections.observableArrayList();

            while (r.next())
            {
                tmp.add(new OrdiniVendita(r.getString("id") ,r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo"),r.getDouble("prezzo"),r.getString("Idcliente")));
                notifiche.setItems(tmp);
            }
    }

}
