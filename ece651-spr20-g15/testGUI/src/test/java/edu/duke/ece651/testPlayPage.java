package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class testPlayPage {
    @Test
    public void testCheck() throws IOException {

        try{
            ServerSocket ss = new ServerSocket(6666);
            Client currClient = new Client("localhost", 6666);
            String initString = "2(Blue)Green^Food$5000^-Narnia+(Private_1+Corporal_0+Sergent_0+Lieutenant_0+Captain_0+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Blue^Food$50000^-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)";
            JFrame frame = new JFrame("playRoom");
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
            playPage testRoom = new playPage(player, frame, currClient);
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

            JLabel cmd = testRoom.getCmd();
            JComboBox Src = testRoom.getSrcCombo();
            JComboBox Type = testRoom.getTypeCombo();
            JComboBox Num = testRoom.getNumCombo();
            JComboBox Dst = testRoom.getDstCombo();

            // Test Attack
            JButton attack = testRoom.getAttack();
            //attack.doClick();
            cmd.setText("Attack");
            //attack.doClick();
            Src.requestFocus();
            Src.setSelectedItem("Midkemia");
            Type.requestFocus();
            Type.setSelectedItem("Private");
            Num.requestFocus();
            Num.setSelectedItem(1);
            Dst.requestFocus();
            Dst.setSelectedItem("Narnia");

            //Test Move
            JButton move = testRoom.getMove();
            //move.doClick();
            Src.requestFocus();
            Src.setSelectedItem("Midkemia");
            Type.requestFocus();
            Type.setSelectedItem("Private");
            Num.requestFocus();
            Num.setSelectedItem(1);
            Dst.requestFocus();
            Dst.setSelectedItem("Oz");

            // Test Upgrade normally
            JButton upgrade = testRoom.getUpgrade();
            //upgrade.doClick();
            Src.requestFocus();
            Src.setSelectedItem("Midkemia");
            Type.requestFocus();
            Type.setSelectedItem("Private");
            Num.requestFocus();
            Num.setSelectedItem(1);
            Dst.requestFocus();
            Dst.setSelectedItem("Major");

            // Test Upgrade Spy
            JButton upgrade1 = testRoom.getUpgrade();
            //upgrade1.doClick();
            Src.requestFocus();
            Src.setSelectedItem("Midkemia");
            Type.requestFocus();
            Type.setSelectedItem("Private");
            Num.requestFocus();
            Num.setSelectedItem(1);
            Dst.requestFocus();
            Dst.setSelectedItem("Spy");

            // Test Cloak
            JButton cloak = testRoom.getCloak();
            JComboBox cloakCombo = testRoom.getCloakCombo();
            cloakCombo.requestFocus();
            cloakCombo.setSelectedItem("Oz");
            //cloak.doClick();

            frame.dispose();
            ss.close();
        }catch (IOException e) {
            System.out.println("fail");
        }
    }
}
