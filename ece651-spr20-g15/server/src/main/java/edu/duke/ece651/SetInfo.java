package edu.duke.ece651;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SetInfo {
    public static void setInfo(int num) {
        Map<String,Map<String,Integer>> spiesOnMap = new HashMap<>();
        Map<String,Integer> terrAndCloakCount = new HashMap<>();

        Map<String,Integer> commonUnitAndNum = new HashMap<>();
        commonUnitAndNum.put("Private", 7);
        commonUnitAndNum.put("Corporal", 2);
        commonUnitAndNum.put("Captain", 1);
        Map<String,Integer> specUnitAndNum = new HashMap<>();
        specUnitAndNum.put("Private", 1);

        Map<String, Map<String, Territory>> infos = serialize.terrInfo.getPlayerAndTerrs();

        if (num == 2) {
            Narnia narnia = new Narnia("Green", specUnitAndNum, spiesOnMap, terrAndCloakCount);
            Midkemia midkemia = new Midkemia("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Oz oz = new Oz("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Elantris elantris = new Elantris("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Scadrial scadrial = new Scadrial("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Mordor mordor = new Mordor("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Gondor gondor = new Gondor("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Roshar roshar = new Roshar("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Hogwarts hogwarts = new Hogwarts("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Map<String, Territory> greens = new HashMap<>();
            Map<String, Territory> blues = new HashMap<>();
            greens.put("Narnia", narnia);
            blues.put("Midkemia", midkemia);
            blues.put("Oz", oz);
            blues.put("Elantris", elantris);
            blues.put("Scadrial", scadrial);
            blues.put("Mordor", mordor);
            blues.put("Gondor", gondor);
            blues.put("Roshar", roshar);
            blues.put("Hogwarts", hogwarts);
            infos.put("Green", greens);
            infos.put("Blue", blues);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else if (num == 3) {
            Narnia narnia = new Narnia("Green", specUnitAndNum, spiesOnMap, terrAndCloakCount);
            Midkemia midkemia = new Midkemia("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Oz oz = new Oz("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Elantris elantris = new Elantris("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Scadrial scadrial = new Scadrial("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Mordor mordor = new Mordor("Red", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Gondor gondor = new Gondor("Red", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Roshar roshar = new Roshar("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Hogwarts hogwarts = new Hogwarts("Red", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Map<String, Territory> greens = new HashMap<>();
            Map<String, Territory> blues = new HashMap<>();
            Map<String, Territory> reds = new HashMap<>();
            greens.put("Narnia", narnia);
            blues.put("Midkemia", midkemia);
            blues.put("Oz", oz);
            blues.put("Elantris", elantris);
            blues.put("Scadrial", scadrial);
            reds.put("Mordor", mordor);
            reds.put("Gondor", gondor);
            blues.put("Roshar", roshar);
            reds.put("Hogwarts", hogwarts);
            infos.put("Green", greens);
            infos.put("Blue", blues);
            infos.put("Red", reds);
        } else if (num == 4) {
            Narnia narnia = new Narnia("Green", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Midkemia midkemia = new Midkemia("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Oz oz = new Oz("Yellow", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Elantris elantris = new Elantris("Green", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Scadrial scadrial = new Scadrial("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Mordor mordor = new Mordor("Red", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Gondor gondor = new Gondor("Red", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Roshar roshar = new Roshar("Yellow", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Hogwarts hogwarts = new Hogwarts("Red", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Map<String, Territory> greens = new HashMap<>();
            Map<String, Territory> blues = new HashMap<>();
            Map<String, Territory> reds = new HashMap<>();
            Map<String, Territory> yellows = new HashMap<>();
            greens.put("Narnia", narnia);
            blues.put("Midkemia", midkemia);
            yellows.put("Oz", oz);
            greens.put("Elantris", elantris);
            blues.put("Scadrial", scadrial);
            reds.put("Mordor", mordor);
            reds.put("Gondor", gondor);
            yellows.put("Roshar", roshar);
            reds.put("Hogwarts", hogwarts);
            infos.put("Green", greens);
            infos.put("Blue", blues);
            infos.put("Red", reds);
            infos.put("Yellow", yellows);
        } else if (num == 5) {
            Narnia narnia = new Narnia("Green", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Midkemia midkemia = new Midkemia("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Oz oz = new Oz("Yellow", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Elantris elantris = new Elantris("Green", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Scadrial scadrial = new Scadrial("Blue", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Mordor mordor = new Mordor("Orange", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Gondor gondor = new Gondor("Red", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Roshar roshar = new Roshar("Yellow", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Hogwarts hogwarts = new Hogwarts("Orange", commonUnitAndNum, spiesOnMap, terrAndCloakCount);
            Map<String, Territory> greens = new HashMap<>();
            Map<String, Territory> blues = new HashMap<>();
            Map<String, Territory> reds = new HashMap<>();
            Map<String, Territory> yellows = new HashMap<>();
            Map<String, Territory> oranges = new HashMap<>();
            greens.put("Narnia", narnia);
            blues.put("Midkemia", midkemia);
            yellows.put("Oz", oz);
            greens.put("Elantris", elantris);
            blues.put("Scadrial", scadrial);
            oranges.put("Mordor", mordor);
            reds.put("Gondor", gondor);
            yellows.put("Roshar", roshar);
            oranges.put("Hogwarts", hogwarts);
            infos.put("Green", greens);
            infos.put("Blue", blues);
            infos.put("Red", reds);
            infos.put("Yellow", yellows);
            infos.put("Orange", oranges);
        }
    }
}
