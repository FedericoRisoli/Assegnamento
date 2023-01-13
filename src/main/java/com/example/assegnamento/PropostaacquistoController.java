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
        private TableColumn<Vini, String> t_propname;

        @FXML
        private TableColumn<Vini, String> t_propqta;

        @FXML
        private TableView<Vini> tabellapropac;

        //TODO FIX ME PLZ
        @FXML
        public void initializa(){
                t_propname.setCellValueFactory(new PropertyValueFactory<Vini,String>("nome"));
                t_propqta.setCellValueFactory(new PropertyValueFactory<Vini,String>("valore"));
                ObservableList<Vini> data = FXCollections.observableArrayList();

                int i=0;
                for  (String item : carrello.getOrdineNonDisp())
                {
                data.add(carrello.getNondisp().get(i));
                tabellapropac.setItems(data);
                i++;
                data.add(carrello.getNondisp().get(i));
                i++;
                tabellapropac.setItems(data);
                }
        }

    }


