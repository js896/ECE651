package edu.duke.ece651;

import java.util.ArrayList;
import java.util.Map;

public class Oz extends Territory {

	public Oz(String ownerName,Map<String,Integer> unitAndNum,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
		super("Oz", spiesOnMap,terrAndCloakCount);
			terrName="Oz";
			this.ownerName=ownerName;
			size = 12;
			foodProduce = new Food(9);
			adjList = new ArrayList<>();
			adjList.add("Midkemia");
			adjList.add("Gondor");
			adjList.add("Mordor");
			adjList.add("Scadrial");
			setUnit(units,unitAndNum);

		}
	}


