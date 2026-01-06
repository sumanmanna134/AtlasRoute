package com.offlix.atlas_route.strategy.routing;

import com.offlix.atlas_route.dto.NodeDistance;
import com.offlix.atlas_route.model.GraphEdge;
import com.offlix.atlas_route.model.GraphNode;
import com.offlix.atlas_route.model.RouteResult;
import com.offlix.atlas_route.strategy.enums.RoutingAlgorithmType;
import com.offlix.atlas_route.utils.ExecutionTimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DijkstraAlgorithm implements RoutingAlgorithm {
    private final Logger log = LoggerFactory.getLogger(DijkstraAlgorithm.class);

    @Override
    public RouteResult findShortestPath(Map<String, GraphNode> graph, GraphNode startNode, GraphNode endNode) {
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        if(reachedDestination(startNode, endNode)){
            log.info("Reached Destination, Early Stopped");
            return RouteResult.builder()
                    .path(List.of(startNode))
                    .totalDistance(0.0)
                    .totalWeight(0.0)
                    .nodesExplored(1)
                    .computationTimeMs(timer.elapsedMillis()).build();

        }
        Map<String, Double> distances = new HashMap<>();
        Map<String, GraphNode> prevNodes = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        int nodesExplored = 0;
        distances.put(startNode.getId(), 0.0);
        pq.offer(new NodeDistance(startNode, 0.0));
        while (!pq.isEmpty()){
            NodeDistance current = pq.poll();
            GraphNode currentNode = current.node();
            if(visited.contains(currentNode.getId())){
                continue;
            }
            visited.add(currentNode.getId());
            nodesExplored++;
            if(reachedDestination(currentNode, endNode)){
                List<GraphNode> path = constructPath(prevNodes, startNode, endNode);
                double totalDistance = calculateDistance(path);
                double totalWeight = distances.get(endNode.getId());
                return RouteResult.builder()
                        .path(path)
                        .totalDistance(totalDistance)
                        .totalWeight(totalWeight)
                        .nodesExplored(nodesExplored)
                        .computationTimeMs(timer.elapsedMillis()).build();

                // reached destination

            }

            double currentDistance = distances.get(currentNode.getId());
            for(GraphEdge edge: currentNode.getEdges()){
                GraphNode neighbor = edge.to(); //extract neighbor
                if(visited.contains(neighbor.getId())){
                    continue;
                }

                double newDistance = currentDistance + edge.getEffectiveWeight();
                double oldDistance = distances.getOrDefault(neighbor.getId(), Double.POSITIVE_INFINITY); // initialize inf if not found
                if(newDistance<oldDistance){
                    distances.put(neighbor.getId(), newDistance);
                    prevNodes.put(neighbor.getId(), currentNode);
                    pq.offer(new NodeDistance(neighbor, newDistance));
                }

            }

        }

        // no path exist
        log.warn("Dijkstra: No path found from {} to {}", startNode.getId(), endNode.getId());
        return RouteResult.builder()
                .path(Collections.EMPTY_LIST)
                .totalWeight(0.0)
                .totalDistance(0.0)
                .nodesExplored(nodesExplored)
                .computationTimeMs(timer.elapsedMillis()).build();
    }

    private double calculateDistance(List<GraphNode> path){
        double total = 0.0;
        for(int i=0;i<path.size()-1;i++){
            GraphNode source = path.get(i);
            GraphNode target = path.get(i+1);
            Optional<GraphEdge> edge = source.getEdges().stream().filter(e -> reachedDestination(e.to(), target)).findFirst();
            if(edge.isPresent()){
                total += edge.get().distanceKm();
            }
        }
        return total;
    }

    private List<GraphNode> constructPath(Map<String, GraphNode> prev, GraphNode start, GraphNode end){
        LinkedList<GraphNode> path = new LinkedList<>();
        GraphNode current = end;
        while (current!=null){
            path.addFirst(current);
            if(reachedDestination(current, start)){
                break;
            }
            current = prev.get(current.getId());
        }

        return path;
    }

    @Override
    public RoutingAlgorithmType getType() {
        return RoutingAlgorithmType.DIJKSTRA;
    }
}
