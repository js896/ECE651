package edu.duke.ece651;

import java.util.*;
import java.util.Map.Entry;


public class Player {
    //add round number
    int round;
    String color;
    //store the current territories and units of player
    Set<Resource> totalResource;
    Set<Territory> territories;
    TerritoryMap tm;
    Map<String, Player> allTerrAndUnits;
    //territory name owner name spy num
    Map<String, Map<String,Integer>> spiesOnMap;
    // evolution2
    Map<String, Map<String, Integer>> currTerrAndUnits;

    //store territory name and cloak count
    //if 0 not cloak,  cloak  change to 3
    Map<String,Integer> terrAndCloakCount;

    //include all territory name and corresponding territory
    Map<String,Territory> allTerrs;

    public Player() {
        round =0;
        tm = new TerritoryMap();
        territories = new HashSet<>();
        allTerrAndUnits = new HashMap<>();
        this.color = "";
    }

    public Player(String color) {
        round =0;
        tm = new TerritoryMap();
        territories = new HashSet<>();
        allTerrAndUnits = new HashMap<>();
        this.color = color;
        allTerrs = new HashMap<>();
    }
//one territory: unit type, unit num,
    public Player(String color,Map<String,Integer> resources,Map<String, Map<String,Integer>> terrsAndUnits,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
        this.color = color;
        tm = new TerritoryMap();
        territories = new HashSet<>();
        allTerrAndUnits = new HashMap<>();
        currTerrAndUnits = terrsAndUnits;
        totalResource = new HashSet<>();
        allTerrs = new HashMap<>();
        this.spiesOnMap = spiesOnMap;
        this.setTotalResource(totalResource, resources);
        this.setTerritory(territories,terrsAndUnits,spiesOnMap,terrAndCloakCount);
    }

    public Map<String, Map<String, Integer>> getSpiesOnMap() {
        return spiesOnMap;
    }

    public String getColor() {
        return color;
    }

    public Set<Territory> getTerritories() {
        return territories;
    }

    public void setCloak(String name) {
        allTerrs.get(name).setCount(3);
        System.out.println("~~~~~~~~~~~Round~~~~~~~~~~~");
        System.out.println(name + ": " + allTerrs.get(name).getCount());
    }

    //only contains neighbour not their own territory
    public boolean isNeighbour(Set<Territory> myTerrs,String terrName){
        for(Territory myTerr: myTerrs){
            if(myTerr.terrName.equals(terrName)){
                return false;
            }
        }
        return true;
    }
    //Update the variable of serialize: Map<String, Map<String,Territory>>
    public void setTerrInfo(){
        //update terrinfo in the serialize class
        Map<String,Territory> visibleTerrs = serialize.terrInfo.getPlayerAndTerrs().get(this.color);
        for(Entry<String,Territory> e: visibleTerrs.entrySet()){
         //   System.out.println("territory name: "+e.getValue().terrName);
            e.getValue().isNew=false;
        }

        //every round, search for all territory curr player has and put it in the terrinfo
       // Set<String> currTerrName = new HashSet<>();
        for(Territory curr:territories){
            curr.isNew=true;
            visibleTerrs.put(curr.terrName,curr);
        }

        //except its own terrs, terr's neighbour can be seen
        Map<String,Territory> terrNeigh = new HashMap<>();
        for(Territory curr:territories){

            for (String terrName : curr.adjList) {
                    //exclude those their own territoriories

                    Territory Neigh = this.allTerrs.get(terrName);

                    if(isNeighbour(territories,Neigh.terrName)&&!terrNeigh.containsKey(Neigh.terrName)) {
                        terrNeigh.put(Neigh.terrName, Neigh);
                    }
            }
        }

          //  System.out.println("all neighbour: ");
        for (Entry<String,Territory> e:terrNeigh.entrySet()) {
            //**********Need to add, if current territory is not cloaked**********
            Territory currNeigh = e.getValue();
            if(currNeigh.count == 0) {
                currNeigh.isNew = true;
                visibleTerrs.put(currNeigh.terrName, currNeigh);
            }
        }


        //for all terr, as long as there is spy, the territory is visible
        for(Entry<String,Territory> e: this.allTerrs.entrySet()) {
            Territory currTerr = e.getValue();
            for(Entry<String,Integer> spyOwnerAndNum:currTerr.allSpiesOnCurrTerr.entrySet()){
                if(spyOwnerAndNum.getKey().equals(this.color)){
                    visibleTerrs.put(currTerr.terrName, currTerr);
                    currTerr.isNew=true;
                }
            }
        }

        //if territory is not being updated, then it is old info

    }

