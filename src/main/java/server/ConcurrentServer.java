package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentServer {

    private static final ConcurrentServer instance = new ConcurrentServer();
    public static ConcurrentServer getInstance() { return instance; }

    final CopyOnWriteArrayList<Integer> impiegatiOnline = new CopyOnWriteArrayList<>();
    protected final CopyOnWriteArrayList<Integer> ordVendita = new CopyOnWriteArrayList<>();


    public void addOrdVendita(int value) {
        ordVendita.add(value);
        //System.out.println(instance.ordVendita);
    }
    public boolean OrdVenditaIsEmpty(){
        //System.out.println(instance.ordVendita);
        return ordVendita.isEmpty();
    }

    public int getFirstOrdVendita() {
        if(ordVendita.isEmpty())
            return -1;
        int tmp = ordVendita.get(0);
        removeOrdVendita(tmp);
        //System.out.println(instance.ordVendita);
        return tmp;
    }
    //questo rimuove per ID dell'impiegato, per rimuovere per posizione della lista serve un metodo differente o una modifica
    public void removeOrdVendita(int value) {
        ordVendita.remove(Integer.valueOf(value));
    }

    public static void setOrdVendita() throws SQLException {
        ResultSet r = DBHelper.query("SELECT id FROM `ordinivendita` WHERE `completato` = 0");
        while (r.next())
        {
            instance.ordVendita.add(r.getInt("id"));
        }
        //System.out.println(instance.ordVendita);
    }

    public void addImpiegato(int value) {
        if(!impiegatiOnline.contains(value))
        {impiegatiOnline.add(value);}
    }

    //questo rimuove per ID dell'impiegato, per rimuovere per posizione della lista serve un metodo differente o una modifica
    public void removeImpiegato(int value) {
        impiegatiOnline.remove(Integer.valueOf(value));
    }

    public static void main(String[] args) throws IOException, SQLException {
        // Crea un nuovo ServerSocket sulla porta specificata
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = new Socket("localhost", 8080);

        //ottengo ord vendita su cui lavorare
        setOrdVendita();

        while (true) {
            // In attesa di una nuova connessione
            Socket clientSocket = serverSocket.accept();

            // Crea un nuovo thread per gestire la connessione del client
            Thread thread = new Thread(new ClientHandler(clientSocket));
            thread.start();
            //System.out.println("Inizio Lavori");
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ConcurrentServer server = ConcurrentServer.getInstance();
        try {

            String message = "";

            // Ottieni gli stream di input e output dal socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            System.out.println("Client Handler Eseguito");

            String[] variabiliMessaggio;
            while(true) {

                // Legge il messaggio del client
                try {
                    message = in.readLine();
                    // utilizza il messaggio
                    System.out.println("Ho ricevuto il tuo messaggio: '" + message + "'");
                    //ottengo nuovo lavoro dal server, altrimenti tempo scaduto

                    //nuova connessione al server

                    /** CONTROLLARE SOLO DENTRO QUESTO ELSE I MESSAGGI*/
                    if (message.startsWith("employee")){
                        message = message.replace("employee","");
                        int id = Integer.valueOf(message);
                        server.addImpiegato(id);
                        if (!server.OrdVenditaIsEmpty())
                        {
                            out.println(id+" "+ server.getFirstOrdVendita());
                        }

                    } else if (message.startsWith("admin")) {
                        message = message.replace("admin","");
                        int id = Integer.valueOf(message);
                        server.addImpiegato(id);
                        if (!server.OrdVenditaIsEmpty())
                        {
                            out.println(id+" "+ server.getFirstOrdVendita());
                        }

                    } else if (message.startsWith("LOGOUT_EMP")) {
                        message = message.replace("LOGOUT_EMP","");
                        variabiliMessaggio= message.split(" ");
                        server.removeImpiegato(Integer.valueOf(variabiliMessaggio[0]));
                        if (!variabiliMessaggio[1].equals("-1"))
                            server.addOrdVendita(Integer.valueOf(variabiliMessaggio[1]));
                    }
                    else if (message.startsWith("TIMEDOUT")) {
                        message = message.replace("TIMEDOUT","");
                        variabiliMessaggio= message.split(" ");
                        if(variabiliMessaggio[1].equals("-1"))
                        {
                            if(!server.OrdVenditaIsEmpty())
                                out.println(variabiliMessaggio[0]+" "+server.getFirstOrdVendita());
                        }
                        else
                        {
                            server.addOrdVendita(Integer.valueOf(variabiliMessaggio[1]));
                            //assegno nuovo lavoro
                            if(!server.OrdVenditaIsEmpty())
                                out.println(variabiliMessaggio[0]+" "+server.getFirstOrdVendita());

                            //segno che non ha finito il lavoro
                            ResultSet r = DBHelper.query("SELECT * FROM `utenti` WHERE id="+variabiliMessaggio[0]);
                            try {
                                r.next();
                                int valore = r.getInt("job_falliti");
                                valore++;

                                DBHelper.update("UPDATE `utenti` SET `job_falliti` = \""+valore+"\" WHERE id=\""+variabiliMessaggio[0]+"\"");
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    }
                    else if (message.startsWith("COMPLETATO"))
                    {
                        message = message.replace("COMPLETATO","");
                        ResultSet r = DBHelper.query("SELECT * FROM `utenti` WHERE id=\""+message+"\"");
                        try {
                            r.next();
                            int valore = r.getInt("job_completati");
                            valore++;
                            String query="UPDATE `utenti` SET `job_completati` = \""+valore+"\" WHERE id=\""+message+"\"";
                            DBHelper.update(query);

                            if(!server.OrdVenditaIsEmpty())
                                out.println(message+" "+server.getFirstOrdVendita());

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else if (message.startsWith("SKIP"))
                    {
                        message = message.replace("SKIP","");
                        variabiliMessaggio = message.split(" ");
                        server.addOrdVendita(Integer.valueOf(variabiliMessaggio[1]));
                        out.println(variabiliMessaggio[0]+" "+server.getFirstOrdVendita());
                    }
                    else if (message.startsWith("ADDWORK")) {
                        message = message.replace("ADDWORK","");
                        server.addOrdVendita(Integer.valueOf(message));
                    }

                    if (message.equals("STOP")) {
                        System.out.println("Chiusura Connessione con client");
                        // Chiude gli stream e il socket
                        in.close();
                        out.close();
                        socket.close();
                        break;
                    }


                    //timeout in millisecondi
                    socket.setSoTimeout(30_000);
                } catch (SocketTimeoutException e) {
                    // nessun messaggio disponibile entro il timeout
                    //System.out.println("Nessun messaggio ricevuto");

                    //mando messaggio a tutti
                     if(!server.OrdVenditaIsEmpty())
                        out.println("TIMEOUT");
                }

            }

        } catch (IOException e) {
            System.out.println("Connessione col server Fallita");
            e.printStackTrace();
        }
    }
}
