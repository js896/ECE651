package edu.duke.ece651;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Attack extends Action {
  private static int MAX = 20;
  private static int MIN = 1;

  private static Map<String, Integer> bonusMap = new HashMap<String, Integer>();

  private static void init() {
    bonusMap.put("Private", 0);
    bonusMap.put("Corporal", 1);
    bonusMap.put("Sergent", 3);
    bonusMap.put("Lieutenant", 5);
    bonusMap.put("Captain", 8);
    bonusMap.put("Major", 11);
    bonusMap.put("Colonel", 15);
  }
  private static Map<String, Integer> attackerMap = new HashMap<String, Integer>();

  @Override
  public void getAttackRes(Data data, String Action, Boolean high) {
    int attckerBonus;
    int defenderBonus;
    init();
    Check check = new CheckAttack();
    String[] attributes = Action.split("~");
    attckerBonus = bonusMap.get(attributes[1]);
    String src = attributes[3];
    String dst = attributes[4];
    String attackerLevel = attributes[1];
    int attackerNumber = Integer.parseInt(attributes[2]);
    String defenderLevel;
    String defender = getPlayer(data, attributes[4]);
    String attacker = getPlayer(data, attributes[3]);
    if (check.checkStatus(data, null, Action)) {
      if (high) {
        defenderLevel = getHigh(data, attributes[4]);
      }
      else {
        defenderLevel = getLow(data, attributes[4]);
      }
       if (defenderLevel.equals("nothing")) {
         changeOwnership(data, src, dst, defender, attacker);
         return;
       }
      defenderBonus = bonusMap.get(defenderLevel);
      //attacker's units decrease
      data.unitMap.get(src).replace(attackerLevel,
                                    data.unitMap.get(src).get(attackerLevel) - attackerNumber);
      //begin tosing the dice
      int randAttack = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
      int randDefend = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
      //defender wins a battle in the combat
      if (randAttack + attckerBonus <= randDefend + defenderBonus) {
      }
      //attacker wins a battle in the combat
      else {
        attackerMap.replace(attackerLevel, attackerMap.get(attackerLevel) + 1);
        data.unitMap.get(dst).replace(defenderLevel, data.unitMap.get(dst).get(defenderLevel) - 1);
      }
      int resource = Integer.parseInt(data.resourceMap.get(attacker));
      resource -= 1;
      data.resourceMap.replace(attacker, Integer.toString(resource));
      //attacker wins the battle
      if (getLow(data, attributes[4]).equals("nothing")) {
        changeOwnership(data, src, dst, defender, attacker);
        return;
      }
    }
  }

  private static void changeOwnership(Data data,
                                      String src,
                                      String dst,
                                      String defender,
                                      String attacker) {
    data.unitMap.replace(dst, attackerMap);
    //loser loses the dst
    List<String> newDefenderList = data.ownMap.get(defender);
    newDefenderList.remove(dst);
    data.ownMap.replace(defender, newDefenderList);
    // winner gets the dst
    List<String> newAttackerList = data.ownMap.get(attacker);
    newAttackerList.add(dst);
    data.ownMap.replace(attacker, newAttackerList);
  }

  private static String getPlayer(Data data, String tt) {
    List<String> targetList = new ArrayList<String>();
    Collection<List<String>> values = data.ownMap.values();
    for (List<String> value : values) {
      if (value.contains(tt)) {
        targetList = value;
        break;
      }
      else {
        continue;
      }
    }
    return getKeyByValue(data.ownMap, targetList);
  }

  private static String getKeyByValue(Map<String, List<String>> ownMap, List<String> value) {
    for (Map.Entry<String, List<String>> entry : ownMap.entrySet()) {
      if (value.equals(entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

  private static String getHigh(Data data, String dst) {
    Map<String, Integer> bMap = data.unitMap.get(dst);
    if (bMap.get("Colonel") != 0) {
      return "Colonel";
    }
    else if (bMap.get("Major") != 0) {
      return "Major";
    }
    else if (bMap.get("Captain") != 0) {
      return "Captain";
    }
    else if (bMap.get("Lieutenant") != 0) {
      return "Lieutenant";
    }
    else if (bMap.get("Sergent") != 0) {
      return "Sergent";
    }
    else if (bMap.get("Corporal") != 0) {
      return "Corporal";
    }
    else if (bMap.get("Private") > 0) {
      return "Private";
    }
    else {
      return "nothing";
    }
  }

  private static String getLow(Data data, String dst) {
    Map<String, Integer> bMap = data.unitMap.get(dst);
    if (bMap.get("Private") != 0) {
      return "Private";
    }
    else if (bMap.get("Corporal") != 0) {
      return "Corporal";
    }
    else if (bMap.get("Sergent") != 0) {
      return "Sergent";
    }
    else if (bMap.get("Lieutenant") != 0) {
      return "Lieutenant";
    }
    else if (bMap.get("Captain") != 0) {
      return "Captain";
    }
    else if (bMap.get("Major") != 0) {
      return "Major";
    }
    else if (bMap.get("Colonel") > 0) {
      return "Colonel";
    }
    else {
      return "nothing";
    }
  }

  public Attack() {
    attackerMap.put("Private", 0);
    attackerMap.put("Corporal", 0);
    attackerMap.put("Sergent", 0);
    attackerMap.put("Lieutenant", 0);
    attackerMap.put("Captain", 0);
    attackerMap.put("Major", 0);
    attackerMap.put("Colonel", 0);
  }

  @Override
  public void getRes(Data data, String player, String Action) {}
}
