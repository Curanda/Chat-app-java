import java.io.PrintWriter;

class ClientDescription {
    PrintWriter out;
    String name;

    ClientDescription(String name, PrintWriter out) {
        this.name = name;
        this.out = out;
    }

    synchronized void sendMessage(String sender, String message) {
        out.println(sender + ": " + message);
    }
}
