package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Scadrial extends Territory {

	public Scadrial(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Scadrial", spiesOnMap,terrAndCloakCount);
		terrName="Scadrial";
		this.ownerName=ownerName;
		size = 13;
		foodProduce = new Food(7);
		adjList = new ArrayList<>();
		adjList.add("Midkemia");
		adjList.add("Elantris");
		adjList.add("Mordor");
		adjList.add("Oz");
		adjList.add("Hogwarts");
		adjList.add("Roshar");       
		setUnit(units,unitAndNum);

	}
}
