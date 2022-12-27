package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Carrello {

    private static final Carrello istance = new Carrello();

    private ObservableList<Vini> lista = FXCollections.observableArrayList();

    Carrello(){}

    public static Carrello getIstance() {
        return istance;
    }

    public void setCarrello(ObservableList<Vini> lista) {
        this.lista = lista;
    }

    public ObservableList<Vini> getCarrello() {
        return lista;
    }
}
