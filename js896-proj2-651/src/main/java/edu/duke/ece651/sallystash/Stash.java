package edu.duke.ece651.sallystash;

import java.util.*;

public class Stash {
  public int len;
  public int vertical;
  public int horizontal;
  public int num_grids;
  public char direction;
  public char color;
  //use this map to track hitted grids during rotation
  public HashMap<String, Integer> keepTrack = new HashMap<String, Integer>();
  public ArrayList<Integer> hitted = new ArrayList<Integer>();

  public Stash(String s, char colour, int num) {
    len = s.length();
    vertical = s.charAt(0);
    if (vertical > 96) {
      vertical -= 32;
    }
    horizontal = Integer.parseInt(s.substring(1, 2));
    num_grids = num;
    direction = s.charAt(2);
    color = colour;
  }
}
