package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class testHome {
    @Test
    public void testShow() throws IOException {
        GamePlay testGame = new GamePlay();
        JFrame frame = new JFrame("home");
        home test = new home(frame);
        frame.setContentPane(test.getRoot());
        JTextField IP = test.getTextField1();
        JTextField port = test.getTextField2();
        IP.setText("localhost");
        port.setText("6666");
        //JButton submit = test.getSubmit();
        frame.dispose();
        //submit.doClick();
        testGame.close();
        return;
    }
}
