package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import javax.swing.*;

public class testInfoWindow {
    @Test
    public void test() {
        JFrame frame = new JFrame("name" + " information");
        infoWindow info = new infoWindow("info", frame);
        frame.setContentPane(info.getRoot());
        JButton finish = info.getFinishButton();
        finish.doClick();
        frame.dispose();
    }
}
