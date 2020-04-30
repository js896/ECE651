package edu.duke.ece651;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TerritoryMap {
    //store the territory and its adjacency list
    final Map<String, List<String>> allTerritories;

    public TerritoryMap() {
        allTerritories = new HashMap<String, List<String>>();

        List<String> narNeigh = new ArrayList<>();
        narNeigh.add("Midkemia");
        narNeigh.add("Elantris");
        allTerritories.put("Narnia", narNeigh);

        List<String> midNeigh = new ArrayList<>();
        midNeigh.add("Narnia");
        midNeigh.add("Elantris");
        midNeigh.add("Scadrial");
        midNeigh.add("Oz");
        allTerritories.put("Midkemia", midNeigh);

        List<String> scaNeigh = new ArrayList<>();
        scaNeigh.add("Midkemia");
        scaNeigh.add("Elantris");
        scaNeigh.add("Mordor");
        scaNeigh.add("Oz");
        scaNeigh.add("Hogwarts");
        scaNeigh.add("Roshar");
        allTerritories.put("Scadrial", scaNeigh);

        List<String> ozNeigh = new ArrayList<>();
        ozNeigh.add("Midkemia");
        ozNeigh.add("Scadrial");
        ozNeigh.add("Mordor");
        ozNeigh.add("Gondor");
        allTerritories.put("Oz", ozNeigh);

        List<String> morNeigh = new ArrayList<>();
        morNeigh.add("Oz");
        morNeigh.add("Scadrial");
        morNeigh.add("Hogwarts");
        morNeigh.add("Gondor");
        allTerritories.put("Mordor", morNeigh);

        List<String> gonNeigh = new ArrayList<>();
        gonNeigh.add("Oz");
        gonNeigh.add("Mordor");
        allTerritories.put("Gondor", gonNeigh);

        List<String> hogNeigh = new ArrayList<>();
        hogNeigh.add("Scadrial");
        hogNeigh.add("Mordor");
        hogNeigh.add("Roshar");
        allTerritories.put("Hogwarts", hogNeigh);

        List<String> rosNeigh = new ArrayList<>();
        rosNeigh.add("Scadrial");
        rosNeigh.add("Hogwarts");
        rosNeigh.add("Elantris");
        allTerritories.put("Roshar", rosNeigh);

        List<String> elaNeigh = new ArrayList<>();
        elaNeigh.add("Narnia");
        elaNeigh.add("Midkemia");
        elaNeigh.add("Scadrial");
        elaNeigh.add("Roshar");
        allTerritories.put("Elantris", elaNeigh);
    }

}
