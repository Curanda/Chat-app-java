package MultiChat;

import java.io.*;
import java.net.Socket;

public class ClientTalker {
    public static void main(String[] args) {
        int serverPort = 12345;
        String serverAddress = "localhost";
        Socket socket = null;
        PrintWriter out = null;

        try {
            System.out.println("Connecting to " + serverAddress + ":" + serverPort);
            socket = new Socket(serverAddress, serverPort);
        } catch (IOException e) {
            System.out.println("Connection failed");
        } catch (Exception e) {
            System.exit(0);
        }

        Thread thread = new ClientThread(socket);
        thread.start();

        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (Exception e) {
        }

        while (true) {
            String info;
            // strumień z klawiatury
            BufferedReader we = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter message for the server : ");
            try {
                info = we.readLine();
            } catch (IOException e) {
                info = "";
            }

            try {
                out.println(info);
                if (info.equals("END")) break;
            } catch (Exception e) {
                break;
            }
        }
        try {
            out.close(); // zamykam strumień do serwera
        } catch (Exception e) {
        }

    }
}
