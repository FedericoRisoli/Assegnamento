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

public class EmployeemainpageController {

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
    void OnButtonModifyPSWClick(ActionEvent event) throws IOException {
        //creare pop up per modificare psw
       /* Parent root = FXMLLoader.load(HelloApplication.class.getResource("modifypsw.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Modifica Password");
        stage.setScene(new Scene(root));
        stage.show();*/

    }

}
