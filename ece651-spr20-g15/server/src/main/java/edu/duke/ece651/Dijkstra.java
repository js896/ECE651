package edu.duke.ece651;

import java.util.*;

public class Dijkstra {
  public String src;
  // List<Vertex> vertices = new ArrayList<Vertex>();
  private Data maps;
  private Map<Vertex, Integer> distMap = new HashMap<Vertex, Integer>();
  private Map<Vertex, String> vertexMap = new HashMap<Vertex, String>();
  private Map<String, Vertex> getVertexMap = new HashMap<String, Vertex>();

  private int length(String former, String latter) { return maps.sizeMap.get(latter); }

  public Dijkstra(Data data, String player, String Action) {
    maps = data;
    String[] attributes = Action.split("~");
    src = attributes[3];
    int record = 0;
    for (String element : maps.ownMap.get(player)) {
      Vertex vertex = new Vertex(element);
      if (!element.equals(src)) {
        vertex.dist = (int)Double.POSITIVE_INFINITY - record;
        ++record;
      }
      else {
        vertex.dist = 0;
      }
      distMap.put(vertex, vertex.dist);
      vertexMap.put(vertex, vertex.Name);
      getVertexMap.put(vertex.Name, vertex);
    }
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    for (Integer dist : distMap.values()) {
      pq.add(dist);
    }
    while (!pq.isEmpty()) {
      int closest = pq.poll();
      Vertex curr = findByValue(closest);
      for (String neighbor : maps.adjacentMap.get(vertexMap.get(curr))) {
        if (!maps.ownMap.get(player).contains(neighbor)) {
          continue;
        }
        if (getVertexMap.get(neighbor).dist > curr.dist + length(vertexMap.get(curr), neighbor)) {
          pq.remove(getVertexMap.get(neighbor).dist);
          distMap.remove(getVertexMap.get(neighbor), getVertexMap.get(neighbor).dist);
          getVertexMap.get(neighbor).dist = curr.dist + length(vertexMap.get(curr), neighbor);
          pq.add(getVertexMap.get(neighbor).dist);
          distMap.put(getVertexMap.get(neighbor), getVertexMap.get(neighbor).dist);
        }
      }
    }
  }

  public Vertex findByValue(int value) {
    for(Map.Entry<Vertex, Integer> entry : distMap.entrySet()) {
      int currValue = entry.getValue();
      if (currValue == value) {
        return entry.getKey();
      }
    }
    return null;
  }

  public int getShortestPath(String dst) { return getVertexMap.get(dst).dist; }
}
