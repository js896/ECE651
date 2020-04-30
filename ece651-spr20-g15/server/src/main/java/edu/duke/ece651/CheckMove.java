package edu.duke.ece651;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CheckMove implements Check {
  //move cost<=total resource
  //move=size*number
  @Override
  public Boolean checkStatus(Data data, String player, String Action) {
    Dijkstra dijkstra = new Dijkstra(data, player, Action);

    int ownedResource = Integer.parseInt(data.resourceMap.get(player));
    List<String> territories = data.ownMap.get(player);
    String[] attributes = Action.split("~");
    String unitLevel = attributes[1];
    int unitNum = Integer.parseInt(attributes[2]);
    String srcTerr = attributes[3];
    String dstTerr = attributes[4];
    int cost;
    if (unitLevel.equals("Spy")) {
      cost = data.sizeMap.get(dstTerr);
    }
    else {
      cost = dijkstra.getShortestPath(dstTerr);
    }
    Map<String, Integer> unitLevelAndNum = data.unitMap.get(srcTerr);

    if (ownedResource < cost)
      return false;
    return true;
  }

  private Boolean checkPath(String player, String src, String dst, Data data) {
    List<String> visited = new ArrayList<String>();
    Stack<String> stack = new Stack<String>();
    String curr;
    stack.push(src);
    while (!stack.empty()) {
      curr = stack.pop();
      if (curr.equals(dst)) {
        return true;
      }
      if (!visited.contains(curr)) {
        visited.add(curr);
      }
      for (String neighbor : data.adjacentMap.get(curr)) {
        if (!data.ownMap.get(player).contains(neighbor)) {
          continue;
        }
        if (!visited.contains(neighbor)) {
          stack.push(neighbor);
        }
      }
    }
    return false;
  }
}
