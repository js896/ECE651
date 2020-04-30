package edu.duke.ece651;
import java.util.*;

public class Move extends Action {
  @Override
  public void getAttackRes(Data data, String Action, Boolean high) {
    return;
  }

  @Override
  public void getRes(Data data, String player, String Action) {
    Check check = new CheckMove();
    if (check.checkStatus(data, player, Action)) {
      Dijkstra dijkstra = new Dijkstra(data, player, Action);
      String[] attributes = Action.split("~");
      String unitLevel = attributes[1];
      int unitNum = Integer.parseInt(attributes[2]);
      String src = attributes[3];
      String dest = attributes[4];
      int cost;
      //int cost = dijkstra.getShortestPath(dest) * unitNum;
      // int resource = Integer.parseInt(data.resourceMap.get(player));
      //resource -= cost;
      //data.resourceMap.replace(player, Integer.toString(resource));
      if (unitLevel.equals("Spy")) {
        cost = data.sizeMap.get(dest) * unitNum;
        Map<String, Integer> ttSpyMap = data.spyMap.get(player);
        int left = ttSpyMap.get(src) - unitNum;
        ttSpyMap.replace(src, left);
        //if nothing left, delete from map
        if (left == 0) {
          ttSpyMap.remove(src);
        }
        if (ttSpyMap.keySet().contains(dest)) {
          ttSpyMap.replace(dest, ttSpyMap.get(dest) + unitNum);
        }
        //if no spys before, create a new entry
        else {
          ttSpyMap.put(dest, unitNum);
        }
      }
      else {
        cost = dijkstra.getShortestPath(dest) * unitNum;
        data.unitMap.get(src).replace(unitLevel, data.unitMap.get(src).get(unitLevel) - unitNum);
        data.unitMap.get(dest).replace(unitLevel, data.unitMap.get(dest).get(unitLevel) + unitNum);
      }
      int resource = Integer.parseInt(data.resourceMap.get(player));
      resource -= cost;
      data.resourceMap.replace(player, Integer.toString(resource));
    }
  }
}
