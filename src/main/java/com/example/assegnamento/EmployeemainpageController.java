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
    Data data = Data.getInstance();



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
    void OnButtonModifyPSWClick(ActionEvent event) {
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






}

