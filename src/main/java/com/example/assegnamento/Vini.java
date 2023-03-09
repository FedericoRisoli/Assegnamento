package com.example.assegnamento;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vini {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty Nome;
    private final SimpleStringProperty p1;
    private final SimpleStringProperty p2;
    private final SimpleStringProperty a;
    private final SimpleStringProperty v;
    private final SimpleStringProperty not;
    private final SimpleDoubleProperty prezzo;
    private final SimpleStringProperty p;
    private final SimpleStringProperty quantita;
    private final SimpleStringProperty vendite;
    private CheckBox check;
    private Spinner<Integer> spin;

    private boolean disponibile=false;    //ci sono abbastanza bottiglie per l'ordine?


    //https://www.youtube.com/watch?v=fnU1AlyuguE
    Vini(Integer id, String nom, String prod, String prov, String anno, String vitigno, String note, String qualita, String vendite, String promo, String qta)
    {
        this.Nome= new SimpleStringProperty(nom);
        this.p1 = new SimpleStringProperty(prod);
        this.p2 = new SimpleStringProperty(prov);
        this.a = new SimpleStringProperty(anno);
        this.v = new SimpleStringProperty(vitigno);
        this.not = new SimpleStringProperty(note);
        this.p = new SimpleStringProperty(promo);
        this.check = new CheckBox("");
        this.spin = new Spinner<Integer>();
        this.quantita=new SimpleStringProperty(qta);
        this.id= new SimpleIntegerProperty(id);
        this.vendite=new SimpleStringProperty(vendite);

        double tmp=0;
        if (qualita.equals("Alta"))
            tmp=50;
        else if (qualita.equals("Media"))
            tmp=30;
        else
            tmp=10;

        tmp=tmp+Double.valueOf(vendite)/20;

        if (promo.equals("1"))
            tmp=tmp*0.75;

        tmp=round(tmp,2);

        this.prezzo = new SimpleDoubleProperty(tmp);

        //spinner setup
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10000,1);
        this.spin.setValueFactory(valueFactory);

    }
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getA() {
        return a.get();
    }
    public CheckBox getCheck() {
        return check;
    }

    public String getQuantita() {
        return quantita.get();
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

    public String getVendite() {
        return vendite.get();
    }

    public SimpleStringProperty venditeProperty() {
        return vendite;
    }

    public Spinner getSpin() { return spin;}

    public int getSpinnerValue(){
        return spin.getValue();
    }
    public String getSpinnerValueString(){
        return Integer.toString(spin.getValue());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public Boolean getDisponibilita(){
        //ci sono abbastanza bottiglie per l'ordine?
        if (spin.getValue()<=Integer.valueOf(this.quantita.get()))
        {
            System.out.println("Ci sono abbastanza bottiglie");
            disponibile=true;
        }
        else System.out.println("NOOOOOOOOOOOOOOO");
        return disponibile;
    }
}
