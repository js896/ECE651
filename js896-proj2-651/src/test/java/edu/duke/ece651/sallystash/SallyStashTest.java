package edu.duke.ece651.sallystash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;

import java.io.*;

public class SallyStashTest {
  @Test
  public void test_SallyStash() throws IOException, FileNotFoundException {
    FileInputStream fs = new FileInputStream("./testfile/for2C.txt");

    BufferedReader br = new BufferedReader(new InputStreamReader(fs));
    SallyStash sally = new SallyStash(br);
    sally.chooseRole();
    // assert (true == false);
  }
}
