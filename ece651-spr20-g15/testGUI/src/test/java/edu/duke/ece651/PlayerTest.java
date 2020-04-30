package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getTerritory() {
        Player blue = new Player();
        Player green = new Player();
        Player red = new Player();
        Map<String,Map<String,Integer>> spiesOnMap = new HashMap<>();
        Map<String,Integer> terrAndCloakCount = new HashMap<>();
        Map<String,Integer> unitMap = new HashMap<>();
        unitMap.put("Private",10);
        Territory Narnia = new Narnia("green",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Midkemia = new Midkemia("green",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Oz = new Midkemia("green",unitMap, spiesOnMap, terrAndCloakCount);
        green.territories.add(Narnia);
        green.territories.add(Midkemia);
        green.territories.add(Oz);
        Territory Elantris = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Scadrial = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Roshar = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        blue.territories.add(Elantris);
        blue.territories.add(Scadrial);
        blue.territories.add(Roshar);
        Territory Gondor = new Gondor("red",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Mordor = new Mordor("red",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Hogwarts = new Hogwarts("red",unitMap, spiesOnMap, terrAndCloakCount);
        red.territories.add(Gondor);
        red.territories.add(Mordor);
        red.territories.add(Hogwarts);
        red.allTerrAndUnits.put("green",green);
        red.allTerrAndUnits.put("blue",blue);
        red.allTerrAndUnits.put("red",red);
        green.allTerrAndUnits.put("green",green);
        green.allTerrAndUnits.put("blue",blue);
        green.allTerrAndUnits.put("red",red);
        blue.allTerrAndUnits.put("green",green);
        blue.allTerrAndUnits.put("blue",blue);
        blue.allTerrAndUnits.put("red",red);
        System.out.println("owner is ");
        System.out.println(green.getTerritory("Narnia"));
    }
    @Test
    void printAllinfo(){
        Player blue = new Player();
        Player green = new Player();
        Player red = new Player();
        Map<String,Map<String,Integer>> spiesOnMap = new HashMap<>();
        Map<String,Integer> terrAndCloakCount = new HashMap<>();
        Map<String,Integer> unitMap = new HashMap<>();
        unitMap.put("Private",10);
        Territory Narnia = new Narnia("green",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Midkemia = new Midkemia("green",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Oz = new Midkemia("green",unitMap, spiesOnMap, terrAndCloakCount);
        green.territories.add(Narnia);
        green.territories.add(Midkemia);
        green.territories.add(Oz);
        Territory Elantris = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Scadrial = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Roshar = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        blue.territories.add(Elantris);
        blue.territories.add(Scadrial);
        blue.territories.add(Roshar);
        Territory Gondor = new Gondor("red",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Mordor = new Mordor("red",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Hogwarts = new Hogwarts("red",unitMap, spiesOnMap, terrAndCloakCount);
        red.territories.add(Gondor);
        red.territories.add(Mordor);
        red.territories.add(Hogwarts);
        red.allTerrAndUnits.put("green",green);
        red.allTerrAndUnits.put("blue",blue);
        red.allTerrAndUnits.put("red",red);
        green.allTerrAndUnits.put("green",green);
        green.allTerrAndUnits.put("blue",blue);
        green.allTerrAndUnits.put("red",red);
        blue.allTerrAndUnits.put("green",green);
        blue.allTerrAndUnits.put("blue",blue);
        blue.allTerrAndUnits.put("red",red);
        System.out.println("territory info is ");
        //System.out.println(green.getTerritoryInfo("Narnia"));
    }
    @Test
    void canMove(){
        Player blue = new Player();
        Player green = new Player();
        Player red = new Player();
        Map<String,Map<String,Integer>> spiesOnMap = new HashMap<>();
        Map<String,Integer> terrAndCloakCount = new HashMap<>();
        Map<String,Integer> unitMap = new HashMap<>();
        unitMap.put("Private",10);
        Territory Narnia = new Narnia("green",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Midkemia = new Midkemia("green",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Oz = new Midkemia("green",unitMap, spiesOnMap, terrAndCloakCount);
        green.territories.add(Narnia);
        green.territories.add(Midkemia);
        green.territories.add(Oz);
        Territory Elantris = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Scadrial = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Roshar = new Elantris("blue",unitMap, spiesOnMap, terrAndCloakCount);
        blue.territories.add(Elantris);
        blue.territories.add(Scadrial);
        blue.territories.add(Roshar);
        Territory Gondor = new Gondor("red",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Mordor = new Mordor("red",unitMap, spiesOnMap, terrAndCloakCount);
        Territory Hogwarts = new Hogwarts("red",unitMap, spiesOnMap, terrAndCloakCount);
        red.territories.add(Gondor);
        red.territories.add(Mordor);
        red.territories.add(Hogwarts);
        red.allTerrAndUnits.put("green",green);
        red.allTerrAndUnits.put("blue",blue);
        red.allTerrAndUnits.put("red",red);
        green.allTerrAndUnits.put("green",green);
        green.allTerrAndUnits.put("blue",blue);
        green.allTerrAndUnits.put("red",red);
        blue.allTerrAndUnits.put("green",green);
        blue.allTerrAndUnits.put("blue",blue);
        blue.allTerrAndUnits.put("red",red);
        System.out.println(blue.canSpyMoveDstTerr("Narnia"));

        System.out.println(green.canMoveDstTerr("Narnia"));
    }
    @Test
    void equalTest(){
        Map<String,Integer> resources = new HashMap<>();
        resources.put("food",20);
        Map<String,Integer> unitMap = new HashMap<>();
        unitMap.put("Private",10);
        Map<String, Map<String,Integer>> terrsAndUnits = new HashMap<>();
        terrsAndUnits.put("Narnia",unitMap);
        terrsAndUnits.put("Elantris",unitMap);
        terrsAndUnits.put("Midkemia",unitMap);
        terrsAndUnits.put("Oz",unitMap);
        terrsAndUnits.put("Gondor",unitMap);
        terrsAndUnits.put("Mordor",unitMap);
        terrsAndUnits.put("Hogwarts",unitMap);
        terrsAndUnits.put("Roshar",unitMap);
        terrsAndUnits.put("Scadrial",unitMap);


        Map<String,Map<String,Integer>> spiesOnMap = new HashMap<>();
        Map<String,Integer> terrAndCloakCount = new HashMap<>();
        Player green = new Player("green",resources,terrsAndUnits, spiesOnMap, terrAndCloakCount);
        Player blue = new Player("blue",resources,terrsAndUnits, spiesOnMap, terrAndCloakCount);



        System.out.println(green.equals(blue));
        System.out.println(green.equals(green));
    }
}