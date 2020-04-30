package edu.duke.ece651;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Territory {
	String terrName;
	String ownerName;
	int size;
	Resource foodProduce;
	List<String> adjList;
	List<Unit> units;
	//store spy's owner and the number
	Map<String,Integer> allSpiesOnCurrTerr;
	boolean isNew;//whether current information is old
	int count;//indicate cloak, when it is <3, it is cloaked

	protected void setCount(int num) {
		count = num;
	}

	public int getCount(){
		return count;
	}

	public Territory(String name, Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		this.terrName = name;
		units = new ArrayList<>();
	//	isVisible=false;
		isNew=true;
		if(terrAndCloakCount.containsKey(this.terrName)){
			count=terrAndCloakCount.get(this.terrName);
		}
		allSpiesOnCurrTerr = new HashMap<>();

//		System.out.println("~~~~~~~~Print SpiesOnMap~~~~~~~~~~~~~");
//		for (Map.Entry<String, Map<String, Integer>> entry : spiesOnMap.entrySet()) {
//			String terrName = entry.getKey();
//			Map<String, Integer> spyInfo = entry.getValue();
//			System.out.println("Territory name: " + terrName);
//			for (Map.Entry<String, Integer> entry1 : spyInfo.entrySet()) {
//				String owner = entry1.getKey();
//				int num = entry1.getValue();
//				System.out.println("Owner: " + owner + "  Number: " + num);
//			}
//		}

		System.out.println("______________________");
		System.out.println(this.terrName);
		if(spiesOnMap.containsKey(this.terrName)){
//			if (this.terrName.equals("Elantris")) {
//				System.out.println("______________Test Elantris Spy Map________________");
//				System.out.println(spiesOnMap.get(this.terrName).isEmpty());
//			}
			this.allSpiesOnCurrTerr=spiesOnMap.get(this.terrName);
		}
	}

	public int getFoodNum() {
		return foodProduce.getNum();
	}

	public Map<String, Integer> getAllSpiesOnCurrTerr() {
		return allSpiesOnCurrTerr;
	}


	//initialize list of units
	public void setUnit(List<Unit> units,Map<String,Integer> unitAndNum) {		
		Unit temp = null;
		for(Entry<String, Integer> e:unitAndNum.entrySet()) {
			if(e.getKey().equals("Private")) {
				temp = new Private(e.getValue());		
			}
			else if(e.getKey().equals("Corporal")) {
				temp = new Corporal(e.getValue());
			}
			else if(e.getKey().equals("Sergent")) {
				temp = new Sergent(e.getValue());
			}
			else if(e.getKey().equals("Lieutenant")) {
				temp = new Lieutenant(e.getValue());
			}
			else if(e.getKey().equals("Captain")) {
				temp = new Captain(e.getValue());
			}
			else if(e.getKey().equals("Major")) {
				temp = new Major(e.getValue());
			}
			else if(e.getKey().equals("Colonel")) {
				temp = new Colonel(e.getValue());
			}
			units.add(temp);
		}
	}

	public String getTerrName() {
		return terrName;
	}
	}

