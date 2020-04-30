package edu.duke.ece651;

import java.util.HashMap;
import java.util.Map;

public class Upgrade extends Action {
  Map<String, Integer> nameAndCost;

  public Upgrade() {
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
  public void getAttackRes(Data data, String Action, Boolean high) {
    return;
  }

  @Override
  public void getRes(Data data, String player, String Action) {
    Check check = new CheckUpgrade();
    String[] attributes = Action.split("~");
    String terr = attributes[1];
    String srcUnit = attributes[2];
    int unitNum = Integer.parseInt(attributes[3]);
    String dstUnit = attributes[4];
    Map<String, Integer> unitLevelAndNum = data.unitMap.get(terr);
    int totalCost = (this.nameAndCost.get(dstUnit) - this.nameAndCost.get(srcUnit)) * unitNum;
    int ownedResource = Integer.parseInt(data.resourceMap.get(player));
    if (check.checkStatus(data, player, Action)) {
      //resource decrease
      data.resourceMap.replace(player, String.valueOf(ownedResource - totalCost));
      if (dstUnit.equals("Spy")) {
        unitLevelAndNum.replace(srcUnit, unitLevelAndNum.get(srcUnit) - unitNum);
        if (data.spyMap.containsKey(player)) {
          Map<String, Integer> ttSpyMap = data.spyMap.get(player);
          ttSpyMap.replace(terr, ttSpyMap.get(terr) + unitNum);
        } else {
          Map<String, Integer> ttSpyMap = new HashMap<>();
          data.spyMap.put(player, ttSpyMap);
          ttSpyMap.put(terr, unitNum);
        }
      }
      else {
        //update unit map
        unitLevelAndNum.replace(srcUnit, unitLevelAndNum.get(srcUnit) - unitNum);
        unitLevelAndNum.replace(dstUnit, unitLevelAndNum.get(dstUnit) + unitNum);
      }
    }
  }
}
