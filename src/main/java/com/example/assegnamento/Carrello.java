package com.example.assegnamento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Carrello {

    private static final Carrello istance = new Carrello();

    private ObservableList<Vini> lista = FXCollections.observableArrayList();
    private ObservableList<Vini> nondisp = FXCollections.observableArrayList();

    //lista che formerà l'ordine di vendita successivamente
    private ObservableList<String> ordine = FXCollections.observableArrayList();
    private ObservableList<String> ordinenondisp = FXCollections.observableArrayList();

    //prezzo che il cliente deve pagare
    private double totale=0;

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

    public ObservableList<String> getOrdine() {
        return ordine;
    }
    public void addOrdine(String stringa){
        ordine.add(stringa);
    }
    public void clearOrdine(){
        ordine.clear();
    }

    public ObservableList<Vini> getNondisp() {
        return nondisp;
    }

    public void setNondisp(ObservableList<Vini> nondisp) {
        this.nondisp = nondisp;
    }

    public ObservableList<String> getOrdineNonDisp() {
        return ordinenondisp;
    }

    public void setOrdineNonDisp(ObservableList<String> ordinenondisp) {
        this.ordinenondisp = ordinenondisp;
    }
    public void clearOrdineNonDisp() {
        this.ordinenondisp.clear();
    }
    public void addOrdineNonDisp(String stringa){
        ordinenondisp.add(stringa);
    }

    public void setTotale(double tot){totale=tot;}
    public double getTotale(){return totale;}
}
