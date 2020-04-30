package edu.duke.ece651.sallystash;

import java.util.*;
import java.io.*;

public class Board {
  public HashMap<String, Stash> objMap = new HashMap<String, Stash>();
  public HashMap<String, Stash> temp = new HashMap<String, Stash>();
  public ArrayList<String> grids = new ArrayList<String>();
  public ArrayList<String> hitCoor = new ArrayList<String>();
  public ArrayList<Character> hitColors = new ArrayList<Character>();
  public ArrayList<String> missCoor = new ArrayList<String>();
  public int unhitted = 43;
  public String guessRec = new String();
  public ArrayList<String> toOppo = new ArrayList<String>();
  public int tokenMove = 3;
  public int tokenScan = 3;

  public void initiate() {
    grids.add("  0|1|2|3|4|5|6|7|8|9  ");
    grids.add("A  | | | | | | | | |  A");
    grids.add("B  | | | | | | | | |  B");
    grids.add("C  | | | | | | | | |  C");
    grids.add("D  | | | | | | | | |  D");
    grids.add("E  | | | | | | | | |  E");
    grids.add("F  | | | | | | | | |  F");
    grids.add("G  | | | | | | | | |  G");
    grids.add("H  | | | | | | | | |  H");
    grids.add("I  | | | | | | | | |  I");
    grids.add("J  | | | | | | | | |  J");
    grids.add("K  | | | | | | | | |  K");
    grids.add("L  | | | | | | | | |  L");
    grids.add("M  | | | | | | | | |  M");
    grids.add("N  | | | | | | | | |  N");
    grids.add("O  | | | | | | | | |  O");
    grids.add("P  | | | | | | | | |  P");
    grids.add("Q  | | | | | | | | |  Q");
    grids.add("R  | | | | | | | | |  R");
    grids.add("S  | | | | | | | | |  S");
    grids.add("T  | | | | | | | | |  T");
    grids.add("  0|1|2|3|4|5|6|7|8|9  \n");

    toOppo = (ArrayList<String>)grids.clone();
  }

