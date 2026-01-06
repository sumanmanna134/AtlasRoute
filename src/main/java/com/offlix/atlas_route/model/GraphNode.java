package com.offlix.atlas_route.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
public final class GraphNode {
    private final String id;
    private final Coordinate coordinate;
    private final List<GraphEdge> edges;
    private final ConcurrentHashMap<String, Object> metadata;


    public GraphNode(String id, Coordinate coordinate) {
        this.id = Objects.requireNonNull(id, "Node Id cannot be null");
        this.coordinate = Objects.requireNonNull(coordinate, "Coordinate cannot be null");
        this.edges = new ArrayList<>();
        this.metadata = new ConcurrentHashMap<>();
    }

    public void addEdge(GraphEdge edge){
        if(edge==null){
            throw new IllegalArgumentException(" Edge Cannot be null");
        }

        edges.add(edge);
    }

    public String getId() {
        return id;
    }

    public ConcurrentHashMap<String, Object> getMetadata() {
        return metadata;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public List<GraphEdge> getEdges() {
        return new ArrayList<>(edges);
    }

    public void setMetadata(String key, Object value){
        metadata.put(key, value);
    }

    public void clearMetadata(){
        metadata.clear();
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "id='" + id + '\'' +
                ", coordinate=" + coordinate +
                ", edges=" + edges +
                ", metadata=" + metadata +
                '}';
    }
}
