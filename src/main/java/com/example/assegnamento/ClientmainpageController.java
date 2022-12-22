package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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
        //ResultSet r = DBHelper.query("SELECT `anno` FROM `wines` WHERE 1");


        annata.getItems().addAll(
                "Option 4", "option5"
        );

    }




}
