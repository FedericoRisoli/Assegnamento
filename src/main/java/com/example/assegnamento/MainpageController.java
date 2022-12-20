package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainpageController {

    //guardare https://docs.oracle.com/javafx/2/ui_controls/combo-box.htm
    @FXML
    private ComboBox<String> annata;


    @FXML
    private TextField nome_vino;

    @FXML
    private Button search;

    @FXML
    void OnButtonClickSearch(ActionEvent event) {

    }

    @FXML
    private void initialize(){
        System.out.println("Richiedere Gli anni e aggiungerli a ComboBox annata");
        //funziona, bisogna allargare abbastanza la finestra altrimenti non si vede
        //get annate from DB
        annata.getItems().addAll(
                "Option 4", "option5"
        );

    }


}
