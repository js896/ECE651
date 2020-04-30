package edu.duke.ece651;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class testPlayRoom {
    @Test
    public void testCheck() throws IOException {

                try{
                    ServerSocket ss = new ServerSocket(6666);
                    Client currClient = new Client("localhost", 6666);
                    String initString = "Green^Food$5000^-Narnia+(Private_1+Corporal_0+Sergent_0+Lieutenant_0+Captain_0+Major_0+Colonel_0)" +
                            "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                            "*Blue^Food$50000^-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                            "-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                            "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)";
                    JFrame frame = new JFrame("playRoom");
                    Player player = serialize.PlayerDeserialization("Blue", initString);
                    System.out.println("here");
                    playRoom testRoom = new playRoom(player, frame, currClient);
                    serialize.updateAll(player.getAllTerrAndUnits(), initString);
                    frame.setContentPane(testRoom.getRoot());

                    // Test Read Territory
                    JButton ReadElantris = testRoom.getElantris();
                    JButton ReadOz = testRoom.getOz();
                    JButton ReadHogwarts = testRoom.getHogwarts();
                    JButton ReadGondor = testRoom.getGondor();
                    JButton ReadMordor = testRoom.getMordor();
                    JButton ReadScadrial = testRoom.getScadrial();
                    JButton ReadMidkemia = testRoom.getMidkemia();
                    JButton ReadNarinia = testRoom.getNarnia();
                    JButton ReadRoshar = testRoom.getRoshar();
                    ReadElantris.doClick();
                    ReadOz.doClick();
                    ReadGondor.doClick();
                    ReadMidkemia.doClick();
                    ReadHogwarts.doClick();
                    ReadMordor.doClick();
                    ReadScadrial.doClick();
                    ReadNarinia.doClick();
                    ReadRoshar.doClick();

                    // Test Attack Order
                    JComboBox attackSrc = testRoom.getAttackSrc();
                    JComboBox attackLevel = testRoom.getAttackUnitLevel();
                    JComboBox attackNum = testRoom.getAttackNum();
                    JComboBox attackDst = testRoom.getAttackDst();
                    attackSrc.setSelectedItem("Midkemia");
                    attackDst.addItem("Narnia");
                    attackDst.setSelectedItem("Narinia");
                    attackLevel.addItem("Private");
                    attackLevel.setSelectedItem("Private");
                    attackNum.addItem(1);
                    attackNum.setSelectedItem(1);
                    JButton attack = testRoom.getFinishButton();
                    attack.doClick();

                    // Test Move Order
                    JComboBox moveSrc = testRoom.getMoveSrc();
                    JComboBox moveLevel = testRoom.getMoveUnitLevel();
                    JComboBox movekNum = testRoom.getMoveNum();
                    JComboBox moveDst = testRoom.getMoveDst();
                    moveSrc.setSelectedItem("Midkemia");
                    moveDst.addItem("Oz");
                    moveDst.setSelectedItem("Oz");
                    moveLevel.addItem("Private");
                    moveLevel.setSelectedItem("Private");
                    movekNum.addItem(1);
                    movekNum.setSelectedItem(1);
                    JButton move = testRoom.getFinishButton1();
                    move.doClick();

                    // Test Upgrade Order
                    JComboBox upgradeSrc = testRoom.getUpgradeSrc();
                    JComboBox upgradeLevel = testRoom.getUpgradeUnitLevel();
                    JComboBox upgradeNum = testRoom.getUpgradeNum();
                    JComboBox upgradeDst = testRoom.getUpgradeTargetLevel();
                    upgradeSrc.setSelectedItem("Midkemia");
                    upgradeDst.addItem("Major");
                    upgradeDst.setSelectedItem("Major");
                    upgradeLevel.addItem("Private");
                    upgradeLevel.setSelectedItem("Private");
                    upgradeNum.addItem(1);
                    upgradeNum.setSelectedItem(1);
                    JButton upgrade = testRoom.getFinishButton2();
                    upgrade.doClick();

                    frame.dispose();
                    ss.close();
                }catch (IOException e) {
                    System.out.println("fail");
                }
            }

    }

