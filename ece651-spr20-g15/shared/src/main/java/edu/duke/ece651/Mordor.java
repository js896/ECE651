package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Mordor extends Territory {

	public Mordor(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Mordor", spiesOnMap,terrAndCloakCount);
		terrName="Mordor";
		this.ownerName=ownerName;
		size = 12;
		foodProduce = new Food(5);
		adjList = new ArrayList<>();
		adjList.add("Gondor");
		adjList.add("Oz");
		adjList.add("Scadrial");
		adjList.add("Hogwarts");
		setUnit(units,unitAndNum);

	}

}
