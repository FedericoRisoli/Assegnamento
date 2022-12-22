package com.example.assegnamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AdminmainpageController {

    @FXML
    private ComboBox<String> annata;

    @FXML
    private TextField nome_vino;

    @FXML
    private Button search;

    @FXML
    void OnButtonClickSearch(ActionEvent event) {
 System.out.println("Admin: search");
    }

    @FXML
    void OnButtonModifyPSWClick(ActionEvent event) {

    }

}
