package com.example.assegnamento;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Clienti
{
    private final SimpleStringProperty Cognome;
    private final SimpleStringProperty Nome;
    private final SimpleStringProperty Username;
    private final SimpleStringProperty Mail;
    private final SimpleStringProperty Telefono;
    private final SimpleStringProperty Indirizzo;
    private final SimpleStringProperty CodiceFiscale;

    Clienti(String cog, String nome, String user, String mail, String tel, String ind , String cf)
    {
        this.Cognome=new SimpleStringProperty(cog);
        this.Nome=new SimpleStringProperty(nome);
        this.Username=new SimpleStringProperty(user);
        this.Mail=new SimpleStringProperty(mail);
        this.Telefono=new SimpleStringProperty(tel);
        this.Indirizzo=new SimpleStringProperty(ind);
        this.CodiceFiscale=new SimpleStringProperty(cf);

    }

    public String getCognome() {
        return Cognome.get();
    }

    public String getNome() {
        return Nome.get();
    }

    public String getUsername() {
        return Username.get();
    }

    public String getMail() {
        return Mail.get();
    }

    public String getTelefono() {
        return Telefono.get();
    }

    public String getIndirizzo() {
        return Indirizzo.get();
    }

    public String getCodiceFiscale() {
        return CodiceFiscale.get();
    }
}
