package com.example.assegnamento;

public class Data {
    private static final Data instance = new Data();
    public int id;
    public String role;
    public String username;
    public String nome;
    public String cognome;
    public boolean success;

    public boolean promo;

    private Data(){}
    public  static Data getInstance()
    {
        return instance;
    }
    public void Setid(int i)
    {
        this.id=i;
    }
    public void Setname(String s)
    {
        this.nome=s;
    }
    public void Setsurname(String s)
    {
        this.cognome=s;
    }
    public void SetPromo(boolean p) {this.promo=p;}
    public boolean GetPromo(){return promo;}
    public void SetRole(String s )
    {
        this.role=s;
    }

    public void Setusername(String s )
    {
        this.username=s;
    }
    public int GetId(){return id;}
    public String Getrole(){return role;}
    public String Getname(){return nome;}
    public String Getsurname(){return cognome;}
    public String Getusername(){return username;}
    public boolean GetSuccess(){return success;}
    public void SetSuccess(boolean s){this.success=s;}
    public void reset(){this.id=-100;this.role="";this.username="";this.nome="";this.cognome="";};
}
