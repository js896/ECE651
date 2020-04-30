package edu.duke.ece651;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import edu.duke.ece651.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class playPage {
    private JLabel SrcImg;
    private BackgroudPanel Map;
    private JPanel root;
    private JButton attack;
    private JButton Narnia;
    private JButton Elantris;
    private JButton Midkemia;
    private JButton Scadrial;
    private JButton Roshar;
    private JButton Oz;
    private JButton Gondor;
    private JButton Mordor;
    private JButton Hogwarts;
    private JPanel src;
    private JPanel dst;
    private JLabel DstImg;
    private JButton move;
    private JButton upgrade;
    private JLabel Src;
    private JLabel Dst;
    private JLabel type;
    private JLabel num;
    private JLabel Terriinfo;
    private JButton commit;
    private JButton finish;
    private JLabel tip;
    private JComboBox srcCombo;
    private JComboBox typeCombo;
    private JComboBox numCombo;
    private JComboBox dstCombo;
    private JLabel cmd;
    private JButton cloak;
    private JPanel cloakPanel;
    private JLabel cloakInfo;
    private JComboBox cloakCombo;
    private JLabel Playerinfo;
    private Image OriginalMap = new ImageIcon(getClass().getResource("/Map.jpg")).getImage();

    private JFrame waitF;
    private String action = "";
    private String[] levels = {"Private", "Corporal", "Sergent", "Lieutenant", "Captain", "Major", "Colonel"};
    private Map<String, Map<String, Integer>> allUnits = new HashMap<>();
    protected Client client;

    public JButton getNarnia() {
        return Narnia;
    }

    public JButton getElantris() {
        return Elantris;
    }

    public JButton getMidkemia() {
        return Midkemia;
    }

    public JButton getScadrial() {
        return Scadrial;
    }

    public JButton getRoshar() {
        return Roshar;
    }

    public JButton getOz() {
        return Oz;
    }

    public JButton getGondor() {
        return Gondor;
    }

    public JButton getMordor() {
        return Mordor;
    }

    public JButton getHogwarts() {
        return Hogwarts;
    }

    public JPanel getRoot() {
        return root;
    }

    public JButton getAttack() {
        return attack;
    }

    public JButton getMove() {
        return move;
    }

    public JButton getUpgrade() {
        return upgrade;
    }

    public JLabel getSrcImg() {
        return SrcImg;
    }

    public JComboBox getSrcCombo() {
        return srcCombo;
    }

    public JComboBox getTypeCombo() {
        return typeCombo;
    }

    public JComboBox getNumCombo() {
        return numCombo;
    }

    public JComboBox getDstCombo() {
        return dstCombo;
    }

    public JLabel getCmd() {
        return cmd;
    }

    public JButton getCloak() {
        return cloak;
    }

    public JComboBox getCloakCombo() {
        return cloakCombo;
    }

    public void setAM() {
        Src.setText("Source Territory");
        Dst.setText("Destination Territory");
        type.setText("Units Level");
        num.setText("Units Number");
    }

    public void setPlayerinfoColor(Player player) {
        String color = player.getColor();
        if (color.equals("Green")) {
            Playerinfo.setBackground(new Color(-1245204));
        } else if (color.equals("Blue")) {
            Playerinfo.setBackground(new Color(100,149,237));
        } else if (color.equals("Red")) {
            Playerinfo.setBackground(new Color(	205, 92, 92));
        } else if (color.equals("Yellow")) {
            Playerinfo.setBackground(new Color(255, 250, 205));
        } else if (color.equals("Orange")) {
            Playerinfo.setBackground(new Color(255, 165, 0));
        } else {
            Playerinfo.setBackground(Color.black);
        }
        Playerinfo.setOpaque(true);
    }

    public void setInfos() {
        Map.add(Narnia, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Scadrial, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Elantris, new GridConstraints(1, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Roshar, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Oz, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Hogwarts, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Gondor, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Midkemia, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Map.add(Mordor, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    public void setUp() {
        Src.setText("Source Territory");
        Dst.setText("Target Unit Level");
        type.setText("Original Units Level");
        num.setText("Units Number");
    }

    public void setSrcCombo(Player player) {
        srcCombo.removeAllItems();
        srcCombo.addItem("...");
        srcCombo.setSelectedItem("...");
        if (cmd.getText().equals("null")) {
            return;
        } else if (cmd.getText().equals("Attack") || cmd.getText().equals("Upgrade")) {
            for (Map.Entry<String, Map<String, Integer>> entry: player.getCurrTerrAndUnits().entrySet()) {
                String tm = entry.getKey();
                srcCombo.addItem(tm);
            }
        } else if (cmd.getText().equals("Move")) {
            Set<String> moveSrc = player.canMoveTerr();
            for (Iterator it = moveSrc.iterator(); it.hasNext(); ) {
                String tm = (String)it.next();
                srcCombo.addItem(tm);
            }

        }
    }

    public void setTypeCombo(Player player) {
        typeCombo.removeAllItems();
        typeCombo.addItem("...");
        typeCombo.setSelectedItem("...");
        String src = (String)srcCombo.getSelectedItem();
        if (cmd.getText().equals("null") || src.equals("...")) {
            return;
        } else if (allUnits.containsKey(src)) {
            Map<String, Integer> unitINFO = allUnits.get(src);
            for (Map.Entry<String, Integer> entry : unitINFO.entrySet()) {
                String unitName = entry.getKey();
                typeCombo.addItem(unitName);
            }
        }
        if (cmd.getText().equals("Move")) {
            String terrInfo = player.getTerritoryInfo(src);
            if (terrInfo.contains("Spy")) {
                typeCombo.addItem("Spy");
            }
        }
    }

    public void setNumCombo(Player player) {
        numCombo.removeAllItems();
        numCombo.addItem("...");
        numCombo.setSelectedItem("...");
        String src = (String)srcCombo.getSelectedItem();
        String type = (String)typeCombo.getSelectedItem();
        if (src.equals("...") || type.equals("...") || cmd.getText().equals("null")) {
            return;
        } else if ((cmd.getText().equals("Attack") || cmd.getText().equals("Upgrade")) || (cmd.getText().equals("Move") && !type.equals("Spy"))) {
            Map<String, Integer> unitINFO = allUnits.get(src);
            int unitNum = unitINFO.get(type);
            for (int i = 1; i <= unitNum; ++i) {
                numCombo.addItem(i);
            }
        } else {
            Map<String, Map<String, Territory>> allT = serialize.terrInfo.playerAndTerrs;
            Territory currT = allT.get(player.getColor()).get(src);
            Map<String, Integer> spies = currT.getAllSpiesOnCurrTerr();
            int num = spies.get(player.getColor());
            for (int i = 1; i <= num; ++i) {
                numCombo.addItem(i);
            }
        }
    }

    public void setDstCombo(Player player) {
        dstCombo.removeAllItems();
        dstCombo.addItem("...");
        dstCombo.setSelectedItem("...");
        String src = (String)srcCombo.getSelectedItem();
        String type = (String)typeCombo.getSelectedItem();
        if (src.equals("...") || type.equals("...") || cmd.getText().equals("null") || num.equals("...")) {
            return;
        } else if (cmd.getText().equals("Attack")) {
            List<String> adjacentList = player.getTm().allTerritories.get(src);
            Map<String, Map<String, Integer>> ownMap = player.getCurrTerrAndUnits();
            for (String currTerritory : adjacentList) {
                if (!ownMap.containsKey(currTerritory)) {
                    dstCombo.addItem(currTerritory);
                }
            }
        } else if (cmd.getText().equals("Move")) {
            if (type.equals("Spy")) {
                Set<String> dsts = player.canSpyMoveDstTerr(src);
                for (Iterator it = dsts.iterator(); it.hasNext(); ) {
                    String dstT = (String)it.next();
                    dstCombo.addItem(dstT);
                }
            } else {
                Set<String> dsts = player.canMoveDstTerr(src);
                for (Iterator it = dsts.iterator(); it.hasNext(); ) {
                    String dstT = (String)it.next();
                    dstCombo.addItem(dstT);
                }
            }
        } else {
            if (!type.equals("Spy")) {
                boolean find = false;
                for (String level : levels) {
                    if (level.equals(type)) {
                        find = true;
                    } else if (find) {
                        dstCombo.addItem(level);
                    }
                }
                dstCombo.addItem("Spy");
            }
        }
    }

    public void setCloakCombo(Player player) {
        cloakCombo.removeAllItems();
        cloakCombo.addItem("...");
        cloakCombo.setSelectedItem("...");
        for (Map.Entry<String, Map<String, Integer>> entry: player.getCurrTerrAndUnits().entrySet()) {
            String tm = entry.getKey();
            cloakCombo.addItem(tm);
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

    public playPage(Player player, JFrame currFrame, Client currClient) {
        $$$setupUI$$$();
        client = currClient;
        setPlayerinfoColor(player);
        setSrcCombo(player);
        setCloakCombo(player);
        allUnits = deepCopy(player.getCurrTerrAndUnits());
        for (Iterator<Resource> it = player.getTotalResource().iterator(); it.hasNext(); ) {
            Resource currRes = it.next();
            Playerinfo.setText("You are " + player.getColor() + " Player. " + "Your current round has " + currRes.getNum() + " " + currRes.getType() + " in total.");

        }
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setAM();
                cmd.setText("Attack");
                setSrcCombo(player);
                setTypeCombo(player);
                setNumCombo(player);
                setDstCombo(player);
            }
        });
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setAM();
                cmd.setText("Move");
                setSrcCombo(player);
                setTypeCombo(player);
                setNumCombo(player);
                setDstCombo(player);
            }
        });
        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setUp();
                cmd.setText("Upgrade");
                setSrcCombo(player);
                setTypeCombo(player);
                setNumCombo(player);
                setDstCombo(player);
            }
        });
        cloak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String src = (String)cloakCombo.getSelectedItem();
                if (src.equals("...")) {
                    cloakInfo.setText("Please choose a correct territory.");
                } else {
                    player.setCloak(src);
                }
                setCloakCombo(player);
            }
        });
        Narnia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Narnia");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Narnia.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Narnia.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Elantris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Elantris");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Elantris.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Elantris.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Oz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Oz");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Oz.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Oz.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Gondor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Gondor");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Gondor.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Gondor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Mordor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Mordor");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Mordor.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Mordor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Midkemia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Midkemia");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Midkemia.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Midkemia.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Scadrial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Scadrial");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Scadrial.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Scadrial.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Roshar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Roshar");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Roshar.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Roshar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        Hogwarts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String info = player.getTerritoryInfo("Hogwarts");
                Terriinfo.setText(info);
                Image newBG = new ImageIcon(getClass().getResource("/Map_Hogwarts.jpg")).getImage();
                Map.changeBG(newBG);
            }
        });
        Hogwarts.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Map.changeBG(OriginalMap);
            }
        });
        srcCombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setSrcCombo(player);
            }
        });
        srcCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                StringBuilder name = new StringBuilder("/");
                if (srcCombo.getSelectedItem() == null) {
                    return;
                }
                if (!srcCombo.getSelectedItem().equals("...")) {
                    name.append((String) srcCombo.getSelectedItem());
                } else {
                    name.append("null");
                }
                name.append(".jpg");
                SrcImg.setIcon(new ImageIcon(getClass().getResource(name.toString())));
                //setTypeCombo(player);
            }
        });
        typeCombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setTypeCombo(player);
            }
        });
        typeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (cmd.getText().equals("Upgrade")) {
                    StringBuilder name = new StringBuilder("/");
                    if (typeCombo.getSelectedItem() == null) {
                        return;
                    }
                    if (!typeCombo.getSelectedItem().equals("...")) {
                        name.append((String) typeCombo.getSelectedItem());
                    } else {
                        name.append("null");
                    }
                    name.append(".jpg");
                    SrcImg.setIcon(new ImageIcon(getClass().getResource(name.toString())));
                }
            }
        });
        numCombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setNumCombo(player);
            }
        });
        dstCombo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                setDstCombo(player);
            }
        });
        dstCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                StringBuilder name = new StringBuilder("/");
                if (dstCombo.getSelectedItem() == null) {
                    return;
                }
                if (!dstCombo.getSelectedItem().equals("...")) {
                    name.append((String) dstCombo.getSelectedItem());
                } else {
                    name.append("null");
                }
                name.append(".jpg");
                DstImg.setIcon(new ImageIcon(getClass().getResource(name.toString())));
            }
        });

        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!cmd.getText().equals("null") && !srcCombo.getSelectedItem().equals("...") && !typeCombo.getSelectedItem().equals("...") && !numCombo.getSelectedItem().equals("...") && !dstCombo.getSelectedItem().equals("...")) {
                    if (action != "") {
                        action += "@";
                    }
                    action += cmd.getText() + "~";
                    if (cmd.getText().equals("Attack") || cmd.getText().equals("Move")) {
                        action += typeCombo.getSelectedItem() + "~" + numCombo.getSelectedItem() + "~" + srcCombo.getSelectedItem() + "~" + dstCombo.getSelectedItem();
                    } else if (cmd.getText().equals("Upgrade")) {
                        action += srcCombo.getSelectedItem() + "~" + typeCombo.getSelectedItem() + "~" + numCombo.getSelectedItem() + "~" + dstCombo.getSelectedItem();
                    }
                    cmd.setText("null");
                    setSrcCombo(player);
                    setTypeCombo(player);
                    setNumCombo(player);
                    setDstCombo(player);
                }
            }
        });

        finish.addActionListener(new ActionListener() {
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
                    playPage.main(currClient, initString);
                    waitF.setVisible(false);
                } catch (InterruptedException e) {
                    System.out.println("fail to receive");
                } catch (IOException e) {
                    System.out.println("fail to receive");
                }
            }
        });
    }

    public static void main(Client currClient, String initString) {
        JFrame frame = new JFrame("playPage");
        if (!initString.substring(0, 1).equals("(")) {
            System.out.println("____________init Info___________");
            System.out.println(initString);
            int num = Integer.parseInt(initString.substring(0, 1));
            initString = initString.substring(initString.indexOf("("));
            SetInfo.setInfo(num);
        }
        String color = initString.substring(1, initString.indexOf(')'));
        initString = initString.substring(initString.indexOf(')') + 1);
        String isEnd = currClient.check(initString);
        if (isEnd != null) {
            endWindow.main(isEnd);
            return;
        }
        Player player = serialize.PlayerDeserialization(color, initString);
        //String[] infos = initString.split("%");
        //String playerInfo = infos[0];
        serialize.updateAll(player.getAllTerrAndUnits(), initString);
        //update all territories
        serialize.updateAllTerritory(player, initString);
        //set terrInfo
        player.setTerrInfo();

        if (player.getTerritories().isEmpty()) {
            loseWindow.main(currClient, initString);
            return;
        }
        frame.setContentPane(new playPage(player, frame, currClient).root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        root = new JPanel();
        root.setLayout(new GridLayoutManager(7, 8, new Insets(0, 0, 0, 0), -1, -1));
        root.setBackground(new Color(-1));
        Font rootFont = this.$$$getFont$$$("Apple Chancery", -1, -1, root.getFont());
        if (rootFont != null) root.setFont(rootFont);
        root.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        Map.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1));
        root.add(Map, new GridConstraints(0, 0, 2, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(674, 486), new Dimension(674, 486), new Dimension(674, 486), 0, false));
        Narnia = new JButton();
        Narnia.setHorizontalTextPosition(11);
        Narnia.setLabel("info");
        Narnia.setText("info");
        Scadrial = new JButton();
        Scadrial.setText("info");
        Elantris = new JButton();
        Elantris.setText("info");
        Roshar = new JButton();
        Roshar.setText("info");
        Oz = new JButton();
        Oz.setText("info");
        Hogwarts = new JButton();
        Hogwarts.setText("info");
        Gondor = new JButton();
        Gondor.setText("info");
        Midkemia = new JButton();
        Midkemia.setText("info");
        Mordor = new JButton();
        Mordor.setText("info");
        setInfos();
        //src = new JPanel();
        src.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        //src.setBackground(new Color(-1245204));
        root.add(src, new GridConstraints(2, 0, 5, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, 120), new Dimension(120, 120), new Dimension(120, 120), 0, false));
        SrcImg = new JLabel();
        SrcImg.setAlignmentY(0.0f);
        SrcImg.setIcon(new ImageIcon(getClass().getResource("/Null.jpg")));
        SrcImg.setText("");
        src.add(SrcImg, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), new Dimension(100, 100), 0, false));
        //dst = new JPanel();
        dst.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        //dst.setBackground(new Color(-1245204));
        root.add(dst, new GridConstraints(2, 6, 5, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(120, 120), new Dimension(120, 120), new Dimension(120, 120), 0, false));
        DstImg = new JLabel();
        DstImg.setIcon(new ImageIcon(getClass().getResource("/Null.jpg")));
        DstImg.setText("");
        dst.add(DstImg, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, 100), new Dimension(100, 100), new Dimension(100, 100), 0, false));
        attack.setIcon(new ImageIcon(getClass().getResource("/attack.jpg")));
        root.add(attack, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        move.setIcon(new ImageIcon(getClass().getResource("/move.jpg")));
        root.add(move, new GridConstraints(2, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        upgrade.setIcon(new ImageIcon(getClass().getResource("/upgrade.jpg")));
        root.add(upgrade, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Terriinfo = new JLabel();
        Terriinfo.setText("Label");
        root.add(Terriinfo, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        commit.setIcon(new ImageIcon(getClass().getResource("/commit.jpg")));
        root.add(commit, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        finish.setIcon(new ImageIcon(getClass().getResource("/finish.jpg")));
        root.add(finish, new GridConstraints(5, 4, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tip = new JLabel();
        tip.setText("Press left \"commit\" button to commit current action order, press right \"finish\" button to finish current round.");
        root.add(tip, new GridConstraints(6, 1, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Src = new JLabel();
        Src.setText("Source");
        root.add(Src, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        type = new JLabel();
        type.setText("Type");
        root.add(type, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        num = new JLabel();
        num.setText("Number");
        root.add(num, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Dst = new JLabel();
        Dst.setText("Destination");
        root.add(Dst, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        srcCombo = new JComboBox();
        root.add(srcCombo, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeCombo = new JComboBox();
        root.add(typeCombo, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numCombo = new JComboBox();
        root.add(numCombo, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dstCombo = new JComboBox();
        root.add(dstCombo, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cmd = new JLabel();
        cmd.setText("null");
        cmd.setVisible(false);
        root.add(cmd, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cloakPanel = new JPanel();
        cloakPanel.setBackground(new Color(-1));
        cloakPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        root.add(cloakPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        //cloak = new JButton();
        cloak.setIcon(new ImageIcon(getClass().getResource("/ghost.jpg")));
        cloakPanel.add(cloak, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cloakInfo = new JLabel();
        cloakInfo.setText("select a territory to cloak");
        cloakPanel.add(cloakInfo, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cloakCombo = new JComboBox();
        cloakPanel.add(cloakCombo, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Playerinfo = new JLabel();
        Playerinfo.setText("Current player's infomation");
        root.add(Playerinfo, new GridConstraints(1, 0, 1, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Image map_image = new ImageIcon(getClass().getResource("/Map.jpg")).getImage();
        Image root_background = new ImageIcon(getClass().getResource("/images.jpg")).getImage();
        Image back = new ImageIcon(getClass().getResource("/commit.jpg")).getImage();
        src = new BackgroudPanel(root_background);
        dst = new BackgroudPanel(root_background);
        //root = new BackgroudPanel(back);
        Map = new BackgroudPanel(map_image);
        attack = new CircleButton("");
        upgrade = new CircleButton("");
        move = new CircleButton("");
        commit = new CircleButton("");
        finish = new CircleButton("");
        cloak = new CircleButton("");
    }
}
