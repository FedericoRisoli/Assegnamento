package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImieiordiniController {
    Data data = Data.getInstance();

    @FXML
    private Button back;

    @FXML
    private TableColumn<OrdiniVendita, String> t_cognome;

    @FXML
    private TableColumn<OrdiniVendita, String> t_dataconsegna;

    @FXML
    private TableColumn<OrdiniVendita, String> t_indirizzo;

    @FXML
    private TableColumn<OrdiniVendita, String> t_nome;

    @FXML
    private TableColumn<OrdiniVendita, String> t_ordine;

    @FXML
    private TableView<OrdiniVendita> tabella;

    @FXML
    void OnButtonClickBack(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    }
    @FXML
    private void initialize() throws SQLException, IOException {
        ResultSet r;

        t_nome.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Nome"));
        t_cognome.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Cognome"));
        t_ordine.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Ordine"));
        t_indirizzo.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Indirizzo"));
        t_dataconsegna.setCellValueFactory(new PropertyValueFactory<OrdiniVendita,String>("Dataconsegna"));
        r = DBHelper.query("SELECT * FROM `ordinivendita` WHERE `nome` LIKE \""+data.Getname()+"\" AND `cognome` LIKE \""+data.Getsurname()+"\"  ORDER BY `dataconsegna`;");
        ObservableList<OrdiniVendita> tmp3 = FXCollections.observableArrayList();

        while (r.next())
        {
            tmp3.add(new OrdiniVendita( r.getString("id"),r.getString("dataconsegna"),r.getString("nome"),r.getString("cognome"),r.getString("ordine"),r.getString("indirizzo"),r.getDouble("prezzo"),r.getString("Idcliente")));
            tabella.setItems(tmp3);
        }

    }

}
