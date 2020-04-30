package edu.duke.ece651;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckUpgrade implements Check {
  Map<String, Integer> nameAndLevel;
  Map<String, Integer> nameAndCost;
  public CheckUpgrade() {
    nameAndLevel = new HashMap<>();
    nameAndLevel.put("Private", 0);
    nameAndLevel.put("Corporal", 1);
    nameAndLevel.put("Sergent", 2);
    nameAndLevel.put("Spy", 3);
    nameAndLevel.put("Lieutenant", 4);
    nameAndLevel.put("Captain", 5);
    nameAndLevel.put("Major", 6);
    nameAndLevel.put("Colonel", 7);
    nameAndCost = new HashMap<>();
    nameAndCost.put("Private", 0);
    nameAndCost.put("Corporal", 3);
    nameAndCost.put("Sergent", 11);
    nameAndCost.put("Spy", 20);
    nameAndCost.put("Lieutenant", 30);
    nameAndCost.put("Captain", 55);
    nameAndCost.put("Major", 90);
    nameAndCost.put("Colonel", 140);
  }
  @Override
  public Boolean checkStatus(Data data, String player, String Action) {
    List<String> territories = data.ownMap.get(player);
    String[] attributes = Action.split("~");
    String terr = attributes[1];
    String srcUnit = attributes[2];
    int unitNum = Integer.parseInt(attributes[3]);
    String dstUnit = attributes[4];
    int totalCost = (this.nameAndCost.get(dstUnit) - this.nameAndCost.get(srcUnit)) * unitNum;
    int ownedResource = Integer.parseInt(data.resourceMap.get(player));

    if (!territories.contains(terr))
      return false;
    // check if new level > current level
    if (this.nameAndLevel.get(srcUnit) >= this.nameAndLevel.get(dstUnit))
      return false;
    // total cost for upgrade <=owned resource
    if (totalCost > ownedResource)
      return false;
    return true;
  }
}
