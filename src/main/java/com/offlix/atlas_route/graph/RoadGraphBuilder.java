package com.offlix.atlas_route.graph;

import com.offlix.atlas_route.constant.Constant;
import com.offlix.atlas_route.model.Coordinate;
import com.offlix.atlas_route.model.GraphEdge;
import com.offlix.atlas_route.model.GraphNode;
import com.offlix.atlas_route.utils.DistanceCalculator;
import com.offlix.atlas_route.utils.NodeIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoadGraphBuilder {
    private static final Logger log = LoggerFactory.getLogger(RoadGraphBuilder.class);

    private final ConcurrentHashMap<String, GraphNode> nodes = new ConcurrentHashMap<>();


    public Map<String, GraphNode> buildFromPolyline(List<Coordinate> coordinates, double trafficMultiplier){
        if(coordinates==null || coordinates.size()<2){
            log.warn("Cannot build graph from less than 2 coordinates");
            return Collections.emptyMap();
        }

        log.info("Building graph from {} coordinates", coordinates.size());
        Map<String, GraphNode> graphNodes = new HashMap<>();
        GraphNode prevNode = null;
        for(int i=0;i<coordinates.size();i++){
            Coordinate coord = coordinates.get(i);
            // return GraphNode if exists, create if not exist
            GraphNode node = findOrCreateNode(coord);
            if(prevNode!=null){
                double distanceKm = DistanceCalculator.harvesineDistance(prevNode.getCoordinate(), coord);
                double baseWeight = (distanceKm/Constant.DEFAULT_SPEED_KM)*60.0;
                GraphEdge edge = new GraphEdge(prevNode, node, distanceKm, baseWeight, trafficMultiplier);
                prevNode.addEdge(edge);
            }
            graphNodes.put(node.getId(), node);
            prevNode = node;
        }
        return graphNodes;
    }
    public GraphNode findOrCreateNode(Coordinate coordinate){
        Optional<GraphNode> nearByNode = nodes.values().stream().filter(node -> {
            double distance = DistanceCalculator.harvesineDistance(node.getCoordinate(), coordinate);
            return distance < Constant.NODE_DEDUP_THRESHOLD_KM;
        }).findFirst();

        if(nearByNode.isPresent()){
            return nearByNode.get();
        }

        String nodeId = NodeIdGenerator.generate();
        GraphNode node = new GraphNode(nodeId, coordinate);
        nodes.put(nodeId, node);
        return node;
    }

    public Optional<GraphNode> findClosestNode(Coordinate coordinate){
        return nodes.values().stream().min(
            Comparator.comparingDouble(node -> DistanceCalculator.harvesineDistance(node.getCoordinate(), coordinate)));
    }

    public Optional<GraphNode> getNode(String nodeId){
        return Optional.ofNullable(nodes.get(nodeId));
    }

    public Map<String, Double> statistics(){

        double totalEdges = nodes.values().stream().mapToInt(node-> node.getEdges().size()).sum();
        double totalNodes = nodes.size();
        double averageWeights = nodes.isEmpty()? 0: totalEdges/totalNodes;

        return Map.of("totalEdges", totalEdges, "totalNodes", totalNodes, "averageWeights", averageWeights);

    }

    public void clear(){
        NodeIdGenerator.reset();
        nodes.clear();
        log.info("Graph cleared");
    }

    @Override
    public String toString() {
        return "RoadGraphBuilder{" +
                "nodes=" + nodes +
                '}';
    }
}
