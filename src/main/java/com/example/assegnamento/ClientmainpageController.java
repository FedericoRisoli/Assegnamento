package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientmainpageController {


    //guardare https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
    @FXML
    private ComboBox<String> annata;


    @FXML
    private TextField nome_vino;

    @FXML
    private Button search;

    @FXML
    void OnButtonClickSearch(ActionEvent event) {
    System.out.println("Client Search");
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
                {anni.add(a); annata.getItems().add(Integer.toString(a));}
        }

    }

}
