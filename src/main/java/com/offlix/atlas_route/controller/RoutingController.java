package com.offlix.atlas_route.controller;

import com.offlix.atlas_route.dto.RouteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/route")
@Tag(name = "Routing", description = "Route computation endpoints")
public class RoutingController {

    @PostMapping("/shortest")
    @Operation(summary = "Compute Shortest route", description = "Computes the shortest path between two coordinates using specified algorithm and map provider")
    public ResponseEntity<RouteResponse> computeShortestRoute(){

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
