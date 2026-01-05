package com.offlix.atlas_route.utils;

import com.offlix.atlas_route.constant.Constant;
import com.offlix.atlas_route.dto.Coordinate;
import com.offlix.atlas_route.dto.graph.GraphNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoadGraphBuilder {
    private static final Logger log = LoggerFactory.getLogger(RoadGraphBuilder.class);

    private final ConcurrentHashMap<String, GraphNode> nodes = new ConcurrentHashMap<>();

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

    @Override
    public String toString() {
        return "RoadGraphBuilder{" +
                "nodes=" + nodes +
                '}';
    }
}
