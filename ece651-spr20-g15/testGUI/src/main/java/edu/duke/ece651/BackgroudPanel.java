package edu.duke.ece651;

import javax.swing.*;
import java.awt.*;

public class BackgroudPanel extends JPanel {
    private Image image = null;

    public BackgroudPanel(Image image) {
        this.image = image;
    }

    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    public void changeBG(Image image) {
        this.image = image;
        if (this.getGraphics() == null) {
            return;
        }
        this.getGraphics().drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
