import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private static final int portNumber = 12345;
    // port nasłuchiwania serwera. Można ustawić dowolny.
    private ServerSocket serverSocket;

    public Server() {
        try {
            // ten blok podejmuje próbę zajęcia portu do nasłuchiwania.
            serverSocket = new ServerSocket(12345);
            System.out.println("Server is up");
        } catch (IOException e) {
            System.out.println("Server could not be created");
            System.exit(1);
        }
    }

    public void action() {

        Socket clientSocket;

        while(true) {
            if (serverSocket == null) return;
            try {
                System.out.println("Waiting for the client");
            }
        }

    }

}
