package com.offlix.atlas_route.controller;

import com.offlix.atlas_route.constant.Constant;
import com.offlix.atlas_route.dto.RouteRequest;
import com.offlix.atlas_route.dto.RouteResponse;
import com.offlix.atlas_route.model.Coordinate;
import com.offlix.atlas_route.model.GraphNode;
import com.offlix.atlas_route.model.RouteResult;
import com.offlix.atlas_route.strategy.enums.MapProviderType;
import com.offlix.atlas_route.strategy.enums.RoutingAlgorithmType;
import com.offlix.atlas_route.strategy.routing.RoutingAlgorithm;
import com.offlix.atlas_route.service.RoutingService;
import com.offlix.atlas_route.graph.RoadGraphBuilder;
import com.offlix.atlas_route.utils.FakeRouteMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/route")
@Tag(name = "Routing", description = "Route computation endpoints")
@RequiredArgsConstructor
public class RoutingController {
    private final RoutingService routingService;

    @PostMapping("/shortest")
    @Operation(summary = "Compute Shortest route", description = "Computes the shortest path between two coordinates using specified algorithm and map provider")
    public ResponseEntity<?> computeShortestRoute(){
        Coordinate loc1 = FakeRouteMap.fakeStartCoordinate();
        Coordinate loc2 = FakeRouteMap.fakeEndCoordinate();

        RouteRequest request = new RouteRequest(loc1, loc2, RoutingAlgorithmType.DIJKSTRA, MapProviderType.GOOGLE_MAPS);

        RouteResponse result = routingService.computeRoute(request);


        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
