package com.assegnamento.run;

import java.util.*;




public class Main {

    public static void main(String[] args) {
        int u, c; // utente, comando
        boolean X ; //condizione while


        List<clienti> C = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        u=SystemLogin(scanner); //login
        if(u==0||u==1) //controllo input
        {
            X=true;
            DisplayCommand(u);
            System.out.println("Cosa vuoi fare?");
            c = scanner.nextInt();
//inizio ciclo infinito per i comandi
            do {


                switch (c) {
                    case 0: {
                        //mostra i comandi
                        DisplayCommand(u);
                        c = scanner.nextInt();
                        break;
                    }
                    case 1: {
                        //aggiunge cliente

                        System.out.println("Insert Name ");
                        String name = scanner.next();

                        System.out.println("Insert Surname ");
                        String surname = scanner.next();


                        System.out.println("Insert Fiscal Code ");
                        String fc = scanner.next();

                        System.out.println("Insert Address ");
                        String add = scanner.next();

                        C.add(new clienti(name, surname, fc, add));
                        c = 0;


                        break;
                    }
                    case 2: {
                        //modifica cliente
                        String n = SelezionaCliente(C, scanner);


                        for (clienti w : C) {

                            if (w.name.equals(n)) {
                                System.out.println("Insert Name ");
                                w.name = scanner.next();

                                System.out.println("Insert Surname ");
                                w.surname = scanner.next();

                                System.out.println("Insert Fiscal Code ");
                                w.fc = scanner.next();

                                System.out.println("Insert Address ");
                                w.address = scanner.next();
                            }
                        }
                    }
                    case 3: {
                        //mostra acquisti di un cliente
                        String n = SelezionaCliente(C, scanner);
                        for (clienti w : C) {
                            if (w.name.equals(n)) {
                                System.out.println(w.carrello);
                                System.out.println("Al costo di: " + w.prezzotot);
                            }
                        }

                        c = 0;
                        break;
                    }
                    case 4: {
                        //aggiunge acquisto a un cliente
                        String n = SelezionaCliente(C, scanner);
                        for (clienti w : C) {

                            if (w.name.equals(n)) {
                                System.out.println("Ok Quale articolo vuoi aggiungere?" + " a " + w.name);
                                String articolo = scanner.next();
                                w.carrello.add(articolo);
                                System.out.println("Prezzo?");
                                int p = scanner.nextInt();
                                w.prezzotot += p;
                            }
                        }

                        c = 0;
                        break;
                    }
                    case 5: {
                        //mostra la lista dei clienti
                        System.out.println(C.get(0));

                        for (clienti w : C) {

                            System.out.println(w.name + " | " + w.surname + " | " + w.fc + " | " + w.address);
                            System.out.println("-----------------------------------------");

                        }

                        c = 0;
                        break;
                    }
                    case 10:
                    {
                        //termina programma
                        X=false;
                        break;
                    }


                }

            } while (X);
        }
        else {System.out.println("Inserire 0 o 1");}
        }

        //funzione ceh stampa i possibili comandi
    public static void DisplayCommand(int n) {
        System.out.println("1---Inserisci Cliente");
        System.out.println("2---Modifica Cliente");
        System.out.println("3---Mostra Acquisti di un Cliente");
        System.out.println("4---Aggiungi acquisto di un Cliente");
        System.out.println("5---Lista Clienti");

        if (n == 0)
        {
            System.out.println("6---Annulla Acquisto di un Cliente");
        }
        System.out.println("10---Quit");
        }


    //funzione che permette di selezionare il cliente
    public static String SelezionaCliente(List <clienti> C,Scanner scanner)
    {
        System.out.println("Seleziona Cliente: ");
        String nome;
        for(clienti c: C)
        {
            System.out.println("- "+c.name);
        }
        System.out.println("Inserire nome: ");
        nome=scanner.next();
        return nome ;
    }
    //funzione che permette di selezionare il ruolo
    public static int SystemLogin(Scanner scanner)
    {

        int u;
        String p;
        String AdminPSW = "password";
        System.out.println("Enter Role");
        System.out.println("0 --- Admin");
        System.out.println("1 --- Worker ");
        u = scanner.nextInt();

        switch (u)
        {
            case 0:
                System.out.println("Ok Admin Insert password");
                p=scanner.next();
                if(p.equals(AdminPSW))
                {
                    return u;

                }
                else
                {
                    System.out.println("Password Errata");
                }
            case 1:
                return u;
        }
        return u;
    }
}
