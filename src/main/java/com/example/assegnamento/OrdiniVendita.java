package com.example.assegnamento;

import javafx.beans.property.SimpleStringProperty;

public class OrdiniVendita extends MyController {
    private final SimpleStringProperty Dataconsegna;
    private final SimpleStringProperty Nome;
    private final SimpleStringProperty Cognome;
    private final SimpleStringProperty Ordine;
    private final SimpleStringProperty Indirizzo;

    OrdiniVendita(String datac,String nom,String cog,String ord,String ind){
        this.Dataconsegna=new SimpleStringProperty(datac);
        this.Nome=new SimpleStringProperty(nom);
        this.Cognome=new SimpleStringProperty(cog);
        this.Ordine=new SimpleStringProperty(ord);
        this.Indirizzo=new SimpleStringProperty(ind);


    }

    public String getDataconsegna() {
        return Dataconsegna.get();
    }

    public String getNome() {
        return Nome.get();
    }

    public String getCognome() {
        return Cognome.get();
    }

    public String getOrdine() {
        return Ordine.get();
    }

    public String getIndirizzo() {
        return Indirizzo.get();
    }
}
