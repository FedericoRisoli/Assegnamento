package com.example.assegnamento;

import javafx.beans.property.*;


public class Clienti
{

    private final SimpleStringProperty Nome;
    private final SimpleStringProperty Cognome;
    private final SimpleStringProperty Username;


    public Clienti(String nom,String cog, String user) {
        this.Nome = new SimpleStringProperty(nom);
        this.Cognome=new SimpleStringProperty(cog);
        this.Username=new SimpleStringProperty(user);
    }
   /* private final SimpleStringProperty codicefiscale;
    private final SimpleStringProperty mail;
    private final SimpleStringProperty telefono;
    private final SimpleStringProperty indirizzo;*/






    public   String GetNome(){return Nome.get();}
    public   String GetCog(){return Cognome.get();}
    public   String Getusername(){return Username.get();}
/*
    public  String Getmail(){return mail.get();}
    public  String Gettel(){return telefono.get();}
    public  String Getadd(){return indirizzo.get();}
    public  String Getcf(){return codicefiscale.get();}*/
}
