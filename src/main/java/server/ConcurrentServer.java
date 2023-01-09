package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ConcurrentServer {
    public static void main(String[] args) throws IOException {
        // Crea un nuovo ServerSocket sulla porta specificata
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = new Socket("localhost", 8080);


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
        try {
            // Ottieni gli stream di input e output dal socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true) {

                // Legge il messaggio del client
                String message = in.readLine();

                // Invia una risposta al client
                out.println("Server: ho ricevuto il tuo messaggio: '" + message + "'");

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
