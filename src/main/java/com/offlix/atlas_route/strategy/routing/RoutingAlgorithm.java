package com.offlix.atlas_route.strategy.routing;

import com.offlix.atlas_route.model.GraphNode;
import com.offlix.atlas_route.model.RouteResult;
import com.offlix.atlas_route.strategy.enums.RoutingAlgorithmType;

import java.util.Map;
import java.util.Objects;

public interface RoutingAlgorithm {

    RouteResult findShortestPath(Map<String, GraphNode> graph, GraphNode start, GraphNode end);

    RoutingAlgorithmType getType();

    default boolean reachedDestination(GraphNode start, GraphNode end){
        boolean reachedDestination = Objects.equals(start, end);
        if(reachedDestination){
            return true;
        }
        return false;
    }
}
