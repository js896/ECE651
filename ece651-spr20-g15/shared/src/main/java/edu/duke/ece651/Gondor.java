package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Gondor extends Territory {

	public Gondor(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Gondor", spiesOnMap,terrAndCloakCount);
		terrName="Gondor";
		this.ownerName=ownerName;
		size = 12;
		foodProduce = new Food(2);
		adjList = new ArrayList<>();
		adjList.add("Oz");
		adjList.add("Mordor");
		setUnit(units,unitAndNum);

	}

}
