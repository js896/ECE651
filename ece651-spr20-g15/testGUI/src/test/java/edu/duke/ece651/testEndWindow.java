package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import javax.swing.*;

public class testEndWindow {

    @Test
    public void testEnd() {
        JFrame frame = new JFrame("test end");
        endWindow test = new endWindow("Green", frame);
        JButton continueButton = test.getContinueButton();
        continueButton.doClick();
        frame.dispose();
    }
}
