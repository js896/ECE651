package edu.duke.ece651;


import java.util.*;

// Example: Blue^Food+50^-Elantris+3+(private_1+major_2)-Midkemia+0+(private_1+major_2){Attack~private>~1~Elantris~Scadrial@Attack~<major>~2~Elantris~Scadria@Upgrade~Elantris~<private>~1~<major>@Done~}
// This class is used for Player and Action to serialize the message sent to server and
// players. Also to deserialize the message from server and players.
public class serialize {

    // This function is used for player to serialize its information and action to
    // a string to send to server.
    static TerrInfo terrInfo = new TerrInfo();
    public static String PlayerSerialization(Player currPlayer, String Action) {
        StringBuilder ans = new StringBuilder();
        ans.append(currPlayer.getColor());
        // Add resources and num
        ans.append("^Food$");
        Set<Resource> currTotalResource = currPlayer.getTotalResource();
        for (Iterator<Resource> it = currTotalResource.iterator(); it.hasNext(); ){
            Resource currResource = it.next();
            int num = currResource.getNum();
            ans.append(num);
            ans.append("^");
        }
        // Add territory and units on it
        Map<String, Map<String, Integer>> currTerrAndUnits = currPlayer.getCurrTerrAndUnits();
        for (Map.Entry<String, Map<String, Integer>> entry : currTerrAndUnits.entrySet()) {
            ans.append("-");
            String terrName = entry.getKey();
            ans.append(terrName);
            //add round number
            ans.append("+");
            int round = currPlayer.allTerrs.get(terrName).getCount();
            ans.append(round);

            ans.append("+(");
            Map<String, Integer> currUnits = entry.getValue();
            ans.append("Private_");
            if (currUnits.containsKey("Private")) {
                ans.append(currUnits.get("Private"));
            } else {
                ans.append("0");
            }
            ans.append("+Corporal_");
            if (currUnits.containsKey("Corporal")) {
                ans.append(currUnits.get("Corporal"));
            } else {
                ans.append("0");
            }
            ans.append("+Sergent_");
            if (currUnits.containsKey("Sergent")) {
                ans.append(currUnits.get("Sergent"));
            } else {
                ans.append("0");
            }
            ans.append("+Lieutenant_");
            if (currUnits.containsKey("Lieutenant")) {
                ans.append(currUnits.get("Lieutenant"));
            } else {
                ans.append("0");
            }
            ans.append("+Captain_");
            if (currUnits.containsKey("Captain")) {
                ans.append(currUnits.get("Captain"));
            } else {
                ans.append("0");
            }
            ans.append("+Major_");
            if (currUnits.containsKey("Major")) {
                ans.append(currUnits.get("Major"));
            } else {
                ans.append("0");
            }
            ans.append("+Colonel_");
            if (currUnits.containsKey("Colonel")) {
                ans.append(currUnits.get("Colonel"));
            } else {
                ans.append("0");
            }
            ans.append(")");
        }
        // Add action
        ans.append("{");
        ans.append(Action);
        ans.append("}");

        // Add cloak info
        ans.append("%");
        StringBuilder cloakInfo = new StringBuilder();
        Map<String, Map<String,Integer>> spiesOnMap = currPlayer.getSpiesOnMap();
        for (Map.Entry<String, Map<String, Integer>> entry : spiesOnMap.entrySet()) {
            String terrName = entry.getKey();
            Map<String, Integer> spies = entry.getValue();
            if (spies.containsKey(currPlayer.getColor())) {
                int spyNum = spies.get(currPlayer.getColor());
                if (cloakInfo.length() != 0) {
                    cloakInfo.append("-");
                }
                cloakInfo.append("(");
                cloakInfo.append(terrName);
                cloakInfo.append("+");
                cloakInfo.append(spyNum);
                cloakInfo.append(")");
            }
        }
        ans.append(cloakInfo);
        return ans.toString();
    }

    // This function is used for player to deserialize the message from server to
    // a Player object
    public static Player PlayerDeserialization(String color, String allInfo) {
        String[] infos = allInfo.split("\\*");
        StringBuilder playerInfo = new StringBuilder();
        String terrInfo = "";
        boolean first = true;
        for (String info : infos) {
            if(!first) {
                playerInfo.append("*");
            }
            String[] detailedInfos = info.split("%");
            playerInfo.append(detailedInfos[0]);
            first = false;
            terrInfo = detailedInfos[1];
        }
        Map<String,Integer> resources = new HashMap<>();
        Map<String, Map<String,Integer>> terrsAndUnits = new HashMap<>();
        // If this target color doesn't lose yet
        if (playerInfo.toString().contains(color)) {
            String[] players = playerInfo.toString().split("\\*");
            for (String player : players) {
                // Find the target player's information
                if (player.contains(color)) {
                    // Get resource name and number
                    String resource = player.substring(player.indexOf('^') + 1, player.lastIndexOf('^'));
                    String resourceName = resource.substring(0, resource.indexOf("$"));
                    int resourceNum = Integer.parseInt(resource.substring(resource.indexOf("$") + 1));
                    resources.put(resourceName, resourceNum);
                    // Get territory info
                    String territoryINFO = player.substring(player.lastIndexOf('^') + 2);
                    String[] territoryList = territoryINFO.split("-");
                    for (String territory : territoryList) {
                        String terriName = territory.substring(0,territory.indexOf("+"));
                        //int round = Integer.parseInt(territory.substring(territory.indexOf("+") + 1, territory.indexOf("+(")));
                        String unitsINFO = territory.substring(territory.indexOf("(")+1, territory.indexOf(")"));
                        String[] unitsList = unitsINFO.split("\\+");
                        Map<String, Integer> unitMap = new HashMap<>();
                        for (String unit : unitsList) {
                            String unitType = unit.substring(0,unit.indexOf("_"));
                            int unitNumber = Integer.parseInt(unit.substring(unit.indexOf("_") + 1));
                            if (unitNumber != 0) {
                                unitMap.put(unitType, unitNumber);
                            }
                        }
                        terrsAndUnits.put(terriName, unitMap);
                    }
                }
            }
        }

        Map<String,Map<String,Integer>> spiesOnMap = new HashMap<>();
        Map<String,Integer> terrAndCloakCount = new HashMap<>();
        setTerrInfo(spiesOnMap, terrAndCloakCount, terrInfo);
        Player ans = new Player(color, resources, terrsAndUnits, spiesOnMap, terrAndCloakCount);

        return ans;
    }

