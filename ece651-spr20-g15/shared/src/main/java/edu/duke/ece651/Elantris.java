package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Elantris extends Territory {

	public Elantris(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Elantris", spiesOnMap,terrAndCloakCount);
		this.terrName="Elantris";
		this.ownerName=ownerName;
		this.size = 20;
		foodProduce = new Food(6);
		adjList = new ArrayList<>();
		adjList.add("Narnia");
		adjList.add("Midkemia");
		adjList.add("Scadrial");
		adjList.add("Roshar");       
		setUnit(units,unitAndNum);

	}

}