  public boolean addToMap(Stash stash) {
    char vertical = (char)stash.vertical;
    char horizontal = (char)(stash.horizontal + 48);
    String msg = checkCollidesAndOff(stash);
    if (msg != "good") {
      return false;
    }
    //rectangle
    if (stash.color == 'G' || stash.color == 'P') {
      if (stash.direction == 'H' || stash.direction == 'h') {
        for (int i = 0; i < stash.num_grids; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("G/P H " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
      else {
        for (int i = 0; i < stash.num_grids; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("G/P V " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
      }
    }
    //Superstacks
    else if (stash.color == 'R') {
      if (stash.direction == 'U' || stash.direction == 'u') {
        stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 0);
        // System.out.println("R U " + Character.toString(vertical) + Character.toString(horizontal));
        objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
        ++vertical;
        --horizontal;
        for (int i = 1; i < 4; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("R U " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
      else if (stash.direction == 'R' || stash.direction == 'r') {
        for (int i = 1; i < 4; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("R R " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        vertical -= 2;
        ++horizontal;
        stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 0);
        // System.out.println("R R " + Character.toString(vertical) + Character.toString(horizontal));
        objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
      }
      else if (stash.direction == 'D' || stash.direction == 'd') {
        for (int i = 1; i < 4; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 4 - i);
          // System.out.println("R D" + Character.toString(vertical) + Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
        ++vertical;
        horizontal -= 2;
        stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 0);
        // System.out.println("R D " + Character.toString(vertical) + Character.toString(horizontal));
        objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
      }
      else {
        for (int i = 1; i < 4; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 4 - i);
          // System.out.println("R L " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        vertical -= 2;
        --horizontal;
        stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 0);
        // System.out.println("R L " + Character.toString(vertical) + Character.toString(horizontal));
        objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
      }
    }
    //Crazystacks
    else {
      if (stash.direction == 'U' || stash.direction == 'u') {
        for (int i = 0; i < 3; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("B U " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        --vertical;
        ++horizontal;
        for (int i = 3; i < 6; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("B U " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
      }
      else if (stash.direction == 'R' || stash.direction == 'r') {
        for (int i = 0; i < 3; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("B R " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
        --horizontal;
        --vertical;
        for (int i = 3; i < 6; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), i);
          // System.out.println("B R " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
      else if (stash.direction == 'D' || stash.direction == 'd') {
        for (int i = 0; i < 3; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 5 - i);
          // System.out.println("B D " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        --vertical;
        --horizontal;
        for (int i = 0; i < 3; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 2 - i);
          // System.out.println("B D " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
      }
      else {
        for (int i = 0; i < 3; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 5 - i);
          // System.out.println("B L " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
        --horizontal;
        ++vertical;
        for (int i = 0; i < 3; ++i) {
          stash.keepTrack.put(Character.toString(vertical) + Character.toString(horizontal), 2 - i);
          // System.out.println("B L " + Character.toString(vertical) +
          //                    Character.toString(horizontal));
          objMap.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
    }
    return true;
  }

  public ArrayList<String> getKeys(HashMap<String, Stash> map, Stash value) {
    ArrayList<String> keyList = new ArrayList<String>();
    for (String key : map.keySet()) {
      if (map.get(key).equals(value)) {
        keyList.add(key);
      }
    }
    return keyList;
  }

  public void rmFromMap(Stash value) {
    ArrayList<String> keyList = new ArrayList<String>();
    for (String key : objMap.keySet()) {
      if (objMap.get(key).equals(value)) {
        keyList.add(key);
      }
    }
    for (int i = 0; i < keyList.size(); i++) {
      objMap.remove(keyList.get(i));
    }
  }

  public void addToTempMap(Stash stash) {
    char vertical = (char)stash.vertical;
    char horizontal = (char)(stash.horizontal + 48);
    //rectangle
    if (stash.color == 'G' || stash.color == 'P') {
      if (stash.direction == 'H' || stash.direction == 'h') {
        for (int i = 0; i < stash.num_grids; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
      else {
        for (int i = 0; i < stash.num_grids; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
      }
    }
    //Superstacks
    else if (stash.color == 'R') {
      if (stash.direction == 'U' || stash.direction == 'u') {
        temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
        ++vertical;
        --horizontal;
        for (int i = 1; i < 4; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
      else if (stash.direction == 'R' || stash.direction == 'r') {
        for (int i = 1; i < 4; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        vertical -= 2;
        ++horizontal;
        temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
      }
      else if (stash.direction == 'D' || stash.direction == 'd') {
        for (int i = 1; i < 4; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
        ++vertical;
        horizontal -= 2;
        temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
      }
      else {
        for (int i = 1; i < 4; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        vertical -= 2;
        --horizontal;
        temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
      }
    }
    //Crazystacks
    else {
      if (stash.direction == 'U' || stash.direction == 'u') {
        for (int i = 0; i < 3; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        --vertical;
        ++horizontal;
        for (int i = 3; i < 6; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
      }
      else if (stash.direction == 'R' || stash.direction == 'r') {
        for (int i = 0; i < 3; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
        --horizontal;
        --vertical;
        for (int i = 3; i < 6; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
      else if (stash.direction == 'D' || stash.direction == 'd') {
        for (int i = 0; i < 3; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
        --vertical;
        --horizontal;
        for (int i = 0; i < 3; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++vertical;
        }
      }
      else {
        for (int i = 0; i < 3; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
        --horizontal;
        ++vertical;
        for (int i = 0; i < 3; ++i) {
          temp.put(Character.toString(vertical) + Character.toString(horizontal), stash);
          ++horizontal;
        }
      }
    }
  }

  public String checkCollidesAndOff(Stash stash) {
    addToTempMap(stash);
    String msg = "good";
    int horizontal;
    ArrayList<String> keyList = getKeys(temp, stash);
    for (int i = 0; i < keyList.size(); ++i) {
      try {
        horizontal = Integer.parseInt(keyList.get(i).substring(1));
      }
      catch (NumberFormatException e) {
        temp.clear();
        msg = "Stack off the grid\n";
        return msg;
      }
      int vertical = keyList.get(i).charAt(0);
      if (vertical < 65 || vertical > 84) {
        temp.clear();
        msg = "Stack off the grid\n";
        return msg;
      }
      int column = horizontal * 2 + 2;
      int row = vertical - (int)'A' + 1;
      String line = grids.get(row);
      if (line.charAt(column) != ' ') {
        msg = "Collides with other stacks\n";
        temp.clear();
        return msg;
      }
    }
    return msg;
  }

  public String write(Stash stash) {
    String msg = "good";
    if (!addToMap(stash)) {
      msg = "bad";
      return msg;
    }
    ArrayList<String> keyList = getKeys(objMap, stash);
    for (int i = 0; i < keyList.size(); ++i) {
      // System.out.println("write coor " + keyList.get(i) + " color " + stash.color + " orien " +
      //                    stash.direction);
      int horizontal = Integer.parseInt(keyList.get(i).substring(1));
      int vertical = keyList.get(i).charAt(0);
      // System.out.println("ver " + (char)vertical + " hori " + (char)(horizontal + 48));
      int column = horizontal * 2 + 2;
      int row = vertical - (int)'A' + 1;
      int id = stash.keepTrack.get(keyList.get(i));
      String line = grids.get(row);

      StringBuilder sb = new StringBuilder(line);
      if (stash.hitted.contains(id)) {
        sb.setCharAt(column, '*');
      }
      else {
        sb.setCharAt(column, stash.color);
      }
      grids.set(row, sb.toString());
    }
    return msg;
  }

  public void display() {
    Iterator<String> iter = grids.iterator();
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
  }

  public void dispToOppo() {
    //put misses
    for (int i = 0; i < missCoor.size(); i++) {
      int column = Integer.parseInt(missCoor.get(i).substring(1, 2)) * 2 + 2;
      int row = missCoor.get(i).charAt(0) - (int)'A' + 1;
      if (missCoor.get(i).charAt(0) > 96) {
        row -= 32;
      }
      String line = toOppo.get(row);
      StringBuilder sb = new StringBuilder(line);
      sb.setCharAt(column, 'X');
      toOppo.set(row, sb.toString());
    }
    //put hits
    for (int i = 0; i < hitCoor.size(); i++) {
      int column = Integer.parseInt(hitCoor.get(i).substring(1, 2)) * 2 + 2;
      int row = hitCoor.get(i).charAt(0) - (int)'A' + 1;
      if (hitCoor.get(i).charAt(0) > 96) {
        row -= 32;
      }
      String line = toOppo.get(row);
      StringBuilder sb = new StringBuilder(line);
      sb.setCharAt(column, hitColors.get(i));
      toOppo.set(row, sb.toString());
    }
    Iterator<String> iter = toOppo.iterator();
    while (iter.hasNext()) {
      System.out.println(iter.next());
    }
  }

  public void update(int row, int column) {
    String line = grids.get(row);
    char color = line.charAt(column);
    hitColors.add(color);
    StringBuilder sb = new StringBuilder(line);
    sb.setCharAt(column, '*');
    grids.set(row, sb.toString());
  }

  public boolean check(String guess) {
    boolean res = true;
    int vertical = guess.charAt(0);
    int horizontal;
    //wring length
    if (guess.length() != 2) {
      res = false;
      return res;
    }
    //check first letter
    if (vertical < 65 || (vertical > 84 && vertical < 97) || vertical > 116) {
      res = false;
      return res;
    }
    //check second letter
    try {
      horizontal = Integer.parseInt(guess.substring(1, 2));
    }
    catch (NumberFormatException e) {
      res = false;
      return res;
    }
    return res;
  }

  public boolean checkNew(String newP) {
    if (!check(newP.substring(0, 2))) {
      return false;
    }
    ArrayList<String> orientation = new ArrayList<String>();
    orientation.add("U");
    orientation.add("u");
    orientation.add("D");
    orientation.add("d");
    orientation.add("L");
    orientation.add("l");
    orientation.add("R");
    orientation.add("r");
    orientation.add("H");
    orientation.add("h");
    orientation.add("V");
    orientation.add("v");

    if (!orientation.contains(newP.substring(2))) {
      return false;
    }
    return true;
  }

  public void clean(Stash theOne) {
    ArrayList<String> keyList = getKeys(objMap, theOne);
    for (int i = 0; i < keyList.size(); ++i) {
      int horizontal = Integer.parseInt(keyList.get(i).substring(1));
      int vertical = keyList.get(i).charAt(0);
      int column = horizontal * 2 + 2;
      int row = vertical - (int)'A' + 1;
      String line = grids.get(row);
      StringBuilder sb = new StringBuilder(line);
      sb.setCharAt(column, ' ');
      grids.set(row, sb.toString());
    }
  }

  public boolean handleMove(String target, String newPos) {
    char vertical = target.charAt(0);
    char horizontal = target.charAt(1);
    if (vertical > 96) {
      vertical -= 32;
    }
    String up = Character.toString(vertical) + Character.toString(horizontal);

    Stash theOne = objMap.get(up);
    Stash neo = new Stash(newPos, theOne.color, theOne.num_grids);
    neo.hitted = theOne.hitted;
    if (write(neo) != "good") {
      return false;
    }
    clean(theOne);
    rmFromMap(theOne);
    return true;
  }

  public boolean locate(String inp) {
    char vertical = inp.charAt(0);
    char horizontal = inp.charAt(1);
    if (vertical > 96) {
      vertical -= 32;
    }
    String up = Character.toString(vertical) + Character.toString(horizontal);
    if (objMap.get(up) == null) {
      return false;
    }
    return true;
  }

  public Boolean checkMove(String inp) {
    if (!check(inp)) {
      return false;
    }
    if (!(locate(inp))) {
      return false;
    }
    return true;
  }

  public void addToHitted(String s) {
    char vertical = s.charAt(0);
    char horizontal = s.charAt(1);
    if (vertical > 96) {
      vertical -= 32;
    }
    String up = Character.toString(vertical) + Character.toString(horizontal);
    Stash stack = objMap.get(up);
    // System.out.println("DEBUG: addToHitted: " + up + "and integer is: " + stack.keepTrack.get(up));
    //stack.keepTrack.entrySet().forEach(entry->{
    // System.out.println(up + " " + stack.keepTrack.get(up));
    //});
    Integer id = stack.keepTrack.get(up);
    stack.hitted.add(id);
  }

  public String handleDig(String guess) {
    String msg = new String();
    guessRec = guess;
    int row;
    int column;
    if (!check(guessRec)) {
      msg = "invalid";
      return msg;
    }
    column = Integer.parseInt(guessRec.substring(1, 2)) * 2 + 2;
    row = guessRec.charAt(0) - (int)'A' + 1;
    if (guessRec.charAt(0) > 96) {
      row -= 32;
    }
    String line = grids.get(row);
    char target = line.charAt(column);
    if (target == ' ') {
      missCoor.add(guessRec);
      msg = "You missed!\n";
      return msg;
    }
    else if (target == '*') {
      msg = "You've been here before!\n";
      return msg;
    }
    else {
      addToHitted(guessRec);
      hitCoor.add(guessRec);
      update(row, column);
      unhitted--;
      msg = "You found a stack!\n";
      return msg;
    }
  }

  //for Sonar Scan
  public char Count(String s) {
    Stash result = objMap.get(s);
    if (result == null) {
      return 'N';
    }
    return result.color;
  }

  public void handleScan(String inp) {
    char vertical = inp.charAt(0);
    if (vertical > 96) {
      vertical -= 32;
    }
    char horizontal = inp.charAt(1);
    char res;
    int cntG = 0;
    int cntP = 0;
    int cntR = 0;
    int cntB = 0;
    //first line
    vertical -= 3;
    res = Count(Character.toString(vertical) + Character.toString(horizontal));
    if (res != 'N') {
      if (res == 'G') {
        ++cntG;
      }
      else if (res == 'P') {
        ++cntP;
      }
      else if (res == 'R') {
        ++cntR;
      }
      else {
        ++cntB;
      }
    }
    //second lines
    ++vertical;
    --horizontal;
    for (int i = 0; i < 3; ++i) {
      res = Count(Character.toString(vertical) + Character.toString(horizontal));
      if (res != 'N') {
        if (res == 'G') {
          ++cntG;
        }
        else if (res == 'P') {
          ++cntP;
        }
        else if (res == 'R') {
          ++cntR;
        }
        else {
          ++cntB;
        }
      }
      ++horizontal;
    }
    //third line
    horizontal -= 4;
    ++vertical;
    for (int i = 0; i < 5; ++i) {
      res = Count(Character.toString(vertical) + Character.toString(horizontal));
      if (res != 'N') {
        if (res == 'G') {
          ++cntG;
        }
        else if (res == 'P') {
          ++cntP;
        }
        else if (res == 'R') {
          ++cntR;
        }
        else {
          ++cntB;
        }
      }
      ++horizontal;
    }
    //fourth line
    horizontal -= 6;
    ++vertical;
    for (int i = 0; i < 7; ++i) {
      res = Count(Character.toString(vertical) + Character.toString(horizontal));
      if (res != 'N') {
        if (res == 'G') {
          ++cntG;
        }
        else if (res == 'P') {
          ++cntP;
        }
        else if (res == 'R') {
          ++cntR;
        }
        else {
          ++cntB;
        }
      }
      ++horizontal;
    }
    //fifth line
    horizontal -= 6;
    ++vertical;
    for (int i = 0; i < 5; ++i) {
      res = Count(Character.toString(vertical) + Character.toString(horizontal));
      if (res != 'N') {
        if (res == 'G') {
          ++cntG;
        }
        else if (res == 'P') {
          ++cntP;
        }
        else if (res == 'R') {
          ++cntR;
        }
        else {
          ++cntB;
        }
      }
      ++horizontal;
    }
    //sixth line
    horizontal -= 4;
    ++vertical;
    for (int i = 0; i < 3; ++i) {
      res = Count(Character.toString(vertical) + Character.toString(horizontal));
      if (res != 'N') {
        if (res == 'G') {
          ++cntG;
        }
        else if (res == 'P') {
          ++cntP;
        }
        else if (res == 'R') {
          ++cntR;
        }
        else {
          ++cntB;
        }
      }
      ++horizontal;
    }
    //seventh line
    horizontal -= 2;
    ++vertical;
    res = Count(Character.toString(vertical) + Character.toString(horizontal));
    if (res != 'N') {
      if (res == 'G') {
        ++cntG;
      }
      else if (res == 'P') {
        ++cntP;
      }
      else if (res == 'R') {
        ++cntR;
      }
      else {
        ++cntB;
      }
    }
    System.out.println("Green stacks occupy " + cntG + " squares\n");
    System.out.println("Purple stacks occupy " + cntP + " squares\n");
    System.out.println("Red stacks occupy " + cntR + " squares\n");
    System.out.println("Blue stacks occupy " + cntB + " squares\n");
  }
}
