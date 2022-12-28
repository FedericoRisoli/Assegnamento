package com.example.assegnamento;

public class Data {
    private static final Data instance = new Data();
    public int id;
    public String role;
    public String username;

    private Data(){}
    public  static Data getInstance()
    {
        return instance;
    }
    public void Setid(int i)
    {
        this.id=i;
    }
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
    public String Getusername(){return username;}
    public void reset(){this.id=-100;this.role="";this.username="";};
}
