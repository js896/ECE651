package edu.duke.ece651.classbuilder;

import edu.duke.ece651.classbuilder.*;

import java.io.*;

import org.json.*;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ClassBuilderTest {
  @Test
  public void test_classbuilder() throws FileNotFoundException, IOException {
    File fl = new File("./ece651-hwk1-tester/inputs/empty.json");
    InputStream is = new FileInputStream(fl);
    ClassBuilder cb = new ClassBuilder(is);
    cb.createAllClasses("./ece651-hwk1-tester/src/main/java/hwk1/testing/empty/");
    cb.handleString(cb.jsonString);
    System.out.println(cb.info);

    // Faculty faculty = new Faculty();
    // Course course = new Course();
    // course.setInstructor(faculty);
    // System.out.println(course.getInstructor());
    // course.setNumStudents(70);
    // System.out.println(course.getNumStudents());
    // faculty.setName("drew");
    // faculty.setCourse(course);
    // Office office = new Office();
    // office.setOccupant(faculty);
    // office.setBuilding("hudson hall");
    // office.setNumber(1);
    // Grade grade = new Grade();
    // grade.setCourse(course);
    // grade.setStudent("jiawei");
    // grade.setGrade(80);

    // // System.out.println(faculty.toJSON().toString());

    //    assert (Deserializer.readCourse(course.toJSON()).equals(course));
    // // System.out.println(office.toJSON().toString());
    // // System.out.println(grade.toJSON().toString());
    // Course c = Deserializer.readCourse(course.toJSON());
    // System.out.println(c.getInstructor());
    // System.out.println(c.getNumStudents());
    // assert (true == false);
  }
}
