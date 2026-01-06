package com.offlix.atlas_route.strategy.enums;
/**
 * Supported routing algorithms.
 *
 * DIJKSTRA: Classic shortest path, explores all directions
 * ASTAR: Heuristic-based, uses Haversine distance
 * BIDIRECTIONAL_ASTAR: Most efficient, searches from both ends
 */
public enum RoutingAlgorithmType {
    DIJKSTRA,
    ASTAR,
    BIDIRECTIONAL_ASTAR
}
