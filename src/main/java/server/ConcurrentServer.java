package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentServer {

    private static final ConcurrentServer instance = new ConcurrentServer();
    public static ConcurrentServer getInstance() { return instance; }

    private final CopyOnWriteArrayList<Integer> impiegatiOnline = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Integer> ordVendita = new CopyOnWriteArrayList<>();


    public void addOrdVendita(int value) {
        impiegatiOnline.add(value);
    }

    public void removeOrdVendita(int value) {
        impiegatiOnline.remove(value);
    }

    public void addImpiegato(int value) {
        impiegatiOnline.add(value);
    }

    public void removeImpiegato(int value) {
        impiegatiOnline.remove(value);
    }

    public static void main(String[] args) throws IOException {
        // Crea un nuovo ServerSocket sulla porta specificata
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = new Socket("localhost", 8080);


        /**
         * qui ci va una query per prendere tutti gli id degli ordini di vendita NON ANCORA PAGATI E NON RIFIUTATI (si potrebbe fare un solo campo nel DB "completato")
         * vanno aggiunte con
         * this.addOrdVendita(id);
         * */


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
            /**
             * qui ci va qualcosa per segnarsi SOLO l'ID di chi ha fatto il login
             * se è employee o admin allora
             * aggiungo il suo id alla lista nel server con
             * server.addImpiegato(id);
             **/



            //timeout in millisecondi
            socket.setSoTimeout(2000);

            String message = "";

            // Ottieni gli stream di input e output dal socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Connessione Riuscita");

            while(true) {

                // Legge il messaggio del client
                try {
                    message = in.readLine();
                    // utilizza il messaggio
                    out.println("Server: ho ricevuto il tuo messaggio: '" + message + "'");
                } catch (SocketTimeoutException e) {
                    // nessun messaggio disponibile entro il timeout
                    out.println("Nessun messaggio ricevuto");

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
