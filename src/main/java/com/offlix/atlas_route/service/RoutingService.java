package com.offlix.atlas_route.service;

import com.offlix.atlas_route.dto.RouteRequest;
import com.offlix.atlas_route.dto.RouteResponse;
import com.offlix.atlas_route.graph.RoadGraphBuilder;
import com.offlix.atlas_route.model.GraphNode;
import com.offlix.atlas_route.model.RouteResult;
import com.offlix.atlas_route.fatory.RoutingAlgorithmFactory;
import com.offlix.atlas_route.strategy.routing.RoutingAlgorithm;
import com.offlix.atlas_route.utils.FakeRouteMap;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoutingService {
    private final RoutingAlgorithmFactory algorithmFactory;
    private final RoadGraphBuilder graphBuilder;
    private static final Logger log = LoggerFactory.getLogger(RoutingService.class);

   public RouteResponse computeRoute(RouteRequest request){
       log.info("Computing route: {} -> {} using {} via {}",
               request.start(), request.end(),
               request.algorithm(), request.provider());

       Map<String, GraphNode> graph = graphBuilder.buildFromPolyline(FakeRouteMap.fetchRoute(), 1.3);

       GraphNode startNode = graphBuilder.findClosestNode(request.start()).orElseThrow(()->new IllegalArgumentException("Cannot find start node"));
       log.info("Start location: {}", startNode);
       GraphNode endNode = graphBuilder.findClosestNode(request.end()).orElseThrow(()-> new IllegalArgumentException("Cannot find end node"));
       log.info("End Location: {}", endNode);

       RoutingAlgorithm routingAlgorithm = algorithmFactory.getAlgorithm(request.algorithm());
       RouteResult result = routingAlgorithm.findShortestPath(graph, startNode, endNode);

       if(!result.hasPath()){
           throw new IllegalArgumentException("Algorithm could not find any path");
       }

       return RouteResponse.builder().algorithmUsed(routingAlgorithm.getType()).distanceKm(result.totalDistance()).durationMinutes(result.getDurationMinutes())
               .trafficAware(true).encodedPolyline("").path(result.path().stream().map(e-> e.getCoordinate()).toList()).metadata(null).build();

   }




}
