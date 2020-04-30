package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Narnia extends Territory {
	
	public Narnia(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Narnia", spiesOnMap,terrAndCloakCount);
		terrName="Narnia";
		this.ownerName=ownerName;
		size = 12;
		foodProduce = new Food(1);
		adjList = new ArrayList<>();
		adjList.add("Midkemia");
		adjList.add("Elantris");
		setUnit(units,unitAndNum);
	}
	
	
}
