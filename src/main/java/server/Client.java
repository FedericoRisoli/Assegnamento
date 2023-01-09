package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // Crea un nuovo socket che si connette al server sulla porta specificata
        int port = 8080;
        Socket socket = new Socket("localhost", port);

        // Ottieni gli stream di input e output dal socket
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Invia un messaggio al server
        String message = "Ciao server!";
        out.println(message);

        // Legge la risposta del server
        String response = in.readLine();
        System.out.println(response);

        // Ottieni gli stream di input e output dal socket
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Crea un reader per leggere l'input della console
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        // Rimani in attesa d'input della console
        while (true) {
            // Legge l'input della console
            message = consoleReader.readLine();

            // Invia il messaggio al server
            out.println(message);

            // Legge la risposta del server
            response = in.readLine();
            System.out.println(response);

            if (message.equals("STOP"))
            {
                System.out.println("Chiusura connessione");
                // Chiude gli stream e il socket
                in.close();
                out.close();
                socket.close();
                break;
            }
        }

    }
}
