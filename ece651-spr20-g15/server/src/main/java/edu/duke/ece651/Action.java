package edu.duke.ece651;

import java.util.*;
import java.lang.String;

public abstract class Action {
  public static void getUnits(String cmd, Data data) {
    String[] players = cmd.split("\\*");
    for (String player : players) {
      String filtered = player.substring(0, player.indexOf("%"));
      String[] attributes = filtered.split("-");
      for (String attribute : attributes) {
        if (attribute.contains("+")) {
          String units = attribute.substring(attribute.indexOf("(") + 1, attribute.indexOf(")"));
          String[] subAttrs = units.split("\\+");
          List<Integer> numOfUnits = new ArrayList<Integer>();
          for (String subAttr : subAttrs) {
            numOfUnits.add(Integer.parseInt(subAttr.substring(subAttr.indexOf("_") + 1)));
          }
          Map<String, Integer> map = new HashMap<String, Integer>();
          map.put("Private", numOfUnits.get(0));
          map.put("Corporal", numOfUnits.get(1));
          map.put("Sergent", numOfUnits.get(2));
          map.put("Lieutenant", numOfUnits.get(3));
          map.put("Captain", numOfUnits.get(4));
          map.put("Major", numOfUnits.get(5));
          map.put("Colonel", numOfUnits.get(6));
          data.unitMap.put(attribute.substring(0, attribute.indexOf("+")), map);
        }
      }
    }
  }

  public static void getOwner(String cmd, Data data) {
    String[] attributes = cmd.split("\\*");
    for (String attribute : attributes) {
      String filtered = attribute.substring(0, attribute.indexOf("%"));
      List<String> owns = new ArrayList<String>();
      String[] subAttrs = filtered.split("-");
      for (String subAttr : subAttrs) {
        if (subAttr.contains("+")) {
          owns.add(subAttr.substring(0, subAttr.indexOf("+")));
        }
      }
      data.ownMap.put(attribute.substring(0, attribute.indexOf("^")), owns);
    }
  }

  public static void getAction(String cmd, Data data) {
    String[] attributes = cmd.split("\\*");
    for (String attribute : attributes) {
      int formerBracket;
      int latterBracket;
      String Action;
      formerBracket = attribute.indexOf("{");
      latterBracket = attribute.indexOf("}");
      Action = attribute.substring(formerBracket + 1, latterBracket);
      List<String> Actions = new ArrayList<String>();
      String[] subAttrs = Action.split("@");
      for (String subAttr : subAttrs) {
        Actions.add(subAttr);
      }
      data.actionMap.put(attribute.substring(0, attribute.indexOf("^")), Actions);
    }
  }

  public static void getResource(String cmd, Data data) {
    String[] attributes = cmd.split("\\*");
    for (String attribute : attributes) {
      int former = attribute.indexOf("^");
      int latter = attribute.indexOf("^", former + 1);
      String resource = attribute.substring(former + 1, latter);
      String num = resource.substring(resource.indexOf("$") + 1);
      data.resourceMap.put(attribute.substring(0, attribute.indexOf("^")), num);
    }
  }

  private static void produceResource(Data data, String player) {
    List<String> tts = data.ownMap.get(player);
    for (String tt : tts) {
      int resource = Integer.parseInt(data.resourceMap.get(player));
      resource += data.productionMap.get(tt);
      data.resourceMap.replace(player, Integer.toString(resource));
    }
  }

