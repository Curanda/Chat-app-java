import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ServerThread(Socket s) {
        System.out.println("server thread constructor");
        socket = s;

        try {
            //komunikacja synchroniczna (odczyt->zapis) obiekt dla danych od klienta
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            System.out.println("Error opening in or out");
        }
    }

    public void run() {
        try {
            while (true) {
                String str = in.readLine();
                // jeżeli klient wyśle stringa o wartości END, to kończymy proces.
                if (str.equals("END")) break;

                // jeżeli wiadomość jest inna niż END, serwer odpisuje to samo do klienta
                out.println(str);
                System.out.println(str);
            }
            System.out.println("Thread finished running");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Run error");
        }


    }
}
