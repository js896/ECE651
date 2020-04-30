package edu.duke.ece651.classbuilder;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ClassBuilder {
  public String jsonString;
  private String package_share;
  public ArrayList<HashMap<String, String>> info;

  // JSONObject jsonobj = new JSONObject();
  // public static void main(String[] args) throws IOException, JSONException {
  // File fl = new File("/home/js/ece651spr20hwk1/ece651-hwk1/array.json");
  // InputStream is = new FileInputStream(fl);
  // ClassBuilder cb = new ClassBuilder(is);
  // cb.createAllClasses("/home/js/js896-json-651/");
  // //System.out.println(jsonString);
  // //System.out.println(getClassNames(jsonString));
  // //System.out.println(getSourceCode("Course"));
  // //System.out.println(handleString(jsonString));
  // }

  public ClassBuilder(String test) throws JSONException { new JSONObject(test); }

  public ClassBuilder(InputStream input) throws IOException {
    try {
      input.read();
    }
    catch (IOException ex) {
    }
    jsonString = CharStreams.toString(new InputStreamReader(input, Charsets.UTF_8));
    jsonString = "{" + jsonString;
    try {
      JSONObject jsonObject = new JSONObject(jsonString);
    }
    catch (JSONException err) {
    }
  }

  public Collection<String> getClassNames(String jsonString) throws JSONException {
    JSONObject obj = null;
    try {
      obj = new JSONObject(jsonString);
    }
    catch (JSONException e) {
      e.printStackTrace();
    }
    Collection<String> classNames = new ArrayList<String>();
    JSONArray arr = obj.getJSONArray("classes");
    for (int i = 0; i < arr.length(); ++i) {
      classNames.add(arr.getJSONObject(i).getString("name"));
    }
    return classNames;
  }

  public void handleString(String jsonString) throws JSONException {
    ArrayList<HashMap<String, String>> inf = new ArrayList<HashMap<String, String>>();
    JSONObject obj = new JSONObject(jsonString);
    String pkg = new String();
    boolean no_pkg = false;
    try {
      pkg = obj.getString("package");
    }
    catch (JSONException ex) {
      no_pkg = true;
    }
    if (!no_pkg) {
      HashMap<String, String> hs_pkg = new HashMap<String, String>();
      hs_pkg.put("package", pkg);
      inf.add(hs_pkg);
    }
    JSONArray arr = obj.getJSONArray("classes");
    for (int i = 0; i < arr.length(); ++i) {
      HashMap<String, String> hs = new HashMap<String, String>();
      hs.put("classname", arr.getJSONObject(i).getString("name"));
      JSONArray arr_fields = arr.getJSONObject(i).getJSONArray("fields");
      for (int j = 0; j < arr_fields.length(); ++j) {
        hs.put("fieldsname" + Integer.toString(j), arr_fields.getJSONObject(j).getString("name"));
        try {
          String tp = arr_fields.getJSONObject(j).getString("type");
          if (tp.equals("byte") || tp.equals("char") || tp.equals("short")) {
            hs.put("fieldstype" + Integer.toString(j), "int");
            continue;
          }

          hs.put("fieldstype" + Integer.toString(j), tp);
        }
        catch (JSONException ex) {
          hs.put("fieldstype" + Integer.toString(j),
                 "ArrayList<" + arr_fields.getJSONObject(j).getJSONObject("type").getString("e") +
                     ">");
        }
      }
      inf.add(hs);
    }
    info = inf;
    // return inf;
  }

  public String num_array(String field_name) {
    StringBuilder sb = new StringBuilder();
    String name = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
    sb.append("  public int num" + name + "() { \n");
    sb.append("    return " + field_name + ".size(); \n");
    sb.append("  } \n");
    String method = sb.toString();
    return method;
  }

  public String add_array(String field_name, String field_type) {
    StringBuilder sb = new StringBuilder();
    String name = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
    String type = field_type.substring(10, field_type.length() - 1);
    sb.append("  public void add" + name + "(" + type + " n) { \n");
    sb.append("    " + field_name + ".add(n); \n");
    sb.append("  } \n");
    String method = sb.toString();
    return method;
  }

  public String get_array(String field_name, String field_type) {
    StringBuilder sb = new StringBuilder();
    String name = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
    String type = field_type.substring(10, field_type.length() - 1);
    sb.append("  public " + type + " get" + name + "(int index) { \n");
    sb.append("    return " + field_name + ".get(index); \n");
    sb.append("  } \n");
    String method = sb.toString();
    return method;
  }

  public String set_array(String field_name, String field_type) {
    StringBuilder sb = new StringBuilder();
    String name = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
    String type = field_type.substring(10, field_type.length() - 1);
    sb.append("  public void set" + name + "(int index, " + type + " n) { \n");
    sb.append("    " + field_name + ".set(index, n); \n");
    sb.append("  } \n");
    String method = sb.toString();
    return method;
  }

  public String get(String field_name, String field_type) {
    StringBuilder sb = new StringBuilder();
    String name = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
    sb.append("  public " + field_type + " get" + name + "() { \n");
    sb.append("    return " + field_name + "; \n");
    sb.append("  } \n");
    String method = sb.toString();
    return method;
  }

  public String set(String field_name, String field_type) {
    StringBuilder sb = new StringBuilder();
    String name = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
    sb.append("  public void set" + name + "(" + field_type + " n) { \n");
    sb.append("    " + field_name + " = n; \n");
    sb.append("  } \n");
    String method = sb.toString();
    return method;
  }

  public String constructor_builder(HashMap<String, String> class_info) {
    boolean arr_flag = false;
    for (int i = 0; i < (class_info.size() - 1) / 2; ++i) {
      if (class_info.get("fieldstype" + Integer.toString(i)).contains("ArrayList")) {
        arr_flag = true;
      }
    }
    // no array fields
    if (arr_flag == false) {
      return "";
    }
    // array fields exists
    else {
      StringBuilder sb = new StringBuilder();
      String class_name = class_info.get("classname");
      sb.append("  public " + class_name + "() { \n");
      for (int j = 0; j < (class_info.size() - 1) / 2; ++j) {
        String field_name = class_info.get("fieldsname" + Integer.toString(j));
        String field_type = class_info.get("fieldstype" + Integer.toString(j));
        if (class_info.get("fieldstype" + Integer.toString(j)).contains("ArrayList")) {
          sb.append("    this." + field_name + " = new " + field_type + " (); \n");
        }
      }
      sb.append("  } \n");
      return sb.toString();
    }
  }

  public String serialization_builder(HashMap<String, String> class_info) {
    StringBuilder sb = new StringBuilder();
    String class_name = class_info.get("classname");
    ArrayList<String> types = new ArrayList<String>();
    types.add("boolean");
    types.add("byte");
    types.add("char");
    types.add("short");
    types.add("int");
    types.add("long");
    types.add("float");
    types.add("double");
    types.add("String");
    sb.append("  public JSONObject toJSON() { \n");
    sb.append("    HashMap<Object, Integer> met = new HashMap<Object, Integer>(); \n");
    sb.append("    return toJSON_helper(met); \n");
    sb.append("    } \n");
    sb.append("  public JSONObject toJSON_helper(HashMap<Object, Integer> met) { \n");
    sb.append("    JSONObject builder = new JSONObject(); \n");
    sb.append("    if (met.containsKey(this)) { \n");
    sb.append("      builder.put(\"ref\", met.get(this)); \n");
    sb.append("      return builder; \n");
    sb.append("    } \n");
    sb.append("    else {\n");
    sb.append("      met.put(this, met.size()+1); \n");
    //sb.append("    System.out.println(met.keySet()); \n");
    sb.append("      JSONArray values = new JSONArray(); \n");
    sb.append("      builder.put(\"id\", met.size()); \n");
    sb.append("      builder.put(\"type\", "
              + "\"" + class_name + "\""
              + " ); \n");
    for (int i = 0; i < (class_info.size() - 1) / 2; ++i) {
      String field_name = class_info.get("fieldsname" + Integer.toString(i));
      String field_type = class_info.get("fieldstype" + Integer.toString(i));
      sb.append("      JSONObject obj" + Integer.toString(i) + " = new JSONObject(); \n");
      if (types.contains(field_type)) {
        sb.append("      obj" + Integer.toString(i) + ".put(\"" + field_name + "\""
                  + ", "
                  + "this." + field_name + "); \n");
      }
      else {
        sb.append("      obj" + Integer.toString(i) + ".put(\"" + field_name + "\""
                  + ", " + field_name + ".toJSON_helper(met)"
                  + "); \n");
      }
      sb.append("      values.put(obj" + Integer.toString(i) + "); \n");
    }
    sb.append("      builder.put(\"values\", values); \n");
    sb.append("      return builder; \n");
    sb.append("    } \n");
    sb.append("  } \n");
    return sb.toString();
  }

  public String getSourceCode_Deserializer() {
    Collection<String> classNames = getClassNames(jsonString);
    Iterator<String> iter = classNames.iterator();
    StringBuilder sb = new StringBuilder();
    ArrayList<String> types = new ArrayList<String>();
    types.add("boolean");
    types.add("byte");
    types.add("char");
    types.add("short");
    types.add("int");
    types.add("long");
    types.add("float");
    types.add("double");
    types.add("String");

    sb.append(package_share);
    //    sb.append("import edu.duke.ece651.classbuilder.*; \n");
    sb.append("import java.util.*; \n");
    sb.append("import org.json.*; \n \n");
    sb.append("public class Deserializer { \n");
    while (iter.hasNext()) {
      String className = (String)iter.next();
      sb.append("  public static " + className + " read" + className +
                "(JSONObject js) throws JSONException { \n");
      sb.append("    HashMap<Integer, Object> map = new HashMap<Integer, Object>(); \n");
      sb.append("    return read" + className + "_helper(js, map); \n");
      sb.append("  } \n");
      sb.append("  public static " + className + " read" + className + "_helper"
                + "(JSONObject js, HashMap<Integer, Object> map) { \n");
      sb.append("    int id = js.has(\"ref\") ? js.getInt(\"ref\") : js.getInt(\"id\"); \n");
      sb.append("    " + className + " result = map.containsKey(id)? (" + className +
                ")map.get(id) : "
                + "new " + className + "(); \n");
      sb.append("    map.put(id, result); \n");
      sb.append("    if (js.has(\"ref\")) { \n");
      sb.append("      return result; \n");
      sb.append("    } \n");
      sb.append("    JSONArray values_arr = js.getJSONArray(\"values\"); \n");
      for (int i = 0; i < info.size(); ++i) {
        if (className.equals(info.get(i).get("classname"))) {
          for (int j = 0; j < (info.get(i).size() - 1) / 2; ++j) {
            String field_name = info.get(i).get("fieldsname" + Integer.toString(j));
            String fn = field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
            String field_type = info.get(i).get("fieldstype" + Integer.toString(j));
            String ft = field_type.substring(0, 1).toUpperCase() + field_type.substring(1);
            if (types.contains(field_type) || field_type.contains("ArrayList")) {
              if (field_type.contains("ArrayList")) {
                sb.append("    result.set" + fn + "(values_arr.getJSONObject(" +
                          Integer.toString(j) + ").getJSONArray(\"" + field_name + "\"));\n");
              }
              else {
                sb.append("    result.set" + fn + "(values_arr.getJSONObject(" +
                          Integer.toString(j) + ").get" + ft + "(\"" + field_name + "\"));\n");
              }
            }
            else {
              sb.append("    result.set" + fn + "(values_arr.getJSONObject(" + Integer.toString(j) +
                        ").get(\"" + field_name + "\").toString().equals(\"null\") ? null : read" +
                        field_type + "_helper(values_arr.getJSONObject(" + Integer.toString(j) +
                        ").getJSONObject(\"" + field_name + "\"), map)); \n");
            }
          }
          sb.append("return result; \n");
        }
      }
      sb.append("  } \n");
    }
    sb.append("} \n");
    return sb.toString();
  }

  public String getSourceCode(String className) {
    handleString(jsonString);
    boolean found = false;
    String constructor = new String();
    String serialization = new String();
    String source_code = new String();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < info.size(); ++i) {
      if (info.get(i).get("package") != null) {
        package_share = "package " + info.get(i).get("package") + "; \n \n";
        sb.append("package " + info.get(i).get("package") + "; \n \n");
        continue;
      }
      if (className.equals(info.get(i).get("classname"))) {
        found = true;
        constructor = constructor_builder(info.get(i));
        serialization = serialization_builder(info.get(i));
        //        sb.append("import edu.duke.ece651.classbuilder.*; \n");
        sb.append("import java.util.*; \n");
        sb.append("import org.json.*; \n \n");
        sb.append("public class " + className + " {"
                  + "\n");
        for (int j = 0; j < (info.get(i).size() - 1) / 2; ++j) {
          sb.append("  private " + info.get(i).get("fieldstype" + Integer.toString(j)) + " " +
                    info.get(i).get("fieldsname" + Integer.toString(j)) + ";"
                    + "\n");
          if ((info.get(i).get("fieldstype" + Integer.toString(j))).contains("ArrayList")) {
            sb.append(num_array(info.get(i).get("fieldsname" + Integer.toString(j))));
            sb.append(add_array(info.get(i).get("fieldsname" + Integer.toString(j)),
                                info.get(i).get("fieldstype" + Integer.toString(j))));
            sb.append(get_array(info.get(i).get("fieldsname" + Integer.toString(j)),
                                info.get(i).get("fieldstype" + Integer.toString(j))));
            sb.append(set_array(info.get(i).get("fieldsname" + Integer.toString(j)),
                                info.get(i).get("fieldstype" + Integer.toString(j))));
          }
          else {
            sb.append(get(info.get(i).get("fieldsname" + Integer.toString(j)),
                          info.get(i).get("fieldstype" + Integer.toString(j))));
            sb.append(set(info.get(i).get("fieldsname" + Integer.toString(j)),
                          info.get(i).get("fieldstype" + Integer.toString(j))));
          }
        }
        sb.append(constructor);
        sb.append(serialization);
        sb.append("}");
      }
    }
    if (found) {
      source_code = sb.toString();
      return source_code;
    }
    else {
      return "";
    }
    // sb.append("}");
    // source_code = sb.toString();
    // return source_code;
  }

  public void createAllClasses(String basePath) throws FileNotFoundException {
    Collection<String> classNames = getClassNames(jsonString);
    Iterator<String> iter = classNames.iterator();
    while (iter.hasNext()) {
      String className = (String)iter.next();
      String source_code = getSourceCode(className);
      File fl = new File(basePath + className + ".java");
      String path = basePath + className + ".java";
      try {
        Files.writeString(Paths.get(path), source_code);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      File fl1 = new File(basePath + "Deserializer.java");
      String path1 = basePath + "Deserializer.java";
      try {
        Files.writeString(Paths.get(path1), getSourceCode_Deserializer());
      }
      catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }
}
