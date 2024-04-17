import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
    private BufferedReader in;

    public ClientThread(Socket socket) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
        }
    }

    public void run() {
        String str;
        try {
            while ((str = in.readLine()) != null) {
                System.out.println("Server sent: " + str);
            }
            in.close();
        } catch (IOException e) {
        }
    }
}
