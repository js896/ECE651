package edu.duke.ece651;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// This class is the parent class of Player class which defines some basic
// constructors and connection functions
public class Client {
    // Current client's socket
    public Socket ClientSocket;

    // This constructor is used to test and connect to local server
    public Client() throws IOException {
        ClientSocket = new Socket("127.0.0.1", 6666);
    }

    // This constructor is used to set new Client based on IP and
    // port number
    public Client(String addr, int port_num) {
        try {
            ClientSocket = new Socket(addr, port_num);
        } catch (IOException e) {
            System.out.println("fail");
        }
    }

    // This constructor is used to set new Client based on an existed socket
    public Client(Socket inputSocket) throws IOException {
        ClientSocket = inputSocket;
    }

    // This function is used to receive message from server
    public String Recv() throws IOException, InterruptedException {
        InputStreamReader isr = new InputStreamReader(ClientSocket.getInputStream());
        // Only receive message when server send message to this client
        while (!isr.ready()) {
            Thread.sleep(5);
        }
        BufferedReader br = new BufferedReader(isr);
        String data = br.readLine();
        return data;
    }

    // This function is used to send message to server
    public void Send(String ClientMsg) throws IOException {
        PrintWriter pw = new PrintWriter(ClientSocket.getOutputStream(), true);
        pw.println(ClientMsg);
    }

    // This function is used to check if received message has the end mark.
    public String check(String input) {
        if (input.contains("end")) {
            return input.substring(input.indexOf('-') + 1);
        }
        return null;
    }

}
