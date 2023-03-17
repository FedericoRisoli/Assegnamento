package com.example.assegnamento;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class OrdiniVendita{
    private final SimpleStringProperty Dataconsegna;
    private  double Prezzo;
    private final SimpleStringProperty Nome;
    private final SimpleStringProperty Cognome;
    private final SimpleStringProperty Idcliente;
    private final SimpleStringProperty Ordine;
    private final SimpleStringProperty Indirizzo;
    private final  SimpleStringProperty Id;
    private final  SimpleStringProperty firmaimp;

    private final SimpleStringProperty DataOrdine;
    private CheckBox check;

    OrdiniVendita(String Id, String dataord, String datac,String nom,String cog,String ord,String ind,double prezzo,String idcl,String firmimp){
        this.Dataconsegna=new SimpleStringProperty(datac);
        this.Nome=new SimpleStringProperty(nom);
        this.Cognome=new SimpleStringProperty(cog);
        this.Ordine=new SimpleStringProperty(ord);
        this.Indirizzo=new SimpleStringProperty(ind);
        this.check = new CheckBox("");
        this.Id= new SimpleStringProperty(Id);
        this.Prezzo=new Double(prezzo);
        this.Idcliente= new SimpleStringProperty(idcl);
        this.DataOrdine = new SimpleStringProperty(dataord);
        this.firmaimp=new SimpleStringProperty(firmimp);

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
    public String getIdcliente() {return Idcliente.get();}
    public String getFirm() {return firmaimp.get();}
    public double getPrezzo() { return Prezzo; }


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

    public String getDataOrdine(){return DataOrdine.get();}
}
