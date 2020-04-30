package edu.duke.ece651;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// This class is the parent class of GamePlay class which defines some basic
// constructors and connection functions
public class Server {
    // Current server's socket
    public ServerSocket currServer;

    // Receive message from client socket
    public String Recv(Socket currSocket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(currSocket.getInputStream()));
        String data = br.readLine();
        return data;
    }

    // Send a message to target client socket
    public void Send(Socket currSocket, String ServerMsg) throws IOException {
        PrintWriter pw = new PrintWriter(currSocket.getOutputStream(), true);
        pw.println(ServerMsg);
    }

}
