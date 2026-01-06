package com.offlix.atlas_route.handson;

import java.util.*;
import java.util.stream.Stream;

public class DijkstraAlgoTest {

    public static void calculateShortestPath(Graph graph, String startId, String targetId){
        HashMap<String, Double> distanceMap = new HashMap<>();
        HashMap<String, String> prevNodes = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();

        for(Graph.GNode node: graph.getAllNodes()){
            distanceMap.put(node.getId(), Double.MAX_VALUE);
        }
        int nodeExplore=0;

        Graph.GNode startNode= graph.getNode(startId);
        if(startNode==null) return;
        distanceMap.put(startId, 0.0);
        pq.add(new NodeDistance(startNode, 0.0));

        while(!pq.isEmpty()){
            NodeDistance current = pq.poll();
            String uId = current.currentNode().getId();
            if(visited.contains(uId)) continue;

            visited.add(uId);
            nodeExplore++;

            if(uId.equals(targetId)) break; // currentPoint == target so break;

            for(Graph.Gedge gedge: current.currentNode().getEdges()){
                String vId = gedge.getTo().getId();
                double weight = gedge.getWeight();
                double newDist = distanceMap.get(uId) + weight;
                if(newDist < distanceMap.get(vId)){
                    distanceMap.put(vId, newDist);
                    prevNodes.put(vId, uId);
                    pq.add(new NodeDistance(gedge.getTo(), newDist));
                }

            }

        }

        System.out.println("Node Explore: "+ nodeExplore);
        System.out.println("PQ: "+ pq.toString());

        printPath(startId, targetId, distanceMap, prevNodes);
    }

    private static void printPath(String start, String end, Map<String, Double> distanceMap, Map<String, String> prevNodes){
        if(distanceMap.get(end) == Double.MAX_VALUE){
            System.out.println("No path found from " + start + " to " + end);
            return;
        }

        List<String> path = new ArrayList<>();
        for (String at=end;at!=null;at=prevNodes.get(at)){
            path.add(at);
        }

        Collections.reverse(path);

        System.out.println("Shortest Distance: "+ distanceMap.get(end));
        System.out.println("Route: "+String.join("->", path));
    }


    private record  NodeDistance(Graph.GNode currentNode, double distance) implements Comparable<NodeDistance> {

        @Override
        public int compareTo(NodeDistance otherDistance) {
            return Double.compare(this.distance , otherDistance.distance());
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        List<List<?>> edges = List.of(
                List.of("Delhi", "Noida", 25.4, 1.8),
                List.of("Noida", "GreaterNoida", 28.2, 1.2),
                List.of("Delhi", "Gurgaon", 32.1, 2.2),
                List.of("Gurgaon", "Manesar", 18.5, 1.4),
                List.of("Delhi", "Ghaziabad", 22.0, 1.9),
                List.of("Ghaziabad", "Meerut", 54.0, 1.3),
                List.of("Noida", "Faridabad", 35.5, 1.5),
                List.of("Delhi", "Faridabad", 28.4, 2.0),
                List.of("Gurgaon", "Faridabad", 32.0, 1.6),
                List.of("Delhi", "Sonipat", 44.5, 1.4),

                // Mumbai / Pune Routes
                List.of("Mumbai", "Thane", 22.5, 2.4),
                List.of("Thane", "Kalyan", 24.1, 1.8),
                List.of("Mumbai", "Navi Mumbai", 20.8, 1.7),
                List.of("Navi Mumbai", "Panvel", 15.2, 1.3),
                List.of("Mumbai", "Pune", 148.0, 1.2),
                List.of("Pune", "Lonavala", 64.5, 1.1),
                List.of("Panvel", "Lonavala", 55.0, 1.2),
                List.of("Bandra", "Juhu", 5.2, 2.5),
                List.of("Andheri", "Borivali", 14.5, 2.3),
                List.of("Dadar", "Colaba", 12.0, 2.1)
        );
        List<String> cities = edges.stream().flatMap(edge -> Stream.of(edge.get(0).toString(), edge.get(1).toString())).distinct().toList();
        System.out.println(cities);
        for(String city: cities){
            graph.addNode(city);
        }

       edges.forEach(data ->{
           Iterator<?> it = data.iterator();
           if(it.hasNext()){
               String source = it.next().toString();
               String target = it.next().toString();

               double distance= ((Number) it.next()).doubleValue();
               double traffic = ((Number) it.next()).doubleValue();
               graph.addEdge(source, target, distance, traffic);
           }
       });


        calculateShortestPath(graph, "Delhi", "Manesar");
    }



}
