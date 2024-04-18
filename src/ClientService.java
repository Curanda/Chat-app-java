import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

public class ClientService extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ClientDescription description;

    static HashSet<ClientDescription> clients = new HashSet<ClientDescription>();

    public ClientService(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendToAll(String name, String message) {
        ClientDescription next;

        for (ClientDescription client : clients) {
            client.sendMessage(name, message);
        }
    }

    void printClients() {
        out.println("There's" + clients.size() + "");

        for (ClientDescription client : clients) {
            out.println(client.name);
        }
        out.println("***************************");
    }

    public void run() {
        String clientName = null;

        try {
            out.println("Provide name: ");
            clientName = in.readLine();
            System.out.println(clientName + " has logged in");
            sendToAll("..:::..", clientName + " has logged in");

            description = new ClientDescription(clientName, out);
            clients.add(description);
            printClients();

            while (true) {
                String info = in.readLine();
                if (info.equals("END")) {
                    System.out.println(clientName + " has logged out");
                    sendToAll("..:::..", clientName + "has logged out");
                    clients.remove(description);
                    in.close();
                    out.close();
                    break;
                }
                sendToAll(clientName, info);
            }

        } catch (IOException e) {
        }

    }


}
