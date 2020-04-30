package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class testLossWindow {
    @Test
    public void testWatch() {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Client currClient = new Client("localhost", 6666);
            String initString = "Green^Food$5000^-Narnia+(Private_1+Corporal_0+Sergent_0+Lieutenant_0+Captain_0+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Blue^Food$50000^-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)";
            JFrame frame = new JFrame("loss window");
            loseWindow test = new loseWindow(currClient,initString,frame);
            JButton watch = test.getWatchButton();
            frame.setContentPane(test.getPanel1());
            watch.doClick();
            frame.dispose();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExit() {
        try {
            ServerSocket ss = new ServerSocket(6666);
            Client currClient = new Client("localhost", 6666);
            String initString = "Green^Food$5000^-Narnia+(Private_1+Corporal_0+Sergent_0+Lieutenant_0+Captain_0+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Blue^Food$50000^-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)";
            JFrame frame = new JFrame("loss window");
            loseWindow test = new loseWindow(currClient,initString,frame);
            JButton exit = test.getExitButton();
            frame.setContentPane(test.getPanel1());
            exit.doClick();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
