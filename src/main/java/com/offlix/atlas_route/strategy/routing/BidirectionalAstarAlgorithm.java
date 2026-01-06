package com.offlix.atlas_route.strategy.routing;

import com.offlix.atlas_route.model.GraphNode;
import com.offlix.atlas_route.model.RouteResult;
import com.offlix.atlas_route.strategy.enums.RoutingAlgorithmType;

import java.util.Map;

public class BidirectionalAstarAlgorithm implements RoutingAlgorithm {
    @Override
    public RouteResult findShortestPath(Map<String, GraphNode> graph, GraphNode start, GraphNode end) {
        return null;
    }

    @Override
    public RoutingAlgorithmType getType() {
        return RoutingAlgorithmType.BIDIRECTIONAL_ASTAR;
    }
}
