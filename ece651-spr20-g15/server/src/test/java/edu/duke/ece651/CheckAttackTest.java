package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckAttackTest {
    @Test
    void getPlayerTest() {
        CheckAttack ca = new CheckAttack();
        Data data = new Data();
        List<String> terrList = new ArrayList<>();
        Map<String,List<String>> ownMap = new HashMap<>();
        terrList.add("Narnia");
        terrList.add("Midkemia");
        terrList.add("Oz");
        ownMap.put("Green",terrList);
        System.out.println(ca.getPlayer("Narnia",ownMap));
    }
    @Test
    void checkStatusTest() {
        CheckAttack cm = new CheckAttack();
        Data data = new Data();
        List<String> terrList = new ArrayList<>();
        Map<String,List<String>> ownMap = new HashMap<>();
        terrList.add("Narnia");
        terrList.add("Midkemia");
        terrList.add("Oz");
        ownMap.put("Green",terrList);
        Map<String, String> resourceMap = new HashMap<String, String>();
        resourceMap.put("Green", "5");
        data.resourceMap=resourceMap;
        data.ownMap=ownMap;
        Map<String, Map<String, Integer>> unitMap = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> LevelAndNum = new HashMap<>();
        LevelAndNum.put("Private",10);
        LevelAndNum.put("Sergent",8);
        unitMap.put("Narnia", LevelAndNum);
        Map<String, Map<String, Integer>> unitMap2 = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> LevelAndNum2 = new HashMap<>();
        LevelAndNum2.put("Private",10);
        LevelAndNum2.put("Sergent",8);
        data.unitMap=unitMap2;
        System.out.println(cm.checkStatus(data, "Green", "Attack~Private~1~Narnia~Oz"));//true
        System.out.println(cm.checkStatus(data, "Green", "Attack~Private~8~Narnia~Oz"));//false

    }

}
