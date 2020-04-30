package edu.duke.ece651;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class watchRoom extends playRoom {
    private JPanel root;
    private JButton Narnia;
    private JButton Elantris;
    private JButton Roshar;
    private JButton Scadrial;
    private JButton Hogwarts;
    private JButton Gondor;
    private JButton Oz;
    private JButton Midkemia;
    private JButton Mordor;
    private JButton updateButton;
    private JPanel top;

    @Override
    public JButton getNarnia() {
        return Narnia;
    }

    @Override
    public JButton getElantris() {
        return Elantris;
    }

    @Override
    public JButton getRoshar() {
        return Roshar;
    }

    @Override
    public JButton getScadrial() {
        return Scadrial;
    }

    @Override
    public JButton getHogwarts() {
        return Hogwarts;
    }

    @Override
    public JButton getGondor() {
        return Gondor;
    }

    @Override
    public JButton getOz() {
        return Oz;
    }

    @Override
    public JButton getMidkemia() {
        return Midkemia;
    }

    @Override
    public JButton getMordor() {
        return Mordor;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public watchRoom(Player player, JFrame currFrame, Client currClient) {
        super(player, currFrame, currClient);
        client = currClient;
        paintColor(player, Narnia);
        paintColor(player, Midkemia);
        paintColor(player, Oz);
        paintColor(player, Elantris);
        paintColor(player, Scadrial);
        paintColor(player, Mordor);
        paintColor(player, Gondor);
        paintColor(player, Roshar);
        paintColor(player, Hogwarts);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    currFrame.setVisible(false);
                    JFrame waitingF = waitingINFO.main();
                    String initString = currClient.Recv();
                    watchRoom.main(currClient, initString);
                    waitingF.setVisible(false);
                } catch (InterruptedException e) {
                    System.out.println("fail to receive");
                } catch (IOException e) {
                    System.out.println("fail to receive");
                }
            }
        });
        Narnia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Narnia", player.getTerritoryInfo("Narnia"));
            }
        });
        Elantris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Elantris", player.getTerritoryInfo("Elantris"));
            }
        });
        Midkemia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Midkemia", player.getTerritoryInfo("Midkemia"));
            }
        });
        Scadrial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Scadrial", player.getTerritoryInfo("Scadrial"));
            }
        });
        Roshar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Roshar", player.getTerritoryInfo("Roshar"));
            }
        });
        Oz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Oz", player.getTerritoryInfo("Oz"));
            }
        });
        Gondor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Gondor", player.getTerritoryInfo("Gondor"));
            }
        });
        Mordor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Mordor", player.getTerritoryInfo("Mordor"));
            }
        });
        Hogwarts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoWindow.main("Hogwarts", player.getTerritoryInfo("Hogwarts"));
            }
        });
    }

    public static void main(Client currClient, String initString) {
        JFrame frame = new JFrame("watchRoom");
        String color = initString.substring(1, initString.indexOf(')'));
        if (initString.indexOf('(') == 0) {
            initString = initString.substring(initString.indexOf(')') + 1);
        }
        Player player = serialize.PlayerDeserialization(color, initString);
        System.out.println(initString);
        serialize.updateAll(player.getAllTerrAndUnits(), initString);
        frame.setContentPane(new watchRoom(player, frame, currClient).top);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        top = new JPanel();
        top.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        root = new JPanel();
        root.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        top.add(root, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Narnia = new JButton();
        Narnia.setText("Narnia");
        root.add(Narnia, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Elantris = new JButton();
        Elantris.setText("Elantris");
        root.add(Elantris, new GridConstraints(1, 0, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Roshar = new JButton();
        Roshar.setText("Roshar");
        root.add(Roshar, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Scadrial = new JButton();
        Scadrial.setText("Scadrial");
        root.add(Scadrial, new GridConstraints(2, 1, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Hogwarts = new JButton();
        Hogwarts.setText("Hogwarts");
        root.add(Hogwarts, new GridConstraints(3, 3, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Gondor = new JButton();
        Gondor.setText("Gondor");
        root.add(Gondor, new GridConstraints(0, 4, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Oz = new JButton();
        Oz.setText("Oz");
        root.add(Oz, new GridConstraints(0, 2, 2, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Midkemia = new JButton();
        Midkemia.setText("Midkemia");
        root.add(Midkemia, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Mordor = new JButton();
        Mordor.setText("Mordor");
        root.add(Mordor, new GridConstraints(2, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Update");
        top.add(updateButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return top;
    }
}
