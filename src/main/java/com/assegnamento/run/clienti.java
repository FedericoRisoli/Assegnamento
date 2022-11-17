package com.assegnamento.run;

import java.util.ArrayList;
import java.util.List;

public class clienti
{
    String name;
    String surname;
    String fc;
    String address;
    List<String> carrello =new ArrayList<String>();
    float prezzotot=0;
    float conto=0;
    public clienti(String name, String surname, String fc, String add)
    {
         this.name = name;
         this.surname = surname;
         this.fc = fc;
         this.address = add;


    }



}
