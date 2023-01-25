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
    }

    public int getFirstOrdVendita() {
        int tmp = ordVendita.get(0);
        removeOrdVendita(tmp);
        return tmp;
    }
    //questo rimuove per ID dell'impiegato, per rimuovere per posizione della lista serve un metodo differente o una modifica
    public void removeOrdVendita(int value) {
        ordVendita.remove(Integer.valueOf(value));
    }

    public static void setOrdVendita() throws SQLException {
        ResultSet r = DBHelper.query("SELECT id FROM `ordinivendita` WHERE `completato` = 1");
        while (r.next())
        {
            instance.ordVendita.add(r.getInt("id"));
        }
    }

    public void addImpiegato(int value) {
        impiegatiOnline.add(value);
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
            System.out.println("Inizio Lavori");
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

            //leggo id utente e tipo
            message = in.readLine();
            if (message.startsWith("employee")){
                //System.out.println("SIUUUUUUUUUUUUUUUUUUUUUM"+message);
                message = message.replace("employee","");
                int id = Integer.valueOf(message);
                server.addImpiegato(id);
            } else if (message.startsWith("admin")) {
                message = message.replace("admin","");
                int id = Integer.valueOf(message);
                server.addImpiegato(id);
            }

            int lavoro_attuale=server.getFirstOrdVendita();

            //timeout in millisecondi
            socket.setSoTimeout(10_000);


            System.out.println("Connessione Riuscita");

            while(true) {

                // Legge il messaggio del client
                try {
                    //ottengo nuovo lavoro dal server, altrimenti tempo scaduto
                    message = in.readLine();
                    // utilizza il messaggio
                    System.out.println("Ho ricevuto il tuo messaggio: '" + message + "'");
                } catch (SocketTimeoutException e) {
                    // nessun messaggio disponibile entro il timeout
                    System.out.println("Nessun messaggio ricevuto");
                    /** segno che non ha completato il lavoro sul DB**/
                    for(Integer emp : server.impiegatiOnline)
                    {
                        ResultSet r = DBHelper.query("SELECT * FROM `utenti` WHERE id="+emp);
                        try {
                            r.next();
                            int valore = r.getInt("job_falliti");
                            valore++;

                            //TODO
                            DBHelper.update("UPDATE `utenti` SET `job_falliti` = \""+valore+"\"WHERE id=\""+emp+"\"");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

                     // restituisco il lavoro alla coda
                     server.addOrdVendita(lavoro_attuale);
                     //prendo il prossimo
                     lavoro_attuale= server.getFirstOrdVendita();
                     /** dico al controller della pagina di ricaricare i lavori da fare?
                    **/



                }

                if (message.equals("STOP")) {
                    System.out.println("Chiusura Connessione con client");
                    // Chiude gli stream e il socket
                    in.close();
                    out.close();
                    socket.close();
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Connessione col server Fallita");
            e.printStackTrace();
        }
    }
}
