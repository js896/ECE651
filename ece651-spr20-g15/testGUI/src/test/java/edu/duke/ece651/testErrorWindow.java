package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import javax.swing.*;

public class testErrorWindow {
    @Test
    public void testShow() {
        errorWindow test = new errorWindow();
        test.main();
        test.currFrame.dispose();
    }
}
