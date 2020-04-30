package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Roshar extends Territory {

	public Roshar(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Roshar", spiesOnMap,terrAndCloakCount);
		terrName="Roshar";
		this.ownerName=ownerName;
		size = 8;
		foodProduce = new Food(4);
		adjList = new ArrayList<>();
		adjList.add("Scadrial");
		adjList.add("Hogwarts");
		adjList.add("Elantris");
	    setUnit(units,unitAndNum);

	}
}
