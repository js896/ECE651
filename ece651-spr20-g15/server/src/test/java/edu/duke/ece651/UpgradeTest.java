package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpgradeTest {
    @Test
    void checkStatusTest() {
        Upgrade u = new Upgrade();
        Data data = new Data();
        List<String> terrList = new ArrayList<>();
        Map<String,List<String>> ownMap = new HashMap<>();
        terrList.add("Narnia");
        terrList.add("Midkemia");
        terrList.add("Oz");
        ownMap.put("Green",terrList);
        Map<String, String> resourceMap = new HashMap<String, String>();
        resourceMap.put("Green", "200");
        data.resourceMap=resourceMap;
        data.ownMap=ownMap;
        Map<String, Map<String, Integer>> unitMap = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> LevelAndNum = new HashMap<>();
        LevelAndNum.put("Private",10);
        LevelAndNum.put("Sergent",8);
        unitMap.put("Narnia", LevelAndNum);
        data.unitMap=unitMap;
        u.getRes(data, "Green", "Upgrade~Narnia~Private~1~Sergent");

    }

}
