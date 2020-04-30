package edu.duke.ece651;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CheckAttack implements Check{

	
	@Override
	public Boolean checkStatus(Data data, String p, String Action) {
		Map<String,List<String>> ownMap = data.ownMap;
		
	    String[] attributes = Action.split("~");
	    String unitLevel = attributes[1];
        int unitNum = Integer.parseInt(attributes[2]);
        String srcTerr = attributes[3];
        String dstTerr = attributes[4];
        String player = this.getPlayer(srcTerr,ownMap);
	    Map<String, Integer> unitLevelAndNum = data.unitMap.get(srcTerr);
	    List<String> territories = data.ownMap.get(player);
	    //check if attack cost>player's resource
	    if(unitNum*1>Integer.parseInt(data.resourceMap.get(player)))
	    	return false;
	    return true;
	}
	public String getPlayer(String terr,Map<String,List<String>> ownMap) {
		for(Entry<String,List<String>> e:ownMap.entrySet()) {
			for(String currTerr: e.getValue()) {
				if(currTerr.equals(terr))
					return e.getKey();
			}
		}
		return null;
	}
}