    // This function is used to split all player's information into a list
    public static List<String> ActionDeserialization(String allInfo) {
        String[] infos = allInfo.split("\\*");
        List<String> ans = new ArrayList<>();
        for (String info : infos) {
            ans.add(info);
        }
        return ans;
    }

    //%(Narnia-[Blue*3]-3)+(Oz-[Blue*1&Green*2]-0)+...

    public static void setTerrInfo(Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount, String terrInfo) {
        String[] terrs = terrInfo.split("\\+");
        for (String terr : terrs) {
            terr = terr.substring(terr.indexOf('(') + 1, terr.indexOf(')'));
            String[] infos = terr.split("-");
            String terrName = infos[0];
            String spyInfo = infos[1].substring(infos[1].indexOf('[') + 1, infos[1].indexOf(']'));
            int count = Integer.parseInt(infos[2]);
            terrAndCloakCount.put(terrName, count);
            Map<String, Integer> spyMap = new HashMap<>();
            if (spyInfo.length() > 0) {
                String[] spies = spyInfo.split("&");
                for (String spy : spies) {
                    String[] spyInfos = spy.split("\\#");
                    String owner = spyInfos[0];
                    int number = Integer.parseInt(spyInfos[1]);
                    System.out.println("~~~~~~~~~~~~Spy Info~~~~~~~~~~~~~~~~");
                    System.out.println("Owner: " + owner + "  Number: " + number);
                    spyMap.put(owner, number);
                }
            }
            spiesOnMap.put(terrName, spyMap);
        }

    }

    public static void updateAllTerritory(Player player, String allInfo) {
        String[] infos = allInfo.split("\\*");
        String terrInfo = "";
        for (String info : infos) {
            String[] detailedInfos = info.split("%");
            terrInfo = detailedInfos[1];
        }
        Map<String,Map<String,Integer>> spiesOnMap = new HashMap<>();
        Map<String,Integer> terrAndCloakCount = new HashMap<>();
        setTerrInfo(spiesOnMap, terrAndCloakCount, terrInfo);
        Map<String, Player> allTerritoryAndUnit = player.getAllTerrAndUnits();
        for (Map.Entry<String, Player> entry : allTerritoryAndUnit.entrySet()) {
            String color = entry.getKey();
            Player currPlayer = entry.getValue();
            Map<String, Map<String, Integer>> currInfos = currPlayer.getCurrTerrAndUnits();
            for (Map.Entry<String, Map<String, Integer>> entry1 : currInfos.entrySet()) {
                String terrName = entry1.getKey();
                Map<String, Integer> currTerrInfo = entry1.getValue();
                Territory territory;
                if (terrName.equals("Narnia")) {
                    territory = new Narnia(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else if (terrName.equals("Midkemia")) {
                    territory = new Midkemia(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else if (terrName.equals("Oz")) {
                    territory = new Oz(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else if (terrName.equals("Elantris")) {
                    territory = new Elantris(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else if (terrName.equals("Scadrial")) {
                    territory = new Scadrial(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else if (terrName.equals("Mordor")) {
                    territory = new Mordor(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else if (terrName.equals("Gondor")) {
                    territory = new Gondor(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else if (terrName.equals("Roshar")) {
                    territory = new Roshar(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                } else {
                    territory = new Hogwarts(color, currTerrInfo, spiesOnMap, terrAndCloakCount);
                }
                territory.setCount(terrAndCloakCount.get(terrName));
                player.allTerrs.put(terrName, territory);
            }
        }
    }

    // This function is used to update all player's information based all player's
    // information.
    public static void updateAll(Map<String, Player> map, String allInfo) {
        if (!allInfo.contains("^")) {
            return;
        }
        Iterator<Map.Entry<String, Player>> it = map.entrySet().iterator();
        // First remove all players' information and then update them
        while (it.hasNext()) {
            Map.Entry<String, Player> item = it.next();
            it.remove();
        }
        //allInfo = allInfo.substring(allInfo.indexOf(')') + 1);
        List<String> infos = serialize.ActionDeserialization(allInfo);
        // Update all players
        for (String info : infos) {
            String color = info.substring(0, info.indexOf('^'));
            StringBuilder new_info = new StringBuilder(allInfo);
            //new_info.insert(0, "(" + color + ")");
            Player currPlayer = serialize.PlayerDeserialization(color, new_info.toString());
            map.put(currPlayer.getColor(), currPlayer);
        }
    }
}