    //when click a territory, print all the info of it
    public String getTerritoryInfo(String terrName) {
        String ans = "<html><body><p>";
        if (!serialize.terrInfo.getPlayerAndTerrs().get(this.getColor()).containsKey(terrName)) {
            ans += "Name: " + terrName + "<br/>";
            ans += "</p></body></html>";
            return ans;
        }
        Territory currTerr = serialize.terrInfo.getPlayerAndTerrs().get(this.color).get(terrName);
        ans+="Name: "+currTerr.terrName+"<br/>";
        ans+="Size: "+currTerr.size+"<br/>";
        ans+="Food Production: "+currTerr.getFoodNum()+"<br/>";
        ans+="Owner: " + currTerr.ownerName + "<br/>";
        ans+="Units:  Type  Number<br/>";
        String owner = currTerr.ownerName;
        //if terr is mine, I can see the spy
        if(owner.equals(this.color)){
            for(Unit u:currTerr.units) {
                ans += "        " + u.type + "  " + u.num + "<br/>";
            }
            if(currTerr.allSpiesOnCurrTerr.containsKey(this.color)){
                ans += "        " + "Spy" + "  " + currTerr.allSpiesOnCurrTerr.get(this.color) + "<br/>";
            }
        }
        else {
            for (Unit u : currTerr.units) {
                if (!u.type.equals("Spy")||u.owner.equals(this.color)) {
                    ans += "        " + u.type + "  " + u.num + "<br/>";
                }
            }
            if(currTerr.allSpiesOnCurrTerr.containsKey(this.color)){
                ans += "        " + "Spy" + "  " + currTerr.allSpiesOnCurrTerr.get(this.color) + "<br/>";
            }
        }
        ans += "</p ></body></html>";
        return ans;
    }
    public Territory getTerritory(String terrName) {
        for(Entry<String, Player> e: this.allTerrAndUnits.entrySet()) {
            Player currPlayer = e.getValue();
            for(Territory t: currPlayer.territories) {
                if(t.terrName.equals(terrName)) {
                    return t;
                }
            }
        }
        return null;
    }


     public TerritoryMap getTm() {
        return tm;
     }


