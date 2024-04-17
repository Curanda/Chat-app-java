import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int portNumber = 12345;
    // port nasłuchiwania serwera. Można ustawić dowolny.
    private ServerSocket serverSocket;

    public Server() {
        try {
            // ten blok podejmuje próbę zajęcia portu do nasłuchiwania.
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is up");
        } catch (IOException e) {
            System.out.println("Server could not be created");
            System.exit(1);
        }
    }

    public void action() {

        Socket clientSocket;

        while (true) {
            if (serverSocket == null) return;
            try {
                System.out.println("Waiting for the client");
                clientSocket = serverSocket.accept();
                System.out.println("Client connected. clientSocket = " + clientSocket);
// przygotowanie strumienia do wysyłania z portu
                PrintWriter theWriter = new PrintWriter(
                        new OutputStreamWriter(clientSocket.getOutputStream()), true);
// przygotowanie strumienia do odczytywania z portu
                BufferedReader theReader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream())
                );
// wysyłanei komunikatu - klient odbiera z portu.
                theWriter.println("How can I help you?");
                // odczytywanie komunikatu z portu
                String info = theReader.readLine();
                System.out.println("Client has written : " + info);
                
                System.out.println("Terminating connection");
                theWriter.close();
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error in connection : \n" + e.getMessage());
                System.exit(1);
                throw new RuntimeException(e);
            }
        }

    }

}
