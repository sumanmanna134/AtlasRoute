package com.offlix.atlas_route.dto;

import com.offlix.atlas_route.model.GraphNode;

/**
 * compare current distance with prev node's distance
 * @param node accept Node, that contains id, Coordinate, edges, and metadata
 * @param distance accept distance from source a to target b
 */

public record NodeDistance(GraphNode node, double distance) implements Comparable<NodeDistance> {

    @Override
    public int compareTo(NodeDistance o) {
        return Double.compare(distance, o.distance);
    }
}
