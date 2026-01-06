package com.offlix.atlas_route.handson;

import com.offlix.atlas_route.model.GraphNode;

import java.util.*;

public class Graph {
    private final Map<String, GNode> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addNode(String id){
        graph.putIfAbsent(id, new GNode(id));
    }

    public void addEdge(String sourceId, String targetId, double distance, double trafficMultiplier){
        GNode source = graph.get(sourceId);
        GNode target = graph.get(targetId);
        if(source!=null && target!=null){
            double weight = distance * trafficMultiplier;
            source.addEdge(new Gedge(target, weight));
        }
    }

    public GNode getNode(String id){
        return graph.get(id);
    }

    public Collection<String> getAllNodesIdAsString(){
        return graph.values().stream().map(GNode::getId).toList();
    }

    public Collection<GNode> getAllNodes(){
        return graph.values();
    }


    public static class GNode {
        private final String id;
        private final List<Gedge> edges=new ArrayList<>();


        public GNode(String id) {
            this.id = id;
        }

        public void addEdge(Gedge edge){
            edges.add(edge); // {to, weight }
        }

        public String getId() {
            return id;
        }

        public List<Gedge> getEdges() {
            return edges;
        }

        @Override
        public String toString() {
            return "GNode{" +
                    "id='" + id + '\'' +
                    ", edges=" + edges +
                    '}';
        }
    }

    public static class Gedge {
        private final GNode to;
        private final double weight;


        public Gedge(GNode gNode, double weight) {
            to = gNode;
            this.weight = weight;
        }

        public GNode getTo() {
            return to;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Gedge{" +
                    "to=" + to.id +
                    ", weight=" + weight +
                    '}';
        }
    }


}
