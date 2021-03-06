package edu.duke.ece651.sallystash;

import java.util.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class SallyStash {
  public static void main(String[] args) throws IOException {
    SallyStash stash = new SallyStash();
    stash.chooseRole();
  }

  //  public SallyStash(BufferedReader br) throws IOException {}

  public String readFromStdin() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String s = reader.readLine();
    return s;
  }

  public String checkValidity(String s, int num_grids, Board board) {
    int vertical = s.charAt(0);
    int horizontal;
    String message = "good";
    //check total number of input characters
    if (s.length() != 3) {
      message = "Please input 3 characters\n";
      return message;
    }
    char direction = s.charAt(2);
    //check if the first character is a letter
    if (vertical < 65 || (vertical > 90 && vertical < 97) || vertical > 122) {
      message = "The first character need to be a letter \n";
      return message;
    }
    //check if the second character is a character representing an integer
    try {
      horizontal = Integer.parseInt(s.substring(1, 2));
    }
    catch (NumberFormatException e) {
      message = "Second character need to be an integer \n";
      return message;
    }
    //check if the third character is valid
    if (direction != 'H' && direction != 'h' && direction != 'V' && direction != 'v' &&
        direction != 'U' && direction != 'u' && direction != 'R' && direction != 'r' &&
        direction != 'D' && direction != 'd' && direction != 'L' && direction != 'l') {
      message = "Third character not valid \n";
      return message;
    }
    // Green and purple stacks can only be H/h or V/v
    if ((num_grids < 4) &&
        (direction != 'H' && direction != 'h' && direction != 'V' && direction != 'v')) {
      message = "Green and purple stacks can only be H/h or V/v\n";
      return message;
    }
    //Red and blue stacks can only be U/u, R/r, D/d, L/l
    if ((num_grids > 3) &&
        (direction != 'U' && direction != 'u' && direction != 'R' && direction != 'r' &&
         direction != 'D' && direction != 'd' && direction != 'L' && direction != 'l')) {
      message = "Red and blue stacks can only be U/u, R/r, D/d, L/l\n";
      return message;
    }
    //Stack off the grids
    if ((vertical > 84 && vertical < 91) || (vertical > 116 && vertical < 123)) {
      message = "Stack off the grid \n";
      return message;
    }
    return message;
  }

  public void chooseRole() throws IOException {
    String roleA = new String();
    String roleB = new String();
    while (true) {
      System.out.println("A is a ? H for human, C for computer\n");
      roleA = readFromStdin();
      System.out.println("B is a ? H for human, C for computer\n");
      roleB = readFromStdin();
      if (roleA.equals("H") && roleB.equals("H")) {
        placingStacks2H();
        break;
      }
      else if (roleA.equals("H") && roleB.equals("C")) {
        placingStacksAHBC();
        break;
      }
      else if (roleA.equals("C") && roleB.equals("H")) {
        placingStacksACBH();
        break;
      }
      else if (roleA.equals("C") && roleB.equals("C")) {
        placingStacks2C();
        break;
      }
      else {
        System.out.println("Wrong input, Please enter C or H to indicate role for each player\n");
        continue;
      }
    }
  }

  public String randPlacingGP() {
    ArrayList<String> set = new ArrayList<String>();
    set.add("H");
    set.add("h");
    set.add("V");
    set.add("v");
    int i = ThreadLocalRandom.current().nextInt(0, 4);
    String orien = set.get(i);
    int randVer = ThreadLocalRandom.current().nextInt(65, 85);
    int randHor = ThreadLocalRandom.current().nextInt(48, 58);
    // System.out.println("Rand pla GP " + Character.toString((char)randVer) +
    //                    Character.toString((char)randHor));
    return Character.toString((char)randVer) + Character.toString((char)randHor) + orien;
  }

  public String randPlacing() {
    ArrayList<String> set = new ArrayList<String>();
    set.add("U");
    set.add("u");
    set.add("D");
    set.add("d");
    set.add("R");
    set.add("r");
    set.add("L");
    set.add("l");
    int i = ThreadLocalRandom.current().nextInt(0, 8);
    String orien = set.get(i);
    int randVer = ThreadLocalRandom.current().nextInt(65, 85);
    int randHor = ThreadLocalRandom.current().nextInt(48, 58);
    // System.out.println("Rand Pla " + Character.toString((char)randVer) +
    //                    Character.toString((char)randHor));
    return Character.toString((char)randVer) + Character.toString((char)randHor) + orien;
  }

  public void placingStacks2C() throws IOException {
    String inp = new String();
    String msg = new String();
    String mess = new String();
    Board BoardA = new Board();
    Board BoardB = new Board();
    BoardA.initiate();
    BoardB.initiate();
    //computer placing stacks
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG1A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG2A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP1A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP2A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP3A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP3A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR1A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR2A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR3A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR3A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB1A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB2A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB3A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB3A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }

    //computer placing stacks
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG1B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG2B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP1B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP2B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP3B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP3B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR1B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR2B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR3B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR3B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB1B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB2B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB3B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB3B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }

    playBegin2C(BoardA, BoardB);
  }

  public void placingStacksAHBC() throws IOException {
    String inp = new String();
    String msg = new String();
    String mess = new String();
    Board BoardA = new Board();
    Board BoardB = new Board();
    BoardA.initiate();
    BoardB.initiate();

    //human placing stacks
    BoardA.display();
    System.out.println(
        "Player A, you are going place Sally’s stash on the board. Make sure Player B isn’t \nlooking! For each stack, type the coordinate of the upper left side of the stash,\nfollowed by either H (for horizontal) or V (for vertical). For example, M4H would \nplace a stack horizontally starting at M4 and going to the right. You have \n");
    System.out.println(
        "2 Green stacks that are 1x2 \n3 Purple stacks that are 1x3 \n3 Red stacks that are 1x4 \n2 Blue stacks that are 1x6 \n");

    System.out.println("Player A, where do you want to place the first Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG1A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG2A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the first Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP1A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP2A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the third Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the third Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP3A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP3A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the third Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the first Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR1A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR2A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the third Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the third Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR3A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR3A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the third Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the first Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB1A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB2A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the third Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the third Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB3A = new Stash(inp, 'B', 6);
      if ((mess = BoardA.write(stashB3A)) != "good") {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the third Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    //computer placing stacks
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG1B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG2B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP1B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP2B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP3B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP3B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR1B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR2B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR3B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR3B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB1B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB1B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB2B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB2B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB3B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB3B)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }

    playBeginAHBC(BoardA, BoardB);
  }

  public void placingStacksACBH() throws IOException {
    String inp = new String();
    String msg = new String();
    String mess = new String();
    Board BoardA = new Board();
    Board BoardB = new Board();
    BoardA.initiate();
    BoardB.initiate();
    //computer placing stacks
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG1A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashG2A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP1A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP2A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacingGP();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        inp = randPlacingGP();
      }
      Stash stashP3A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP3A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR1A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR2A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashR3A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR3A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB1A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB1A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB2A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB2A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }
    while (true) {
      inp = randPlacing();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        inp = randPlacing();
      }
      Stash stashB3A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB3A)).equals("good")) {
        continue;
      }
      else {
        break;
      }
    }

    //human placing stacks
    BoardB.display();
    System.out.println(
        "Player B, you are going place Sally’s stash on the board. Make sure Player B isn’t \nlooking! For each stack, type the coordinate of the upper left side of the stash,\nfollowed by either H (for horizontal) or V (for vertical). For example, M4H would \nplace a stack horizontally starting at M4 and going to the right. You have \n");
    System.out.println(
        "2 Green stacks that are 1x2 \n3 Purple stacks that are 1x3 \n3 Red stacks that are 1x4 \n2 Blue stacks that are 1x6 \n");

    System.out.println("Player B, where do you want to place the first Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG1B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG2B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the first Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP1B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP2B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the third Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the third Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP3B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP3B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the third Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the first Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR1B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR2B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the third Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the third Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR3B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR3B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the third Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the first Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB1B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB2B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the third Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the third Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB3B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB3B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the third Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    playBeginACBH(BoardA, BoardB);
  }

  public void placingStacks2H() throws IOException {
    String inp = new String();
    String msg = new String();
    String mess = new String();
    Board BoardA = new Board();
    Board BoardB = new Board();
    BoardA.initiate();
    BoardB.initiate();

    BoardA.display();
    System.out.println(
        "Player A, you are going place Sally’s stash on the board. Make sure Player B isn’t \nlooking! For each stack, type the coordinate of the upper left side of the stash,\nfollowed by either H (for horizontal) or V (for vertical). For example, M4H would \nplace a stack horizontally starting at M4 and going to the right. You have \n");
    System.out.println(
        "2 Green stacks that are 1x2 \n3 Purple stacks that are 1x3 \n3 Red stacks that are 1x4 \n2 Blue stacks that are 1x6 \n");

    System.out.println("Player A, where do you want to place the first Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG1A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG2A = new Stash(inp, 'G', 2);
      if (!(mess = BoardA.write(stashG2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the first Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP1A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP2A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the third Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the third Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP3A = new Stash(inp, 'P', 3);
      if (!(mess = BoardA.write(stashP3A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the third Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the first Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR1A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR2A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the third Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the third Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR3A = new Stash(inp, 'R', 4);
      if (!(mess = BoardA.write(stashR3A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the third Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the first Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the first Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB1A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB1A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the first Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the second Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the second Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB2A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB2A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the second Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    System.out.println("Player A, where do you want to place the third Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardA)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player A, where do you want to place the third Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB3A = new Stash(inp, 'B', 6);
      if (!(mess = BoardA.write(stashB3A)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player A, where do you want to place the third Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardA.display();

    BoardB.display();
    System.out.println(
        "Player B, you are going place Sally’s stash on the board. Make sure Player B isn’t \nlooking! For each stack, type the coordinate of the upper left side of the stash,\nfollowed by either H (for horizontal) or V (for vertical). For example, M4H would \nplace a stack horizontally starting at M4 and going to the right. You have \n");
    System.out.println(
        "2 Green stacks that are 1x2 \n3 Purple stacks that are 1x3 \n3 Red stacks that are 1x4 \n2 Blue stacks that are 1x6 \n");

    System.out.println("Player B, where do you want to place the first Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG1B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Green stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 2, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Green stack?\n");
        inp = readFromStdin();
      }
      Stash stashG2B = new Stash(inp, 'G', 2);
      if (!(mess = BoardB.write(stashG2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Green stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the first Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP1B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP2B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the third Purple stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 3, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the third Purple stack?\n");
        inp = readFromStdin();
      }
      Stash stashP3B = new Stash(inp, 'P', 3);
      if (!(mess = BoardB.write(stashP3B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the third Purple stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the first Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR1B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR2B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the third Red stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 4, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the third Red stack?\n");
        inp = readFromStdin();
      }
      Stash stashR3B = new Stash(inp, 'R', 4);
      if (!(mess = BoardB.write(stashR3B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the third Red stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the first Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the first Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB1B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB1B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the first Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the second Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the second Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB2B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB2B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the second Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    System.out.println("Player B, where do you want to place the third Blue stack?\n");
    while (true) {
      inp = readFromStdin();
      while (!(msg = checkValidity(inp, 6, BoardB)).equals("good")) {
        System.out.println(msg);
        System.out.println("Player B, where do you want to place the third Blue stack?\n");
        inp = readFromStdin();
      }
      Stash stashB3B = new Stash(inp, 'B', 6);
      if (!(mess = BoardB.write(stashB3B)).equals("good")) {
        System.out.println(mess);
        System.out.println("Player B, where do you want to place the third Blue stack?\n");
        continue;
      }
      else {
        break;
      }
    }
    BoardB.display();

    playBegin2H(BoardA, BoardB);
  }

  public boolean checkInp(String inp) {
    ArrayList<String> list = new ArrayList<String>();
    list.add("D");
    list.add("d");
    list.add("M");
    list.add("m");
    list.add("S");
    list.add("s");
    if (!list.contains(inp)) {
      return false;
    }
    else {
      return true;
    }
  }

  public String randDigging() {
    int randVer = ThreadLocalRandom.current().nextInt(65, 85);
    int randHor = ThreadLocalRandom.current().nextInt(48, 58);
    // System.out.println("Rand dig " + Character.toString((char)randVer) +
    //                    Character.toString((char)randHor));
    return Character.toString((char)randVer) + Character.toString((char)randHor);
  }

  public void playBeginAHBC(Board boardA, Board boardB) throws IOException {
    String inpA = new String();
    String inpB = new String();
    String msg = new String();
    String target = new String();
    while (boardA.unhitted != 0 && boardB.unhitted != 0) {
      //A's turn
      System.out.println("Player A's turn:\n");
      System.out.println("     Your tree\n");
      boardA.display();
      System.out.println("     Player B's tree:\n");
      boardB.dispToOppo();
      while (true) {
        System.out.println("Possible actions for Player A:\n\n");
        System.out.println("D Dig in a square\n");
        System.out.println("M Move a stack to another square(" + boardA.tokenMove +
                           " remaining)\n");
        System.out.println("S Sonar scan (" + boardA.tokenScan + " remaining)\n\n");
        System.out.println("Player A, what would you like to do?\n");
        inpA = readFromStdin();
        if (!checkInp(inpA)) {
          continue;
        }
        if (inpA.equals("D") || inpA.equals("d")) {
          System.out.println("Please enter a coordinate:\n");
          inpA = readFromStdin();
          if ((msg = boardB.handleDig(inpA)).equals("invalid")) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          System.out.println(msg);
          break;
        }
        else if (inpA.equals("M") || inpA.equals("m")) {
          if (boardA.tokenMove == 0) {
            System.out.println("Can't Move any more\n");
            continue;
          }
          System.out.println("Which stack do you wanna move? Enter a coordinate:\n");
          inpA = readFromStdin();
          if (!boardA.checkMove(inpA)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          target = inpA;
          System.out.println("Please enter your new placement\n");
          inpA = readFromStdin();
          if (!boardA.checkNew(inpA)) {
            System.out.println("Invalid\n");
            continue;
          }
          if (!boardA.handleMove(target, inpA)) {
            System.out.println("Error happens when moving\n");
            continue;
          }
          --boardA.tokenMove;
          break;
        }
        else {
          if (boardA.tokenScan == 0) {
            System.out.println("Can't scan any more\n");
            continue;
          }
          System.out.println("Please enter center coordinate of the scan:\n");
          inpA = readFromStdin();
          if (!boardB.check(inpA)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          boardB.handleScan(inpA);
          --boardA.tokenScan;
          break;
        }
      }
      if (boardB.unhitted == 0) {
        break;
      }
      //B's turn
      while (true) {
        inpB = randDigging();
        if ((msg = boardA.handleDig(inpB)).equals("invalid")) {
          continue;
        }
        if (msg.equals("You found a stack!\n")) {
          String color = Character.toString(boardA.objMap.get(inpB).color);
          System.out.println("Player B found your " + color + " stack at " + inpB + "!\n");
        }
        break;
      }
      if (boardA.unhitted == 0) {
        break;
      }
    }
    if (boardA.unhitted == 0) {
      System.out.println("Player B wins!\n");
    }
    else {
      System.out.println("Player A wins!\n");
    }
  }

  public void playBeginACBH(Board boardA, Board boardB) throws IOException {
    String inpA = new String();
    String inpB = new String();
    String msg = new String();
    String target = new String();
    //A's turn
    while (boardA.unhitted != 0 && boardB.unhitted != 0) {
      while (true) {
        inpA = randDigging();
        if ((msg = boardB.handleDig(inpA)).equals("invalid")) {
          continue;
        }
        if (msg.equals("You found a stack!\n")) {
          String color = Character.toString(boardB.objMap.get(inpA).color);
          System.out.println("Player A found your " + color + " stack at " + inpA + "!\n");
        }
        break;
      }
      if (boardB.unhitted == 0) {
        break;
      }
      //B's turn
      System.out.println("Player B's turn:\n");
      System.out.println("     Your tree\n");
      boardB.display();
      System.out.println("     Player A's tree:\n");
      boardA.dispToOppo();
      while (true) {
        System.out.println("Possible actions for Player B:\n\n");
        System.out.println("D Dig in a square\n");
        System.out.println("M Move a stack to another square(" + boardB.tokenMove +
                           " remaining)\n");
        System.out.println("S Sonar scan (" + boardB.tokenScan + " remaining)\n\n");
        System.out.println("Player B, what would you like to do?\n");
        inpB = readFromStdin();
        if (!checkInp(inpB)) {
          continue;
        }
        if (inpB.equals("D") || inpB.equals("d")) {
          System.out.println("Please enter a coordinate:\n");
          inpB = readFromStdin();
          if ((msg = boardA.handleDig(inpB)).equals("invalid")) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          System.out.println(msg);
          break;
        }
        else if (inpB.equals("M") || inpB.equals("m")) {
          if (boardB.tokenMove == 0) {
            System.out.println("Can't Move any more\n");
            continue;
          }
          System.out.println("Which stack do you wanna move? Enter a coordinate:\n");
          inpB = readFromStdin();
          if (!boardB.checkMove(inpB)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          target = inpB;
          System.out.println("Please enter your new placement\n");
          inpB = readFromStdin();
          if (!boardB.checkNew(inpB)) {
            System.out.println("Invalid\n");
            continue;
          }
          if (!boardB.handleMove(target, inpB)) {
            System.out.println("Error happens when moving\n");
            continue;
          }
          --boardB.tokenMove;
          break;
        }
        else {
          if (boardB.tokenScan == 0) {
            System.out.println("Can't scan any more\n");
            continue;
          }
          System.out.println("Please enter center coordinate of the scan:\n");
          inpB = readFromStdin();
          if (!boardA.check(inpB)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          boardA.handleScan(inpB);
          --boardB.tokenScan;
          break;
        }
      }
      if (boardA.unhitted == 0) {
        break;
      }
    }
    if (boardA.unhitted == 0) {
      System.out.println("Player B wins!\n");
    }
    else {
      System.out.println("Player A wins!\n");
    }
  }

  public void playBegin2C(Board boardA, Board boardB) throws IOException {
    boardA.display();
    boardB.display();
    String inpA = new String();
    String inpB = new String();
    String msg = new String();
    while (boardA.unhitted != 0 && boardB.unhitted != 0) {
      //A's turn
      while (true) {
        inpA = randDigging();
        //        System.out.println("A dig " + inpA);
        if ((msg = boardB.handleDig(inpA)).equals("invalid")) {
          continue;
        }
        if (msg.equals("You found a stack!\n")) {
          String color = Character.toString(boardB.objMap.get(inpA).color);
          System.out.println("Player A found your " + color + " stack at " + inpA + "!\n");
        }
        break;
      }
      if (boardB.unhitted == 0) {
        break;
      }
      //B's turn'
      while (true) {
        inpB = randDigging();
        //        System.out.println("B dig " + inpB);
        if ((msg = boardA.handleDig(inpB)).equals("invalid")) {
          continue;
        }
        if (msg.equals("You found a stack!\n")) {
          String color = Character.toString(boardA.objMap.get(inpB).color);
          System.out.println("Player B found your " + color + " stack at " + inpB + "!\n");
        }
        break;
      }
      if (boardA.unhitted == 0) {
        break;
      }
    }
    if (boardA.unhitted == 0) {
      System.out.println("Player B wins!\n");
    }
    else {
      System.out.println("Player A wins!\n");
    }
  }

  public void playBegin2H(Board boardA, Board boardB) throws IOException {
    String inpA = new String();
    String inpB = new String();
    String msg = new String();
    String target = new String();
    while (boardA.unhitted != 0 && boardB.unhitted != 0) {
      //A's turn
      System.out.println("Player A's turn:\n");
      System.out.println("     Your tree\n");
      boardA.display();
      System.out.println("     Player B's tree:\n");
      boardB.dispToOppo();
      while (true) {
        System.out.println("Possible actions for Player A:\n\n");
        System.out.println("D Dig in a square\n");
        System.out.println("M Move a stack to another square(" + boardA.tokenMove +
                           " remaining)\n");
        System.out.println("S Sonar scan (" + boardA.tokenScan + " remaining)\n\n");
        System.out.println("Player A, what would you like to do?\n");
        inpA = readFromStdin();
        if (!checkInp(inpA)) {
          continue;
        }
        if (inpA.equals("D") || inpA.equals("d")) {
          System.out.println("Please enter a coordinate:\n");
          inpA = readFromStdin();
          if ((msg = boardB.handleDig(inpA)).equals("invalid")) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          System.out.println(msg);
          break;
        }
        else if (inpA.equals("M") || inpA.equals("m")) {
          if (boardA.tokenMove == 0) {
            System.out.println("Can't Move any more\n");
            continue;
          }
          System.out.println("Which stack do you wanna move? Enter a coordinate:\n");
          inpA = readFromStdin();
          if (!boardA.checkMove(inpA)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          target = inpA;
          System.out.println("Please enter your new placement\n");
          inpA = readFromStdin();
          if (!boardA.checkNew(inpA)) {
            System.out.println("Invalid\n");
            continue;
          }
          if (!boardA.handleMove(target, inpA)) {
            System.out.println("Error happens when moving\n");
            continue;
          }
          --boardA.tokenMove;
          break;
        }
        else {
          if (boardA.tokenScan == 0) {
            System.out.println("Can't scan any more\n");
            continue;
          }
          System.out.println("Please enter center coordinate of the scan:\n");
          inpA = readFromStdin();
          if (!boardB.check(inpA)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          boardB.handleScan(inpA);
          --boardA.tokenScan;
          break;
        }
      }
      if (boardB.unhitted == 0) {
        break;
      }
      //B's turn
      System.out.println("Player B's turn:\n");
      System.out.println("     Your tree\n");
      boardB.display();
      System.out.println("     Player A's tree:\n");
      boardA.dispToOppo();
      while (true) {
        System.out.println("Possible actions for Player B:\n\n");
        System.out.println("D Dig in a square\n");
        System.out.println("M Move a stack to another square(" + boardB.tokenMove +
                           " remaining)\n");
        System.out.println("S Sonar scan (" + boardB.tokenScan + " remaining)\n\n");
        System.out.println("Player B, what would you like to do?\n");
        inpB = readFromStdin();
        if (!checkInp(inpB)) {
          continue;
        }
        if (inpB.equals("D") || inpB.equals("d")) {
          System.out.println("Please enter a coordinate:\n");
          inpB = readFromStdin();
          if ((msg = boardA.handleDig(inpB)).equals("invalid")) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          System.out.println(msg);
          break;
        }
        else if (inpB.equals("M") || inpB.equals("m")) {
          if (boardB.tokenMove == 0) {
            System.out.println("Can't Move any more\n");
            continue;
          }
          System.out.println("Which stack do you wanna move? Enter a coordinate:\n");
          inpB = readFromStdin();
          if (!boardB.checkMove(inpB)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          target = inpB;
          System.out.println("Please enter your new placement\n");
          inpB = readFromStdin();
          if (!boardB.checkNew(inpB)) {
            System.out.println("Invalid\n");
            continue;
          }
          if (!boardB.handleMove(target, inpB)) {
            System.out.println("Error happens when moving\n");
            continue;
          }
          --boardB.tokenMove;
          break;
        }
        else {
          if (boardB.tokenScan == 0) {
            System.out.println("Can't scan any more\n");
            continue;
          }
          System.out.println("Please enter center coordinate of the scan:\n");
          inpB = readFromStdin();
          if (!boardA.check(inpB)) {
            System.out.println("Invalid coordinate\n");
            continue;
          }
          boardA.handleScan(inpB);
          --boardB.tokenScan;
          break;
        }
      }
      if (boardA.unhitted == 0) {
        break;
      }
    }
    if (boardA.unhitted == 0) {
      System.out.println("Player B wins!\n");
    }
    else {
      System.out.println("Player A wins!\n");
    }
  }
}
