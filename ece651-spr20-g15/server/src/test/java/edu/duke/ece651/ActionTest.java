package edu.duke.ece651;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class ActionTest {
  @Test
  public void getUnitsTest() {
    Data data = new Data();
    String cmd =
        "Blue^Food$50^-Elantris+3+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Mordor+0+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Elantris+3)-(Narnia+1)*Green^Food$50^-Hogwarts+2+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Oz+1+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Hogwarts+2)-(Mordor+3)";
    Action.getUnits(cmd, data);
    assertEquals(data.unitMap.get("Elantris").get("Private"), 1);
    assertEquals(data.unitMap.get("Elantris").get("Corporal"), 2);
    assertEquals(data.unitMap.get("Elantris").get("Sergent"), 3);
    assertEquals(data.unitMap.get("Elantris").get("Lieutenant"), 4);
    assertEquals(data.unitMap.get("Elantris").get("Captain"), 5);
    assertEquals(data.unitMap.get("Elantris").get("Major"), 6);
    assertEquals(data.unitMap.get("Elantris").get("Colonel"), 7);

    assertEquals(data.unitMap.get("Oz").get("Private"), 1);
    assertEquals(data.unitMap.get("Oz").get("Corporal"), 2);
    assertEquals(data.unitMap.get("Oz").get("Sergent"), 3);
    assertEquals(data.unitMap.get("Oz").get("Lieutenant"), 4);
    assertEquals(data.unitMap.get("Oz").get("Captain"), 5);
    assertEquals(data.unitMap.get("Oz").get("Major"), 6);
    assertEquals(data.unitMap.get("Oz").get("Colonel"), 7);
  }

  @Test
  public void getOwnerTest() {
    Data data = new Data();
    String cmd =
        "Blue^Food$50^-Elantris+3+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Mordor+0+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Elantris+3)-(Narnia+1)*Green^Food$50^-Hogwarts+2+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Oz+1+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Hogwarts+2)-(Mordor+3)";
    Action.getOwner(cmd, data);
    List<String> Blue = new ArrayList<String>();
    Blue.add("Elantris");
    Blue.add("Mordor");
    List<String> Green = new ArrayList<String>();
    Green.add("Hogwarts");
    Green.add("Oz");
    assertEquals(data.ownMap.get("Blue"), Blue);
    assertEquals(data.ownMap.get("Green"), Green);
  }

  @Test
  public void getActionTest() {
    Data data = new Data();
    String cmd =
        "Blue^Food$50^-Elantris+3+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Mordor+0+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Elantris+3)-(Narnia+1)*Green^Food$50^-Hogwarts+2+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Oz+1+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Hogwarts+2)-(Mordor+3)";
    Action.getAction(cmd, data);
    List<String> Blue = new ArrayList<String>();
    Blue.add("Attack~Private~1~Elantris~Scadrial");
    Blue.add("Attack~Major~2~Elantris~Scadrial");
    Blue.add("Upgrade~Elantris~Private~1~Major");
    Blue.add("Done~");
    List<String> Green = new ArrayList<String>();
    Green.add("Attack~Private~1~Elantris~Scadrial");
    Green.add("Attack~Major~2~Elantris~Scadrial");
    Green.add("Upgrade~Elantris~Private~1~Major");
    Green.add("Done~");
    assertEquals(data.actionMap.get("Blue"), Blue);
    assertEquals(data.actionMap.get("Green"), Green);
  }

  @Test
  public void getResourceTest() {
    Data data = new Data();
    String cmd =
        "Blue^Food$50^-Elantris+3+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Mordor+0+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Elantris+3)-(Narnia+1)*Green^Food$50^-Hogwarts+2+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Oz+1+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Hogwarts+2)-(Mordor+3)";
    Action.getResource(cmd, data);
    assertEquals(data.resourceMap.get("Blue"), "50");
    assertEquals(data.resourceMap.get("Green"), "50");
  }

  @Test
  public void getCloakTest() {
    Data data = new Data();
    String cmd =
        "Blue^Food$50^-Elantris+3+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Mordor+0+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Elantris+3)-(Narnia+1)*Green^Food$50^-Hogwarts+2+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Oz+1+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Hogwarts+2)-(Mordor+3)";
    Action.getCloak(cmd, data);
    assertEquals(data.cloakMap.get("Elantris"), 3);
    assertEquals(data.cloakMap.get("Mordor"), 0);
    assertEquals(data.cloakMap.get("Hogwarts"), 2);
    assertEquals(data.cloakMap.get("Oz"), 1);
  }

  @Test
  public void getSpyTest() {
    Data data = new Data();
    String cmd =
        "Blue^Food$50^-Elantris+3+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Mordor+0+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Elantris+3)-(Narnia+1)*Green^Food$50^-Hogwarts+2+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7)-Oz+1+(Private_1+Corporal_2+Sergent_3+Lieutenant_4+Captain_5+Major_6+Colonel_7){Attack~Private~1~Elantris~Scadrial@Attack~Major~2~Elantris~Scadrial@Upgrade~Elantris~Private~1~Major@Done~}%(Hogwarts+2)-(Mordor+3)";
    Action.getSpy(cmd, data);
    Map<String, Integer> ttSpyMapBlue = new HashMap<String, Integer>();
    ttSpyMapBlue.put("Elantris", 3);
    ttSpyMapBlue.put("Narnia", 1);
    Map<String, Integer> ttSpyMapGreen = new HashMap<String, Integer>();
    ttSpyMapGreen.put("Hogwarts", 2);
    ttSpyMapGreen.put("Mordor", 3);
    assertEquals(data.spyMap.get("Blue").get("Elantris"), 3);
    assertEquals(data.spyMap.get("Blue").get("Narnia"), 1);
    assertEquals(data.spyMap.get("Green").get("Hogwarts"), 2);
    assertEquals(data.spyMap.get("Green").get("Mordor"), 3);
  }
  @Test
  public void serializeSpyTest() {
    Data data = new Data();
    data.cloakMap.put("Elantris", 0);
    data.cloakMap.put("Mordor", 2);
    data.cloakMap.put("Oz", 3);
    Map<String, Integer> ttSpyMapGreen = new HashMap<String, Integer>();
    ttSpyMapGreen.put("Elantris", 2);
    ttSpyMapGreen.put("Mordor", 0);
    data.spyMap.put("Green", ttSpyMapGreen);
    String res = Action.serializeSpy(data);
    System.out.println("***********************************");
    System.out.println(res);
  }
}
