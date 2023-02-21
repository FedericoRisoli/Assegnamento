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

public class NotificheController {
    Scene scene;
    Data data = Data.getInstance();
    Carrello carrello=Carrello.getIstance();
    double p ;

    @FXML
    private Button Accetta;

    @FXML
    private Button backbutton;
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
    private Label testfisso;

    @FXML
    private Label tot;
    @FXML
    private RadioButton rbon;

    @FXML
    private RadioButton rcar;


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


    public void OnButtonClickAccetta(ActionEvent actionEvent)
    {
        p=0;

        for (OrdiniVendita item : notifiche.getItems() )
        {
            if(item.getCheck().isSelected())
            {
                p += item.getPrezzo();

            }

        }
        carrello.setTotale(p);
        if(rbon.isSelected())
        {
            Parent root = null;
            try {
                root = FXMLLoader.load(HelloApplication.class.getResource("pagobonifico.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Pagamento con Bonifico");
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            //blocca finestra prima
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            try {
                root = FXMLLoader.load(getClass().getResource("usermainpage.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Parent root = null;
            try {
                root = FXMLLoader.load(HelloApplication.class.getResource("pagocarta.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setTitle("Pagamento con Carta di Credito");
            stage.setScene(new Scene(root));
            stage.setAlwaysOnTop(true);
            //blocca finestra prima
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();


        }

        //TODO if pagato andato bene cliente completato = 1 altrimenti no ***DA FARE***
        if(data.GetSuccess()==true)
        {
            Alert alert;
            for (OrdiniVendita item : notifiche.getItems() )
            {
                if(item.getCheck().isSelected())
                {
                    DBHelper.update("UPDATE `ordinivendita` SET clienteCompletato=1 WHERE id=\""+item.getId()+"\" AND Idcliente=\""+item.getIdcliente()+"\" ");
                    System.out.println("FATTO MMERDE");
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setHeaderText("YEEEEEEEEEEEEE");
                    alert.showAndWait();
                }

            }
        }
        data.SetSuccess(false);

    }
}
