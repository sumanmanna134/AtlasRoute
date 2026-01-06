package com.offlix.atlas_route.model;

import lombok.Builder;

import java.util.List;

/**
 * Result of a routing algorithm execution.
 * @param path list of graph nodes,that contains id, Coordinate, edges, and metadata
 * @param totalDistance accept total distance from source a to source b
 * @param totalWeight accept total weight (sum of weight for visited nodes)
 * @param nodesExplored total node explore during visit, accept only unvisited nodes
 * @param computationTimeMs how much take time to execution
 */
@Builder
public record RouteResult(
        List<GraphNode> path,
        double totalDistance,
        double totalWeight,
        int nodesExplored,
        long computationTimeMs

) {

    /**
     * @return extract the coordinates from path
     */
    public List<Coordinate> getCoordinatePath(){
       return path.stream().map(GraphNode::getCoordinate).toList();
    }


    /**
     *
     * @return calculate duration minutes based on totalWeight
     */
    public double getDurationMinutes(){
        return totalWeight;
    }

    /**
     *
     * @return return has path or not
     */
    public boolean hasPath(){
        return path!=null && !path.isEmpty();
    }
}
