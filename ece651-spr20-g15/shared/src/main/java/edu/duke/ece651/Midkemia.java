package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Midkemia extends Territory {
	public Midkemia(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Midkemia",spiesOnMap,terrAndCloakCount);
		terrName="Midkemia";
		this.ownerName=ownerName;
		size = 12;
		foodProduce = new Food(3);
		adjList = new ArrayList<>();
		adjList.add("Narnia");
		adjList.add("Elantris");
		adjList.add("Scadrial");
		adjList.add("Oz");
		setUnit(units,unitAndNum);

	}
}
