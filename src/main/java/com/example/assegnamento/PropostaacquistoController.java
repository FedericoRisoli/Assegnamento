package com.example.assegnamento;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PropostaacquistoController
{

        Carrello carrello = Carrello.getIstance();
        @FXML
        private Button OnButtonAnnullaClick;

        @FXML
        private Button OnButtonInviaClick;

        @FXML
        private TableColumn<String, String> t_propname;

        @FXML
        private TableColumn<String, String> t_propqta;

        @FXML
        private TableView<String> tabellapropac;

        //TODO FIX ME PLZ
        @FXML
        public void initializa(){
                t_propname.setCellValueFactory(new PropertyValueFactory<String,String>("nome"));
                t_propqta.setCellValueFactory(new PropertyValueFactory<String,String>("valore"));
                ObservableList<Vini> nomi = FXCollections.observableArrayList();
                ObservableList<String> qta = FXCollections.observableArrayList();

                for  (String item : carrello.getOrdineNonDisp())
                {

                }
        }

    }


