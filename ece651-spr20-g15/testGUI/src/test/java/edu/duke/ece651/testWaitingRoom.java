package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class testWaitingRoom {
    @Test
    public void test() {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Client currClient = new Client("localhost", 6666);

            JFrame frame = new JFrame("waitingRoom");
            waitingRoom waiting = new waitingRoom(currClient, frame);
            waiting.color = "Red";
            frame.setContentPane(waiting.getPanel1());
            JTextField player1 = waiting.getPlayer1Status();
            player1.grabFocus();
            frame.dispose();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
