package MultiChat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient extends JFrame implements ActionListener {
    PrintWriter out;
    int serverPort = 12345;
    String serverAddress = "localhost";
    Socket socket = null;

    JTextArea list = new JTextArea();
    JScrollPane up = new JScrollPane(list);
    JTextField text = new JTextField(27);
    JButton send = new JButton("Send");

    ChatClient(String title) {
        super(title);
    }

    public static void main(String[] args) {
        ChatClient window = new ChatClient(".:. Super Client .:.");
        window.init();
        window.setVisible(true);
        window.connect();
    }

    void init() {
        setSize(500, 400);
        setResizable(false);
        Container cp = getContentPane();

        list.setEditable(false);
        up.setBorder(
                new TitledBorder(new LineBorder(Color.red), "Server feedback")
        );
        cp.add(up, BorderLayout.CENTER);

        JPanel down = new JPanel();
        down.setLayout(new FlowLayout());
        send.addActionListener(this);
        down.add(send);
        text.addActionListener(this);
        down.add(text);
        cp.add(down, BorderLayout.SOUTH);

        Closer watcher = new Closer(this);
        addWindowListener(watcher);
    }

    void connect() {
        try {
            list.append("Connecting to : " + serverAddress + ":" + serverPort);
        } catch (Exception e) {
            System.exit(0);
        }

        try {
            socket = new Socket(serverAddress, serverPort);
        } catch (IOException e) {
            list.append("Could not connect to : " + serverAddress + ":" + serverPort);
            return;
        }

        // startujemy watek nasluchujacy

        Thread thread = new ThreadClientChat(this);
        thread.start();
        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (Exception e) {
            list.append("Error:" + e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        out.println(text.getText());
        if (text.getText().equals("END")) {
            try {
                out.close();
                socket.close();
            } catch (Exception e2) {
                System.exit(0);
            }
            text.setText("");
        }
    }

}
