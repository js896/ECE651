package edu.duke.ece651;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This function is the child class of server and also the main part of
// this game's server part.
public class GamePlay extends Server {
    // The number of players in current game
    private int playerNum;
    // Timeout for new players when current player number is between 2 and 5
    private int timeOut;
    // All player client in current game
    List<Client> clients = new ArrayList<>();
    // All players' information, used to verify the received player's information
    Map<String, Player> players = new HashMap<>();
    // All players' color
    private String[] colors = {"Green", "Blue", "Red", "Yellow", "Orange"};

    // Default constructor
    public GamePlay() throws IOException {
        currServer = new ServerSocket(6666);
        playerNum = 0;
        timeOut = 10000;
    }

    // Close server socket
    public void close() throws IOException {
        currServer.close();
    }

    // Print all player's IP address
    public void start(Socket inputSocket) {
        String currIP = inputSocket.getInetAddress().getHostAddress();
        System.out.println("IP: " + currIP + " connected");
    }

    // Part 1: Initialize the game
    public boolean init() throws IOException {
        while (true) {
            // When current players are less than 2, wait until at least 2
            // players join this game
            if (playerNum < 2) {
                Socket clientSocket = currServer.accept();
                Client newClient = new Client(clientSocket);
                this.Send(newClient.ClientSocket, colors[playerNum]);
                clients.add(newClient);
                start(clientSocket);
                ++playerNum;
            }
            // When current players are more than 2, set the timeout.
            // Start the game when no new player join the game in the
            // time we set.
            else {
                try {
                    currServer.setSoTimeout(timeOut);
                    Socket clientSocket = currServer.accept();
                    Client newClient = new Client(clientSocket);
                    this.Send(newClient.ClientSocket, colors[playerNum]);
                    clients.add(newClient);
                    start(clientSocket);
                    ++playerNum;
                } catch (SocketTimeoutException e) {
                    System.out.println("Time out for new players! Now start the game!");
                    break;
                }
            }
            // Max players allowed are 5.
            if (playerNum == 5) {
                break;
            }
        }
        // Send the init information of this game to all players
        for (int i = 0; i < clients.size(); ++i) {
            StringBuilder currSB = setGame(playerNum);
            currSB.insert(0, "(" + colors[i] + ")");
            currSB.insert(0, clients.size());
            try {
                this.Send(clients.get(i).ClientSocket, currSB.toString());
            }
            // If one player disconnects, end this game.
            catch (SocketException e) {
                System.out.println("player " + colors[i] + " disconnected. Game ends.");
                return false;
            }
        }
        return true;
    }
    //%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)

    // Set the initial players' information based on the players' number
    public StringBuilder setGame(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 2) {
            sb.append("Green^Food$5000^-Narnia+(Private_1+Corporal_0+Sergent_0+Lieutenant_0+Captain_0+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Blue^Food$50000^-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)");
        } else if (num == 3) {
            sb.append("Green^Food$50^-Narnia+(Private_1+Corporal_0+Sergent_0+Lieutenant_0+Captain_0+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Blue^Food$50^-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)"+
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)"+
                    "*Red^Food$50^-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)"+
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)");
        } else if (num == 4) {
            sb.append("Green^Food$50^-Narnia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                            "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)"+
                    "*Blue^Food$50^-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)"+
                            "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Red^Food$50^-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                            "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Yellow^Food$50^-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)"+
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)");
        } else if (num == 5) {
            sb.append("Green^Food$50^-Narnia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Elantris+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Blue^Food$50^-Midkemia+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Scadrial+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Red^Food$50^-Gondor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)" +
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)"+
                    "*Yellow^Food$50^-Roshar+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Oz+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)"+
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)" +
                    "*Orange^Food$50^-Mordor+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)-Hogwarts+(Private_7+Corporal_2+Sergent_0+Lieutenant_0+Captain_1+Major_0+Colonel_0)"+
                    "%(Narnia-[]-0)+(Elantris-[]-0)+(Midkemia-[]-0)+(Scadrial-[]-0)+(Gondor-[]-0)+(Roshar-[]-0)+(Oz-[]-0)+(Mordor-[]-0)+(Hogwarts-[]-0)");
        }
        // Update and store all players
        serialize.updateAll(players, sb.toString());
        SetInfo.setInfo(num);
        return sb;
    }

    // Check if current game is end or not
    public boolean check(String input) {
        input = input.substring(0, 3);
        if (input.equals("end")) {
            return true;
        } else {
            return false;
        }
    }


    // Check and reset the message if the game is end
    public String checkEnd(String msg) {
        if (!msg.contains("*")) {
            StringBuilder ans = new StringBuilder("end-");
            String color = msg.substring(0, msg.indexOf('^'));
            ans.append(color);
            return ans.toString();
        }
        return msg;
    }

    // Part 2: Players play the game
    public void play() throws IOException {
        boolean isEnd = false;
        while (!isEnd) {
            StringBuilder totalMsg = new StringBuilder();
            for (int i = 0; i < clients.size(); ++i) {
                try {
                    // If current player doesn't lose
                    if (players.containsKey(colors[i])) {
                        // Receive the message from current player
                        String MsgFromPlayer = Recv(clients.get(i).ClientSocket);
                        System.out.println(MsgFromPlayer);
//                        Player currPlayer = serialize.PlayerDeserialization(colors[i], MsgFromPlayer);
//                        // Check if the message sent by current player is correct
//                        if (!checkValid(currPlayer)) {
//                            continue;
//                        }
                        // Join every player's message together
                        if (totalMsg.length() == 0) {
                            totalMsg.append(MsgFromPlayer);
                        } else {
                            totalMsg.append("*" + MsgFromPlayer);
                        }
                    }
                }
                // End the game if one player disconnect
                catch (SocketException e) {
                    System.out.println("player " + colors[i] + " disconnected. Game ends.");
                    return;
                }
            }
            // Execute this total message
            System.out.println(totalMsg.toString());
            String MsgAfterExe = Action.Execute(totalMsg.toString());
            // Check if we need to reset the message
            MsgAfterExe = checkEnd(MsgAfterExe);
            // Update all players we stored before
            System.out.println("~~~~~~~~~MSG~~~~~~~~~~~~~~");
            System.out.println(MsgAfterExe);
            serialize.updateAll(players, MsgAfterExe);
            // Send current turn's result to all players
            for (int i = 0; i < clients.size(); ++i) {
                StringBuilder currPlayerInfo = new StringBuilder(MsgAfterExe);
                currPlayerInfo.insert(0, "(" + colors[i] + ")");
                try {
                    Send(clients.get(i).ClientSocket, currPlayerInfo.toString());
                } catch (SocketException e) {
                    System.out.println("player " + colors[i] + " disconnected. Game ends.");
                    return;
                }
            }
            // Check current game's status
            isEnd = check(MsgAfterExe);
            System.out.println(isEnd);
        }
    }

    public static void main(String[] args) throws IOException {
        GamePlay test = new GamePlay();
        if (test.init()) {
            test.play();
            test.close();
        }
    }

}
