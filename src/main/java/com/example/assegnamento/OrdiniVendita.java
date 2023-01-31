package com.example.assegnamento;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class OrdiniVendita extends MyController {
    private final SimpleStringProperty Dataconsegna;
    private final SimpleStringProperty Nome;
    private final SimpleStringProperty Cognome;
    private final SimpleStringProperty Ordine;
    private final SimpleStringProperty Indirizzo;
    private final  SimpleStringProperty Id;
    private CheckBox check;

    OrdiniVendita(String Id, String datac,String nom,String cog,String ord,String ind){
        this.Dataconsegna=new SimpleStringProperty(datac);
        this.Nome=new SimpleStringProperty(nom);
        this.Cognome=new SimpleStringProperty(cog);
        this.Ordine=new SimpleStringProperty(ord);
        this.Indirizzo=new SimpleStringProperty(ind);
        this.check = new CheckBox("");
        this.Id= new SimpleStringProperty(Id);


    }
    public CheckBox getCheck() {
        return check;
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

    public String getId() {
        return Id.get();
    }
}
