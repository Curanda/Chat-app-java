package MultiChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {
    static final int PORT = 12345;

    public static void main(String[] args) {
        ServerSocket s = null;
        Socket socket = null;

        try {
            s = new ServerSocket(PORT);
            System.out.println("Server started");
            while (true) {
                socket = s.accept();
                System.out.println("New connection from " + socket.getRemoteSocketAddress());

                new ClientService(socket).start();
            }
        } catch (IOException e) {
        } finally {
            try {
                socket.close();
                s.close();
            } catch (IOException e) {
            }
        }


    }

}
