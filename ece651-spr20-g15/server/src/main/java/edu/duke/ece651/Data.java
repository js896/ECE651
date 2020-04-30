package edu.duke.ece651;

import java.util.*;

public class Data {
  //map for adjacency
  public final Map<String, List<String>> adjacentMap = new HashMap<String, List<String>>();
  //map for size
  public final Map<String, Integer> sizeMap = new HashMap<String, Integer>();
  //map for production
  public final Map<String, Integer> productionMap = new HashMap<String, Integer>();
  //map for units on each block
  public Map<String, Map<String, Integer>> unitMap = new HashMap<String, Map<String, Integer>>();
  //map for what blocks each player owns
  public Map<String, List<String>> ownMap = new HashMap<String, List<String>>();
  //map for resource of each player
  public Map<String, String> resourceMap = new HashMap<String, String>();
  //map for action
  public Map<String, List<String>> actionMap = new HashMap<String, List<String>>();
  //map for cloak times
  public Map<String, Integer> cloakMap = new HashMap<String, Integer>();
  //map for spy
  public Map<String, Map<String, Integer>> spyMap = new HashMap<String, Map<String, Integer>>();
  public void init() { initMap(); }

  private void initMap() {
    //fix adjacentMap
    //Narnia
    List<String> Narnia = new ArrayList<String>();
    Narnia.add("Elantris");
    Narnia.add("Midkemia");
    adjacentMap.put("Narnia", Narnia);
    //Midkemia
    List<String> Midkemia = new ArrayList<String>();
    Midkemia.add("Narnia");
    Midkemia.add("Elantris");
    Midkemia.add("Scadrial");
    Midkemia.add("Oz");
    adjacentMap.put("Midkemia", Midkemia);
    //Oz
    List<String> Oz = new ArrayList<String>();
    Oz.add("Midkemia");
    Oz.add("Scadrial");
    Oz.add("Mordor");
    Oz.add("Gondor");
    adjacentMap.put("Oz", Oz);
    //Gondor
    List<String> Gondor = new ArrayList<>();
    Gondor.add("Oz");
    Gondor.add("Mordor");
    adjacentMap.put("Gondor", Gondor);
    //Elantris
    List<String> Elantris = new ArrayList<>();
    Elantris.add("Narnia");
    Elantris.add("Midkemia");
    Elantris.add("Scadrial");
    Elantris.add("Roshar");
    adjacentMap.put("Elantris", Elantris);
    //Scadrial
    List<String> Scadrial = new ArrayList<>();
    Scadrial.add("Elantris");
    Scadrial.add("Midkemia");
    Scadrial.add("Oz");
    Scadrial.add("Mordor");
    Scadrial.add("Hogwarts");
    Scadrial.add("Roshar");
    adjacentMap.put("Scadrial", Scadrial);
    //Roshar
    List<String> Roshar = new ArrayList<>();
    Roshar.add("Elantris");
    Roshar.add("Scadrial");
    Roshar.add("Hogwarts");
    adjacentMap.put("Roshar", Roshar);
    //Mordor
    List<String> Mordor = new ArrayList<>();
    Mordor.add("Gondor");
    Mordor.add("Oz");
    Mordor.add("Scadrial");
    Mordor.add("Hogwarts");
    adjacentMap.put("Mordor", Mordor);
    //Hogwarts
    List<String> Hogwarts = new ArrayList<>();
    Hogwarts.add("Mordor");
    Hogwarts.add("Scadrial");
    Hogwarts.add("Roshar");
    adjacentMap.put("Hogwarts", Hogwarts);

    //fix sizeMap
    sizeMap.put("Narnia", 1);
    sizeMap.put("Gondor", 2);
    sizeMap.put("Midkemia", 3);
    sizeMap.put("Roshar", 4);
    sizeMap.put("Mordor", 5);
    sizeMap.put("Elantris", 6);
    sizeMap.put("Scadrial", 7);
    sizeMap.put("Hogwarts", 8);
    sizeMap.put("Oz", 9);

    //fix productionMap
    productionMap.put("Narnia", 1);
    productionMap.put("Gondor", 2);
    productionMap.put("Midkemia", 3);
    productionMap.put("Roshar", 4);
    productionMap.put("Mordor", 5);
    productionMap.put("Elantris", 6);
    productionMap.put("Scadrial", 7);
    productionMap.put("Hogwarts", 8);
    productionMap.put("Oz", 9);
  }
}
