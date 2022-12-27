package com.example.assegnamento;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class Vini {

    private final SimpleStringProperty Nome;
    private final SimpleStringProperty p1;
    private final SimpleStringProperty p2;
    private final SimpleStringProperty a;
    private final SimpleStringProperty v;
    private final SimpleStringProperty not;
    private final SimpleDoubleProperty prezzo;
    private final SimpleStringProperty p;
    private CheckBox check;

    private Spinner<Integer> spin;


    //https://www.youtube.com/watch?v=fnU1AlyuguE
    Vini(String nom, String prod, String prov, String anno, String vitigno, String note, String qualita, String vendite, String promo){
        this.Nome= new SimpleStringProperty(nom);
        this.p1 = new SimpleStringProperty(prod);
        this.p2 = new SimpleStringProperty(prov);
        this.a = new SimpleStringProperty(anno);
        this.v = new SimpleStringProperty(vitigno);
        this.not = new SimpleStringProperty(note);
        this.p = new SimpleStringProperty(promo);
        this.check = new CheckBox("");
        this.spin = new Spinner<Integer>();

        double tmp=0;
        if (qualita.equals("Alta"))
            tmp=70;
        else if (qualita.equals("Media"))
            tmp=50;
        else
            tmp=20;

        tmp=tmp+Double.valueOf(vendite)/20;

        if (promo.equals("1"))
            tmp=tmp*0.75;

        this.prezzo = new SimpleDoubleProperty(tmp);

        //spinner setup
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10000,1);
        this.spin.setValueFactory(valueFactory);


    }

    public String getA() {
        return a.get();
    }
    public CheckBox getCheck() {
        return check;
    }

    public String getP1() {
        return p1.get();
    }

    public String getP2() {
        return p2.get();
    }

    public String getP() {
        return p.get();
    }

    public String getV() {
        return v.get();
    }

    public double getPrezzo() {
        return prezzo.get();
    }

    public String getNome() {
        return Nome.get();
    }

    public String getNot() {
        return not.get();
    }

    public Spinner getSpin() { return spin;}
}