  private static String serializeUnits(Map<String, Integer> units) {
    StringBuilder sb = new StringBuilder();
    Iterator<Map.Entry<String, Integer>> iter = units.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<String, Integer> entry = iter.next();
      sb.append(entry.getKey() + "_" + Integer.toString(entry.getValue()));
      sb.append("+");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  public static String serializeSpy(Data data) {
    StringBuilder sb = new StringBuilder();
    Iterator<Map.Entry<String, Integer>> iter = data.cloakMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<String, Integer> entry = iter.next();
      String tt = entry.getKey();
      sb.append("(");
      sb.append(tt);
      sb.append("-");
      sb.append("[");
      for (String player : data.spyMap.keySet()) {
        Map<String, Integer> ttSpyMap = data.spyMap.get(player);
        for (String territory : ttSpyMap.keySet()) {
          if (territory.equals(tt)) {
            String numSpy = Integer.toString(ttSpyMap.get(territory));
            sb.append(player);
            sb.append("#");
            sb.append(numSpy);
            sb.append("&");
            break;
          }
        }
      }
      if (!(sb.charAt(sb.length() - 1) == '[')) {
        sb.deleteCharAt(sb.length() - 1);
      }
      sb.append("]");
      sb.append("-");
      int cloakTimes = data.cloakMap.get(tt);
      if (cloakTimes > 0) {
        sb.append(Integer.toString(cloakTimes - 1));
      }
      else {
        sb.append("0");
      }
      sb.append(")+");
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  private static String serialize(Data data) {
    Iterator<Map.Entry<String, Map<String, Integer>>> iter = data.unitMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<String, Map<String, Integer>> entry = iter.next();
      entry.getValue().replace("Private", entry.getValue().get("Private") + 1);
    }
    for (String player : data.ownMap.keySet()) {
      produceResource(data, player);
    }
    StringBuilder sb = new StringBuilder();
    Iterator<Map.Entry<String, List<String>>> itr = data.ownMap.entrySet().iterator();
    while (itr.hasNext()) {
      Map.Entry<String, List<String>> entry = itr.next();
      if (!entry.getValue().isEmpty()) {
        sb.append(entry.getKey());
        sb.append("^Food$" + data.resourceMap.get(entry.getKey()) + "^");
        for (String tt : entry.getValue()) {
          sb.append("-");
          sb.append(tt);
          sb.append("+");
          sb.append("(");
          sb.append(serializeUnits(data.unitMap.get(tt)));
          sb.append(")");
        }
        sb.append("%");
        sb.append(serializeSpy(data));
        sb.append("*");
      }
      else {
        continue;
      }
    }
    sb.deleteCharAt(sb.length() - 1);
    //serialize the spy part
    return sb.toString();
  }

  private static String getAct(String action) { return action.substring(0, action.indexOf("~")); }

  public static void getCloak(String cmd, Data data) {
    String[] attributes = cmd.split("\\*");
    for (String attribute : attributes) {
      int delimiter = attribute.indexOf("%");
      String filtered = attribute.substring(0, delimiter);
      String[] subAttrs = filtered.split("-");
      for (String subAttr : subAttrs) {
        if (subAttr.contains("+")) {
          String tt = subAttr.substring(0, subAttr.indexOf("+"));
          String withoutTt = subAttr.substring(subAttr.indexOf("+") + 1);
          int cloakTimes = Integer.parseInt(withoutTt.substring(0, withoutTt.indexOf("+")));
          data.cloakMap.put(tt, cloakTimes);
        }
      }
    }
  }

  public static void getSpy(String cmd, Data data) {
    String[] attributes = cmd.split("\\*");
    for (String attribute : attributes) {
      Map<String, Integer> ttSpyMap = new HashMap<String, Integer>();
      int delimiter = attribute.indexOf("%");
      if (delimiter == attribute.length() - 1) {
        continue;
      }
      String filtered = attribute.substring(delimiter + 1);
      String[] spys = filtered.split("-");
      for (String spy : spys) {
        int formerBracket = spy.indexOf("(");
        int latterBracket = spy.indexOf(")");
        String target = spy.substring(formerBracket + 1, latterBracket);
        ttSpyMap.put(target.substring(0, target.indexOf("+")),
                     Integer.parseInt(target.substring(target.indexOf("+") + 1)));
      }
      data.spyMap.put(attribute.substring(0, attribute.indexOf("^")), ttSpyMap);
    }
  }

  public static String Execute(String cmd) {
    Data data = new Data();
    data.init();
    getResource(cmd, data);
    getUnits(cmd, data);
    getOwner(cmd, data);
    getAction(cmd, data);
    getCloak(cmd, data);
    getSpy(cmd, data);
    Iterator<Map.Entry<String, List<String>>> itr = data.actionMap.entrySet().iterator();
    Map<String, List<String>> moveMap = new HashMap<String, List<String>>();
    Map<String, List<String>> attackMap = new HashMap<String, List<String>>();
    //    Map<String, List<String>> srcAttackMap = new HashMap<String, List<String>>();
    //for all players
    while (itr.hasNext()) {
      List<String> moves = new ArrayList<String>();
      Map.Entry<String, List<String>> entry = itr.next();
      List<String> Actions = entry.getValue();
      //for each cmd of a player
      for (String command : Actions) {
        System.out.println("~~~~~~~~~~~~~~~~\ncmd:");
        System.out.println(command);
        String act = getAct(command);
        //first execute Upgrade
        if (act.equals("Upgrade")) {
          Action action = new Upgrade();
          action.getRes(data, entry.getKey(), command);
        }
        //Attack
        else if (act.equals("Attack")) {
          String[] attribute = command.split("~");
          if (attackMap.containsKey(attribute[4])) {
            attackMap.get(attribute[4]).add(command);
          }
          else {
            attackMap.put(attribute[4], new ArrayList<String>());
            attackMap.get(attribute[4]).add(command);
          }
        }
        //Move
        else if (act.equals("Move")) {
          moves.add(command);
        }
        //Done
        else {
          Action action = new Done();
          action.getRes(data, entry.getKey(), command);
        }
      }
      moveMap.put(entry.getKey(), moves);
    }
    //then execute Move
    Iterator<Map.Entry<String, List<String>>> iter = moveMap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<String, List<String>> entry = iter.next();
      for (String move : entry.getValue()) {
        Action action = new Move();
        action.getRes(data, entry.getKey(), move);
      }
    }
    //finally the Attacks, first make attacks with multiple units multiple attacks
    for (String dst : attackMap.keySet()) {
      List<String> attacks = attackMap.get(dst);
      List<String> temp = new ArrayList<>();
      for (String curr : attacks) {
        temp.add(curr);
      }
      for (String attack : temp) {
        String[] args = attack.split("~");
        if (!args[2].equals("1")) {
          int times = Integer.parseInt(args[2]);
          attacks.remove(attack);
          for (int i = 0; i < times; i++) {
            attacks.add(args[0] + "~" + args[1] + "~1~" + args[3] + "~" + args[4]);
          }
        }
      }
    }
    //execute Attacks
    for (String dst : attackMap.keySet()) {
      List<String> unsorteds = attackMap.get(dst);
      Action action = new Attack();
      while (!unsorteds.isEmpty()) {
        action.getAttackRes(data, getHigh(unsorteds), true);
        unsorteds.remove(getHigh(unsorteds));
        if (unsorteds.isEmpty()) {
          break;
        }
        action.getAttackRes(data, getLow(unsorteds), false);
        unsorteds.remove(getLow(unsorteds));
      }
    }
    return serialize(data);
  }

  private static String getHigh(List<String> unsorteds) {
    for (String s : unsorteds) {
      if (s.contains("Colonel")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Major")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Captain")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Lieutenant")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Sergent")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Corporal")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Private")) {
        return s;
      }
    }
    return "placeholder";
  }

  private static String getLow(List<String> unsorteds) {
    for (String s : unsorteds) {
      if (s.contains("Private")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Corporal")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Sergent")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Lieutenant")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Captain")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Major")) {
        return s;
      }
    }
    for (String s : unsorteds) {
      if (s.contains("Colonel")) {
        return s;
      }
    }
    return "placeholder";
  }

  //public abstract Boolean checkStatus(Data data, String player, String Action);
  public abstract void getAttackRes(Data data, String Action, Boolean high);
  public abstract void getRes(Data data, String player, String Action);
}
