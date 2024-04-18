package MultiChat;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    static int port = 1234;
    static Socket socket = null;
    static ServerSocket s = null;

    public static void main(String[] args) {
        try {
            s = new ServerSocket(port);
            System.out.println("Server started");
            while (true) {
                // czekamy na klienta
                socket = s.accept();
                System.out.println("Client connected at: " + socket.getInetAddress());
                // oddajemy klientowi do dyspozycji watek serwera :
                ServerThread thread = new ServerThread(socket);
                thread.start();
                // zamknieciem socketu zajmie się watek
            }
        } catch (Exception e) {
            System.out.println("Error occured: " + e);
        }
        try {
            s.close();
        } catch (Exception e) {
            System.out.println("Error occured: " + e);
        }

    }


}
