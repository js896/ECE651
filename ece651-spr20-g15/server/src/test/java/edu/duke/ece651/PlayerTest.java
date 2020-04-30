package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerTest {
   @Test
    void setTerrInfo() {
        Player green = new Player("green");
        Map<String,Territory> map = new HashMap<>();
        Map<String,Territory> gMap = new HashMap<>();
        Map<String,Map<String,Integer>> spiesOnMap= new HashMap<>();
        Map<String,Integer> terrAndCloakCount= new HashMap<>();
        Map<String,Integer> unitAndNumGon2 = new HashMap<>();
        unitAndNumGon2.put("Private",66);
        Territory Gon2 = new Gondor("green",unitAndNumGon2,spiesOnMap,terrAndCloakCount);
        gMap.put("Gondor",Gon2
        );
        serialize.terrInfo.playerAndTerrs.put("green",gMap);

        serialize.terrInfo.playerAndTerrs.put("red",map);
        serialize.terrInfo.playerAndTerrs.put("blue",map);

        String ownerName = "green";
        Map<String,Integer> unitAndNum = new HashMap<>();
        //unitAndNum.put("Spy",1);
        unitAndNum.put("Captain",4);

        Territory Nar = new Narnia("green",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Mid = new Midkemia("green",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Oz = new Oz("green",unitAndNum,spiesOnMap,terrAndCloakCount);
        green.territories.add(Nar);
        green.territories.add(Mid);
        green.territories.add(Oz);
        Player blue= new Player("blue");
        Territory Gon = new Gondor("blue",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Mor = new Mordor("blue",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Hog = new Hogwarts("blue",unitAndNum,spiesOnMap,terrAndCloakCount);
        Spy spy = new Spy(1);
        spy.owner="blue";
        Gon.units.add(spy);

        blue.territories.add(Gon);
        blue.territories.add(Mor);
        blue.territories.add(Hog);
        Player red= new Player("red");
        Territory Ela = new Elantris("red",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Ros = new Roshar("red",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Sca = new Scadrial("red",unitAndNum,spiesOnMap,terrAndCloakCount);
        red.territories.add(Ela);
        red.territories.add(Ros);
        red.territories.add(Sca);

        Map<String,Territory> allTerrs = new HashMap<>();
        green.allTerrs.put("Narnia",Nar);
        green.allTerrs.put("Midkemia",Mid);
        green.allTerrs.put("Oz",Oz);
        green.allTerrs.put("Gondor",Gon);
        green.allTerrs.put("Mordor",Mor);
        green.allTerrs.put("Hogwarts",Hog);
        green.allTerrs.put("Scadrial",Sca);
        green.allTerrs.put("Roshar",Ros);
        green.allTerrs.put("Elantris",Ela);

        green.setTerrInfo();
        //Gondor should be updated because it is neighbour terr
        //if we remove Oz from green player, then Gondor will not be updated and shows old information
        System.out.println(green.getTerritoryInfo("Gondor"));
        System.out.println(green.getTerritoryInfo("Oz"));
        System.out.println(green.getTerritoryInfo("Hogwarts"));
        System.out.println(green.containTerritory("Oz"));
        green.canMoveTerr();
        green.canMoveDstTerr("Oz");

        //can not see the territory roshar
        assert(serialize.terrInfo.playerAndTerrs.get("green").containsKey("Roshar")==false);
    }

    @Test
    void canMoveDstTerrTest(){
        Map<String,Territory> map = new HashMap<>();
        Map<String,Territory> gMap = new HashMap<>();
        Map<String,Map<String,Integer>> spiesOnMap= new HashMap<>();
        Map<String,Integer> terrAndCloakCount= new HashMap<>();
        Map<String,Integer> unitAndNum = new HashMap<>();
        unitAndNum.put("Sergent",1);

        Player green = new Player("green");
        Territory Nar = new Narnia("green",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Mid = new Midkemia("green",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Oz = new Oz("green",unitAndNum,spiesOnMap,terrAndCloakCount);
        green.territories.add(Nar);
        green.territories.add(Mid);
        green.territories.add(Oz);

        Player blue= new Player("blue");
        Territory Gon = new Gondor("blue",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Mor = new Mordor("blue",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Hog = new Hogwarts("blue",unitAndNum,spiesOnMap,terrAndCloakCount);
        blue.territories.add(Gon);
        blue.territories.add(Mor);
        blue.territories.add(Hog);

        Player red= new Player("red");
        Territory Ela = new Elantris("red",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Ros = new Roshar("red",unitAndNum,spiesOnMap,terrAndCloakCount);
        Territory Sca = new Scadrial("red",unitAndNum,spiesOnMap,terrAndCloakCount);
        red.territories.add(Ela);
        red.territories.add(Ros);
        red.territories.add(Sca);

        green.allTerrAndUnits.put("green",green);
        green.allTerrAndUnits.put("blue", blue);
        green.allTerrAndUnits.put("red", red);

        blue.allTerrAndUnits.put("green",green);
        blue.allTerrAndUnits.put("blue", blue);
        blue.allTerrAndUnits.put("red", red);

        red.allTerrAndUnits.put("green",green);
        red.allTerrAndUnits.put("blue", blue);
        red.allTerrAndUnits.put("red", red);


        String srcTerr = "Roshar";
//        for(String str:green.canMoveDstTerr(srcTerr)){
//            System.out.println(str);
//        }
        for(String str:green.canSpyMoveDstTerr(srcTerr)){
            System.out.println(str);
        }
        System.out.println("****************************************************");
        String srcTerr1 = "Scadrial";
//        for(String str:green.canMoveDstTerr(srcTerr1)){
//            System.out.println(str);
//        }
        for(String str:green.canSpyMoveDstTerr(srcTerr1)){
            System.out.println(str);
        }
        System.out.println("****************************************************");
        String srcTerr2 = "Oz";
//        for(String str:green.canMoveDstTerr(srcTerr22)){
//            System.out.println(str);
//        }
        for(String str:green.canSpyMoveDstTerr(srcTerr)){
            System.out.println(str);
        }
    }

}