    public void setTerritory(Set<Territory> territories, Map<String, Map<String,Integer>> terrsAndUnits,Map<String,Map<String,Integer>> spiesOnMap,Map<String,Integer> terrAndCloakCount) {
    	Territory terr=null;
    	for(Map.Entry<String, Map<String,Integer>> e:terrsAndUnits.entrySet()) {
	    	if(e.getKey().equals("Narnia")) {
	    		terr = new Narnia(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	        }
	    	else if(e.getKey().equals("Midkemia")) {
	    		terr = new Midkemia(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	else if(e.getKey().equals("Oz")) {
	    		terr = new Oz(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	else if(e.getKey().equals("Gondor")) {
	    		terr = new Gondor(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	else if(e.getKey().equals("Mordor")) {
	    		terr = new Mordor(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	else if(e.getKey().equals("Hogwarts")) {
	    		terr = new Hogwarts(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	else if(e.getKey().equals("Elantris")) {
	    		terr = new Elantris(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	else if(e.getKey().equals("Scadrial")) {
	    		terr = new Scadrial(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	else if(e.getKey().equals("Roshar")) {
	    		terr = new Roshar(this.color,e.getValue(),spiesOnMap,terrAndCloakCount);
	    	}
	    	territories.add(terr);
    	}
    }
    public Set<Resource> getTotalResource() {
		// TODO Auto-generated method stub
		return this.totalResource;
	}
	public Map<String, Map<String, Integer>> getCurrTerrAndUnits() {
		// TODO Auto-generated method stub
		return this.currTerrAndUnits;
	}
    //set the field of total resource
    public void setTotalResource(Set<Resource> totalResource,Map<String,Integer> resources) {
    	Resource temp = null;
    	for(Entry<String,Integer> e:resources.entrySet()) {
    		if(e.getKey().equals("Food")) {
    			temp=new Food(e.getValue());
    		}
    		totalResource.add(temp);
    	}
    }
    
    public Map<String, Player> getAllTerrAndUnits() {
        return allTerrAndUnits;
    }

    //if terr name belongs to current player
    public boolean containTerritory(String name) {
        Iterator it = this.territories.iterator();
        while (it.hasNext()) {
            Territory curr = (Territory) it.next();
            if (curr.getTerrName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> canSpyMoveDstTerr(String srcTerr){
        Set<String> ans = new HashSet<>();
        //if spy is on player's own terr
        if(this.territories.contains(this.getTerritory(srcTerr))){
            for(Territory curr:territories){
                ans.addAll(curr.adjList);
            }
            ans.addAll(canMoveDstTerr(srcTerr));
        }
        else{
            Territory src = getTerritory(srcTerr);
            ans.addAll(src.adjList);
        }
        ans.remove(srcTerr);
        return ans;
    }

    //return the territories that either is their own's or have spy on it
    public Set<String> canMoveTerr(){
        Set<String> ans = new HashSet<>();
        for(Territory curr:this.territories){
            ans.add(curr.terrName);
        }
        //those territory that have this player's spy
        for(Entry<String,Territory> e: this.allTerrs.entrySet()) {
            Territory currTerr = e.getValue();
            for(Entry<String,Integer> spyOwnerAndNum:currTerr.allSpiesOnCurrTerr.entrySet()){
                if(spyOwnerAndNum.getKey().equals(this.color)){
                    ans.add(currTerr.terrName);
                }
            }
        }
        return ans;
    }

    public Set<String> canMoveDstTerr(String srcTerr){
	    Stack<String> s = new Stack<>();
        Set<String> isVisited = new HashSet<>();
        s.push(srcTerr);
        isVisited.add(srcTerr);

        //use dfs to see if there is a path
        while (!s.empty()) {
            String currTerr = s.pop();
            
            for (String currAdjTerr : tm.allTerritories.get(currTerr)) {
                //if current territory's neighbor belongs to current player and has not been visited
                if (containTerritory(currAdjTerr) && !isVisited.contains(currAdjTerr)) {
                    s.push(currAdjTerr);
                    isVisited.add(currAdjTerr);
                }
            }
        }
        isVisited.remove(srcTerr);
        return isVisited;
    }

    //assess if two player's attribute is equal
    public boolean equals(Player other) {
        if (!other.color.equals(this.color))
            return false;

        Map<String, Map<String, Integer>> currMap = this.currTerrAndUnits;
        Map<String, Map<String, Integer>> otherMap = other.currTerrAndUnits;

        for (Map.Entry<String, Map<String, Integer>> entry : otherMap.entrySet()) {
            String territoryName = entry.getKey();
            if (!currMap.containsKey(territoryName)) {
                return false;
            }
            Map<String, Integer> currUnit = currMap.get(territoryName);
            Map<String, Integer> otherUnit = otherMap.get(territoryName);
            for (Map.Entry<String, Integer> entry_inside : otherUnit.entrySet()) {
                String unitLevel = entry_inside.getKey();
                if (!currUnit.containsKey(unitLevel)) {
                    return false;
                }
                if (!currUnit.get(unitLevel).equals(otherUnit.get(unitLevel))) {
                    return false;
                }
            }
        }

        return true;
    }


}
