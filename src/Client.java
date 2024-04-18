import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {
        int serverPort = 1234;  // taki sam jak port serwera
        String serverAddress = "localhost"; // dla otoczenia sieciowego należy zmodyfikować
        Socket socket = null;   // socket null bo musimy zadeklarować jakąś wartość wejściową. Docelowy socket przyznamy...
        // po połączeniu z serwerem.

        try {
            System.out.println("Connecting to " + serverAddress);
            socket = new Socket(serverAddress, serverPort);
        } catch (IOException e) {
            System.out.println("Could not connect to " + serverAddress);
        }

        // po udanym połączeniu budujemy strumień
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()), true
            );
            String info = in.readLine();
            System.out.println("Server received: " + info);
            System.out.println("Finishing connection");

            out.println("Thank you");
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Could not connect to " + serverAddress);
        }


    }
}
