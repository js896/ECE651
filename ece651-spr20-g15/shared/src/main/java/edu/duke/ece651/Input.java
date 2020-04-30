//package edu.duke.ece651;
//
//
//import java.util.*;
//
//public class Input {
//
//    String input;
//
//    public Input() {
//        input = null;
//    }
//
//    public String getInput() {
//        Scanner in = new Scanner(System.in);
//        this.input = in.nextLine();
//        return input;
//    }
//
//    public boolean isCommandValid(String command) {
//        boolean isInclude = false;
//
//        for (Command e : Command.values()) {
//            String existCommand = e.getValue();
//            if (existCommand.equals(command)) {
//                isInclude = true;
//            }
//        }
//        return isInclude;
//    }
//
//    //can move to territory that has path to it
//    public boolean isMoveDstTerrValid(String srcTerr, String dstTerr, Player p) {
//        if (srcTerr.equals(dstTerr)) {
//            System.out.println("Destination territory should not be the same as Source Territory.");
//            return false;
//        }
//        Stack<String> s = new Stack<>();
//        Set<String> isVisited = new HashSet<>();
//        s.push(srcTerr);
//        isVisited.add(srcTerr);
//
//        //use dfs to see if there is a path
//        while (!s.empty()) {
//            String currTerr = s.pop();
//            //if current territory's adjacent list include destination territory
//            for (String adjTerr : p.tm.allTerritories.get(currTerr)) {
//                if (adjTerr.equals(dstTerr)) {
//                    return true;
//                }
//            }
//            for (String currAdjTerr : p.tm.allTerritories.get(currTerr)) {
//                //if current territory's neighbor belongs to current player and has not been visited
//                if (p.territories.containsKey(currAdjTerr) && !isVisited.contains(currAdjTerr)) {
//                    s.push(currAdjTerr);
//                    isVisited.add(currAdjTerr);
//                }
//            }
//        }
//        return false;
//    }
//
//    //can only attack directly adjacent territory which is not its own's
//    public boolean isAttackDstTerrValid(String srcTerr, String dstTerr, Player player) {
//        if (player.territories.containsKey(dstTerr)) {
//            System.out.println("You cannot attack your own territory.");
//            return false;
//        }
//        List<String> adjacentTerr = player.tm.allTerritories.get(srcTerr);
//        for (String curr : adjacentTerr) {
//            if (curr.equals(dstTerr)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean isAllDigit(String str) {
//        for (int i = 0; i < str.length(); i++) {
//            if (!Character.isDigit(str.charAt(i))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public boolean isContinueWatch() {
//        String ifDisconnect = null;
//        while (true) {
//            System.out.println("You have lost the game. Do you want to continue watching the game?");
//            System.out.println("Yes or No");
//            ifDisconnect = getInput();
//            if (ifDisconnect.equals("Yes")) {
//                return true;
//            } else if (ifDisconnect.equals("No")) {
//                return false;
//            } else {
//                continue;
//            }
//        }
//    }
//}
