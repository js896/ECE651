package edu.duke.ece651;

import java.util.HashMap;
import java.util.Map;


//store 9 territory information for each player
//each include owner, unit, unit type
public class TerrInfo {
    //player    territory name,
    public Map<String, Map<String,Territory>> playerAndTerrs;
    public TerrInfo(){
        playerAndTerrs=new HashMap<>();
    }

    public Map<String, Map<String, Territory>> getPlayerAndTerrs() {
        return playerAndTerrs;
    }
}
