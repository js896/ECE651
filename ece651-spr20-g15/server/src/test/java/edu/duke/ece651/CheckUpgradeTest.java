package edu.duke.ece651;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CheckUpgradeTest {

	@Test
	void checkStatusTest() {
		CheckUpgrade cu = new CheckUpgrade();
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
		System.out.println(cu.checkStatus(data, "Green", "Upgrade~Narnia~Corporal~1~Private"));//false
		System.out.println(cu.checkStatus(data, "Green", "Upgrade~Narnia~Private~1~Private"));//false
		System.out.println(cu.checkStatus(data, "Green", "Upgrade~Narnia~Private~2~Colonel"));//false
		System.out.println(cu.checkStatus(data, "Green", "Upgrade~Narnia~Private~1~Colonel"));//true
		System.out.println(cu.checkStatus(data, "Green", "Upgrade~Elantris~Private~1~Colonel"));//false
		
	}

}
