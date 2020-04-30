package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Hogwarts extends Territory {

	public Hogwarts(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Hogwarts", spiesOnMap,terrAndCloakCount);
		terrName="Hogwarts";
		this.ownerName=ownerName;
		size = 12;
		foodProduce = new Food(8);
		adjList = new ArrayList<>();
		adjList.add("Scadrial");
		adjList.add("Mordor");
		adjList.add("Roshar");      
		setUnit(units,unitAndNum);

	}

}
