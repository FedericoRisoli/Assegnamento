package com.example.assegnamento;

public class Data {
    private static final Data instance = new Data();
    public int id;
    private Data(){}
    public  static Data getInstance()
    {
        return instance;
    }
    public void SetData(int i)
    {
        this.id=i;
    }
    public int GetId(){return id;}
}
