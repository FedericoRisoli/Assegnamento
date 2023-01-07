package com.example.assegnamento;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Clienti
{
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty cognome;
    private final SimpleStringProperty username;
    private final SimpleStringProperty codicefiscale;
    private final SimpleStringProperty mail;
    private final SimpleStringProperty telefono;
    private final SimpleStringProperty indirizzo;



    public Clienti(int id, String nome, String cognome, String username, String c_fiscale, String mail, String telefono, String indirizzo) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);

        this.username = new SimpleStringProperty(username);
        this.codicefiscale = new SimpleStringProperty(c_fiscale);
        this.mail = new SimpleStringProperty(mail);
        this.telefono = new SimpleStringProperty(telefono);
        this.indirizzo = new SimpleStringProperty(indirizzo);

    }


    public   int GetID(){return id.get();}
    public   String GetNome(){return nome.get();}
    public   String GetCog(){return cognome.get();}
    public   String Getusername(){return username.get();}

    public  String Getmail(){return mail.get();}
    public  String Gettel(){return telefono.get();}
    public  String Getadd(){return indirizzo.get();}
    public  String Getcf(){return codicefiscale.get();}
}
