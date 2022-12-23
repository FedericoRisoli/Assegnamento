package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsermainpageController {

    Data data =Data.getInstance();
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
    private ListView<String> wine_list;

    @FXML
    private Button log_out;

    @FXML
    private Label error_text;

    @FXML
    private Button compra;

    //non finito
    @FXML
    void OnButtonClickBuy(ActionEvent event) {
        String selected = wine_list.getSelectionModel().getSelectedItem();
        selected = selected.replace(" IN OFFERTA! -25%", "");
        System.out.println(selected);
        //aprire pop-up per maggiori info sul vino e comprare
    }

    @FXML
    void OnButtonClickLogOut(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
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

        ResultSet r = DBHelper.query("SELECT * FROM `wines` WHERE nome LIKE \"%"+nome+"%\" AND anno = "+anno);

        //clear
        wine_list.getItems().clear();
        //popolo primo e check errori
        if(!r.next())
            {error_text.setOpacity(1); return;}
        if(r.getInt("promo")==1)
            wine_list.getItems().add(r.getString("nome")+" IN OFFERTA! -25%");
        else
            wine_list.getItems().add(r.getString("nome"));
        System.out.println("Risultato trovato");

        while(r.next())
        {
            if(r.getInt("promo")==1)
                wine_list.getItems().add(r.getString("nome")+" IN OFFERTA! -25%");
            else
                wine_list.getItems().add(r.getString("nome"));
            System.out.println("Risultato trovato");
        }
    }
    @FXML
    void OnModifyPSWButtonClick()
    {
        try {

            if(data.Getrole().equals("employee")) {
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("empmodifypsw.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Registrazione Cliente");
                stage.setScene(new Scene(root));
                //blocca finestra prima
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } else if (data.Getrole().equals("admin")) {
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("adminmodifypsw.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Registrazione Cliente");
                stage.setScene(new Scene(root));
                //blocca finestra prima
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initialize() throws SQLException {
        System.out.println("Richiedere Gli anni e aggiungerli a ComboBox annata");
        //funziona, bisogna allargare abbastanza la finestra altrimenti non si vede
        //get annate from DB
        ResultSet r = DBHelper.query("SELECT `anno` FROM `wines` ORDER BY 'anno' ASC");

        //tolgo duplicati
        List<Integer> anni = new ArrayList<>();
        int a;
        while(r.next())
        {
            a = r.getInt("anno");
            if(!anni.contains(a))
                {anni.add(a);annata.getItems().add(Integer.toString(a));}
        }
        if(data.Getrole().equals("client"))
        {
            modifypswbutton.setVisible(false);
        }

        //popolo ListView con offerte
        r = DBHelper.query("SELECT `nome`, `promo` FROM `wines` WHERE `promo`=1");
        while(r.next())
        {
            wine_list.getItems().add(r.getString("nome")+" IN OFFERTA! -25%");
        }
        //popolo ListView con resto
        r = DBHelper.query("SELECT `nome`, `promo` FROM `wines` WHERE `promo`=0");
        while(r.next())
        {
            wine_list.getItems().add(r.getString("nome"));
        }

    }

}
