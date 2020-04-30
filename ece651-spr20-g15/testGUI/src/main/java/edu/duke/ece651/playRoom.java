package edu.duke.ece651;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class playRoom extends JFrame {
    private JButton Narnia;
    private JButton Midkemia;
    private JButton Oz;
    private JButton Elantris;
    private JButton Scadrial;
    private JButton Mordor;
    private JButton Gondor;
    private JButton Roshar;
    private JButton Hogwarts;
    private JPanel root;
    private JComboBox attackSrc;
    private JComboBox attackUnitLevel;
    private JComboBox attackNum;
    private JComboBox moveSrc;
    private JComboBox moveUnitLevel;
    private JComboBox moveNum;
    private JButton finishButton;
    private JButton finishButton1;
    private JButton finishButton2;
    private JComboBox upgradeSrc;
    private JComboBox upgradeUnitLevel;
    private JComboBox upgradeTargetLevel;
    private JLabel attackLabel;
    private JLabel moveLabel;
    private JLabel upgradeLabel;
    private JButton commitButton;
    private JLabel levelLabel;
    private JComboBox attackDst;
    private JComboBox moveDst;
    private JComboBox upgradeNum;
    private JLabel totalResource;
    private JFrame waitF;
    private String[] levels = {"Private", "Corporal", "Sergent", "Lieutenant", "Captain", "Major", "Colonel"};


    private String action = "";
    private Map<String, Integer> levelAndName = new HashMap<>();
    private Map<String, Map<String, Integer>> allUnits = new HashMap<>();
    protected Client client;

    public JButton getNarnia() {
        return Narnia;
    }

    public JFrame getWaitF() {
        return waitF;
    }

    public JButton getMidkemia() {
        return Midkemia;
    }

    public JButton getOz() {
        return Oz;
    }

    public JButton getElantris() {
        System.out.println("here");
        return Elantris;
    }

    public JButton getScadrial() {
        return Scadrial;
    }

    public JButton getMordor() {
        return Mordor;
    }

    public JButton getGondor() {
        return Gondor;
    }

    public JButton getRoshar() {
        return Roshar;
    }

    public JButton getHogwarts() {
        return Hogwarts;
    }

    public JPanel getRoot() {
        return root;
    }

    public JComboBox getAttackSrc() {
        return attackSrc;
    }

    public JComboBox getAttackUnitLevel() {
        return attackUnitLevel;
    }

    public JComboBox getAttackNum() {
        return attackNum;
    }

    public JComboBox getMoveSrc() {
        return moveSrc;
    }

    public JComboBox getMoveUnitLevel() {
        return moveUnitLevel;
    }

    public JComboBox getMoveNum() {
        return moveNum;
    }

    public JButton getFinishButton() {
        return finishButton;
    }

    public JButton getFinishButton1() {
        return finishButton1;
    }

    public JButton getFinishButton2() {
        return finishButton2;
    }

    public JComboBox getUpgradeSrc() {
        return upgradeSrc;
    }

    public JComboBox getUpgradeUnitLevel() {
        return upgradeUnitLevel;
    }

    public JComboBox getUpgradeTargetLevel() {
        return upgradeTargetLevel;
    }

    public JComboBox getAttackDst() {
        return attackDst;
    }

    public JComboBox getMoveDst() {
        return moveDst;
    }

    public JComboBox getUpgradeNum() {
        return upgradeNum;
    }


    public void setColor(String color, JButton currButton) {
        if (color.equals("Green")) {
            currButton.setBackground(Color.green);
        } else if (color.equals("Blue")) {
            currButton.setBackground(Color.blue);
        } else if (color.equals("Red")) {
            currButton.setBackground(Color.red);
        } else if (color.equals("Yellow")) {
            currButton.setBackground(Color.yellow);
        } else if (color.equals("Orange")) {
            currButton.setBackground(Color.orange);
        } else {
            currButton.setBackground(Color.black);
        }
        currButton.setOpaque(true);
        currButton.setBorderPainted(false);
    }

    public void paintColor(Player player, JButton currButton) {
        boolean interrupt = false;
        for (Map.Entry<String, Player> entry : player.getAllTerrAndUnits().entrySet()) {
            String playerColor = entry.getKey();
            Player currPlayer = entry.getValue();
            for (Iterator it = currPlayer.getTerritories().iterator(); it.hasNext(); ) {
                Territory currTerritory = (Territory) it.next();
                String currName = currTerritory.getTerrName();
                if (currName.equals(currButton.getText())) {
                    setColor(playerColor, currButton);
                    interrupt = true;
                    break;
                }
            }
            if (interrupt) {
                break;
            }
        }
    }


    public void setSrcCombo(Player player) {
        attackSrc.addItem("...");
        moveSrc.addItem("...");
        upgradeSrc.addItem("...");
        for (Map.Entry<String, Map<String, Integer>> entry : player.getCurrTerrAndUnits().entrySet()) {
            String tm = entry.getKey();
            attackSrc.addItem(tm);
            moveSrc.addItem(tm);
            upgradeSrc.addItem(tm);
        }
    }

    public Map<String, Map<String, Integer>> deepCopy(Map<String, Map<String, Integer>> input) {
        Map<String, Map<String, Integer>> ans = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : input.entrySet()) {
            String terrName = entry.getKey();
            Map<String, Integer> units = entry.getValue();
            Map<String, Integer> curr = new HashMap<>();
            for (Map.Entry<String, Integer> entry_inside : units.entrySet()) {
                curr.put(entry_inside.getKey(), entry_inside.getValue());
            }
            ans.put(terrName, curr);
        }
        return ans;
    }

    public playRoom(Player player, JFrame currFrame, Client currClient) {
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
        setSrcCombo(player);
        allUnits = deepCopy(player.getCurrTerrAndUnits());
        for (Iterator<Resource> it = player.getTotalResource().iterator(); it.hasNext(); ) {
            Resource currRes = it.next();
            totalResource.setText("You are " + player.getColor() + " Player. " + "Your current round has " + currRes.getNum() + " " + currRes.getType() + " in total.");

        }
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!attackSrc.getSelectedItem().equals("...") && !attackUnitLevel.getSelectedItem().equals("...") && !attackNum.getSelectedItem().equals("...") && !attackDst.getSelectedItem().equals("...")) {
                    if (action != "") {
                        action += "@";
                    }
                    action += "Attack~" + attackUnitLevel.getSelectedItem() + "~" + attackNum.getSelectedItem() + "~" + attackSrc.getSelectedItem() + "~" + attackDst.getSelectedItem();
                    int currNum = allUnits.get(attackSrc.getSelectedItem()).get(attackUnitLevel.getSelectedItem()) - (int) attackNum.getSelectedItem();
                    allUnits.get(attackSrc.getSelectedItem()).remove(attackUnitLevel.getSelectedItem());
                    if (currNum > 0) {
                        allUnits.get(attackSrc.getSelectedItem()).put((String) attackUnitLevel.getSelectedItem(), currNum);
                    }
                }
                attackUnitLevel.removeAllItems();
                attackUnitLevel.addItem("...");
                attackNum.removeAllItems();
                attackNum.addItem("...");
                attackDst.removeAllItems();
                attackDst.addItem("...");
            }
        });
        finishButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!moveSrc.getSelectedItem().equals("...") && !moveUnitLevel.getSelectedItem().equals("...") && !moveNum.getSelectedItem().equals("...") && !moveDst.getSelectedItem().equals("...")) {
                    if (action != "") {
                        action += "@";
                    }
                    action += "Move~" + moveUnitLevel.getSelectedItem() + "~" + moveNum.getSelectedItem() + "~" + moveSrc.getSelectedItem() + "~" + moveDst.getSelectedItem();
                    int currNum = allUnits.get(moveSrc.getSelectedItem()).get(moveUnitLevel.getSelectedItem()) - (int) moveNum.getSelectedItem();
                    allUnits.get(moveSrc.getSelectedItem()).remove(moveUnitLevel.getSelectedItem());
                    if (currNum > 0) {
                        allUnits.get(moveSrc.getSelectedItem()).put((String) moveUnitLevel.getSelectedItem(), currNum);
                    }
                }
                moveUnitLevel.removeAllItems();
                moveUnitLevel.addItem("...");
                moveNum.removeAllItems();
                moveNum.addItem("...");
                moveDst.removeAllItems();
                moveDst.addItem("...");
            }
        });
        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (action != "") {
                    action += "@";
                }
                action += "Done~";
                String msgToSend = serialize.PlayerSerialization(player, action);
                try {
                    currClient.Send(msgToSend);
                    currFrame.setVisible(false);
                    waitF = waitingINFO.main();
                    String initString = currClient.Recv();
                    playRoom.main(currClient, initString);
                    waitF.setVisible(false);
                } catch (InterruptedException e) {
                    System.out.println("fail to receive");
                } catch (IOException e) {
                    System.out.println("fail to receive");
                }
            }
        });
        finishButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!upgradeSrc.getSelectedItem().equals("...") && !upgradeUnitLevel.getSelectedItem().equals("...") && !upgradeNum.getSelectedItem().equals("...") && !upgradeTargetLevel.getSelectedItem().equals("...")) {
                    if (action != "") {
                        action += "@";
                    }
                    action += "Upgrade~" + upgradeSrc.getSelectedItem() + "~" + upgradeUnitLevel.getSelectedItem() + "~" + upgradeNum.getSelectedItem() + "~" + upgradeTargetLevel.getSelectedItem();
                }
                upgradeTargetLevel.removeAllItems();
                upgradeTargetLevel.addItem("...");
                upgradeUnitLevel.removeAllItems();
                upgradeUnitLevel.addItem("...");
                upgradeNum.removeAllItems();
                upgradeNum.addItem("...");
            }
        });
        attackDst.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                attackDst.removeAllItems();
                attackDst.addItem("...");
                String src = (String) attackSrc.getSelectedItem();
                if (src.equals("...")) {
                    return;
                } else {
                    List<String> adjacentList = player.getTm().allTerritories.get(src);
                    Map<String, Map<String, Integer>> ownMap = player.getCurrTerrAndUnits();
                    for (String currTerritory : adjacentList) {
                        if (!ownMap.containsKey(currTerritory)) {
                            attackDst.addItem(currTerritory);
                        }
                    }
                }
            }
        });
        moveDst.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                moveDst.removeAllItems();
                moveDst.addItem("...");
                String src = (String) moveSrc.getSelectedItem();
                if (src.equals("...")) {
                    return;
                } else {
                    Set<String> moveList = player.canMoveDstTerr(src);
                    for (String dest : moveList) {
                        moveDst.addItem(dest);
                    }
                }
            }
        });
        attackUnitLevel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                attackUnitLevel.removeAllItems();
                attackUnitLevel.addItem("...");
                String src = (String) attackSrc.getSelectedItem();
                if (src.equals("...")) {
                    return;
                } else {
                    Map<String, Integer> unitINFO = allUnits.get(src);
                    for (Map.Entry<String, Integer> entry : unitINFO.entrySet()) {
                        String unitName = entry.getKey();
                        attackUnitLevel.addItem(unitName);
                    }
                }
            }
        });
        attackNum.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                attackNum.removeAllItems();
                attackNum.addItem("...");
                String src = (String) attackSrc.getSelectedItem();
                String unitName = (String) attackUnitLevel.getSelectedItem();
                if (src.equals("...") || unitName.equals("...")) {
                    return;
                } else {
                    Map<String, Integer> unitINFO = allUnits.get(src);
                    int unitNum = unitINFO.get(unitName);
                    for (int i = 1; i <= unitNum; ++i) {
                        attackNum.addItem(i);
                    }
                }
            }
        });
        moveUnitLevel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                moveUnitLevel.removeAllItems();
                moveUnitLevel.addItem("...");
                String src = (String) moveSrc.getSelectedItem();
                if (src.equals("...")) {
                    return;
                } else {
                    Map<String, Integer> unitINFO = allUnits.get(src);
                    for (Map.Entry<String, Integer> entry : unitINFO.entrySet()) {
                        String unitName = entry.getKey();
                        moveUnitLevel.addItem(unitName);
                    }
                }
            }
        });
        moveNum.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                moveNum.removeAllItems();
                moveNum.addItem("...");
                String src = (String) moveSrc.getSelectedItem();
                String unitName = (String) moveUnitLevel.getSelectedItem();
                if (src.equals("...") || unitName.equals("...")) {
                    return;
                } else {
                    Map<String, Integer> unitINFO = allUnits.get(src);
                    int unitNum = unitINFO.get(unitName);
                    for (int i = 1; i <= unitNum; ++i) {
                        moveNum.addItem(i);
                    }
                }
            }
        });
        upgradeUnitLevel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                upgradeUnitLevel.removeAllItems();
                upgradeUnitLevel.addItem("...");
                String src = (String) upgradeSrc.getSelectedItem();
                if (src.equals("...")) {
                    return;
                } else {
                    Map<String, Integer> unitINFO = allUnits.get(src);
                    for (Map.Entry<String, Integer> entry : unitINFO.entrySet()) {
                        String unitName = entry.getKey();
                        upgradeUnitLevel.addItem(unitName);
                    }
                }
            }
        });
        upgradeNum.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                upgradeNum.removeAllItems();
                upgradeNum.addItem("...");
                String src = (String) upgradeSrc.getSelectedItem();
                String unitName = (String) upgradeUnitLevel.getSelectedItem();
                if (src.equals("...") || unitName.equals("...")) {
                    return;
                } else {
                    Map<String, Integer> unitINFO = allUnits.get(src);
                    int unitNum = unitINFO.get(unitName);
                    for (int i = 1; i <= unitNum; ++i) {
                        upgradeNum.addItem(i);
                    }
                }
            }
        });
        upgradeTargetLevel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                upgradeTargetLevel.removeAllItems();
                upgradeTargetLevel.addItem("...");
                String currLevel = (String) upgradeUnitLevel.getSelectedItem();
                if (currLevel.equals("...")) {
                    return;
                } else {
                    boolean find = false;
                    for (String level : levels) {
                        if (level.equals(currLevel)) {
                            find = true;
                        } else if (find) {
                            upgradeTargetLevel.addItem(level);
                        }
                    }
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
        JFrame frame = new JFrame("playRoom");
        String color = initString.substring(1, initString.indexOf(')'));
        initString = initString.substring(initString.indexOf(')') + 1);
        String isEnd = currClient.check(initString);
        if (isEnd != null) {
            endWindow.main(isEnd);
            return;
        }
        Player player = serialize.PlayerDeserialization(color, initString);
        serialize.updateAll(player.getAllTerrAndUnits(), initString);
        if (player.getTerritories().isEmpty()) {
            loseWindow.main(currClient, initString);
            return;
        }
        frame.setContentPane(new playRoom(player, frame, currClient).root);
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
        root = new JPanel();
        root.setLayout(new GridLayoutManager(12, 6, new Insets(0, 0, 0, 0), -1, -1));
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
        root.add(Hogwarts, new GridConstraints(3, 3, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Gondor = new JButton();
        Gondor.setText("Gondor");
        root.add(Gondor, new GridConstraints(0, 5, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Oz = new JButton();
        Oz.setText("Oz");
        root.add(Oz, new GridConstraints(0, 2, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Midkemia = new JButton();
        Midkemia.setText("Midkemia");
        root.add(Midkemia, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Mordor = new JButton();
        Mordor.setText("Mordor");
        root.add(Mordor, new GridConstraints(2, 3, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Source Territory");
        root.add(label1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attackSrc = new JComboBox();
        root.add(attackSrc, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attackUnitLevel = new JComboBox();
        root.add(attackUnitLevel, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moveSrc = new JComboBox();
        root.add(moveSrc, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moveUnitLevel = new JComboBox();
        root.add(moveUnitLevel, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        finishButton = new JButton();
        finishButton.setText("Finish");
        root.add(finishButton, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        finishButton1 = new JButton();
        finishButton1.setText("Finish");
        root.add(finishButton1, new GridConstraints(8, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        finishButton2 = new JButton();
        finishButton2.setText("Finish");
        root.add(finishButton2, new GridConstraints(10, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgradeSrc = new JComboBox();
        root.add(upgradeSrc, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgradeUnitLevel = new JComboBox();
        root.add(upgradeUnitLevel, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgradeTargetLevel = new JComboBox();
        root.add(upgradeTargetLevel, new GridConstraints(10, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attackLabel = new JLabel();
        attackLabel.setText("Attack");
        root.add(attackLabel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moveLabel = new JLabel();
        moveLabel.setText("Move");
        root.add(moveLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgradeLabel = new JLabel();
        upgradeLabel.setText("Upgrade");
        root.add(upgradeLabel, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        commitButton = new JButton();
        commitButton.setText("Commit");
        root.add(commitButton, new GridConstraints(11, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Source Territory");
        root.add(label2, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Original Unit Level");
        root.add(label3, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Number of Units");
        root.add(label4, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Target Unit Level");
        root.add(label5, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgradeNum = new JComboBox();
        root.add(upgradeNum, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        levelLabel = new JLabel();
        levelLabel.setText("Units Level");
        root.add(levelLabel, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Number of Units");
        root.add(label6, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Target Territory");
        root.add(label7, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attackDst = new JComboBox();
        root.add(attackDst, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attackNum = new JComboBox();
        root.add(attackNum, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moveNum = new JComboBox();
        root.add(moveNum, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moveDst = new JComboBox();
        root.add(moveDst, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalResource = new JLabel();
        totalResource.setText("Label");
        root.add(totalResource, new GridConstraints(5, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

}
