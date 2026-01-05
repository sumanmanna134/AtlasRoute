package com.offlix.atlas_route.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.offlix.atlas_route.enums.RoutingAlgorithmType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Response DTO containing computed route information.
 *
 * Includes:
 * - Algorithm used
 * - Distance and duration
 * - Path coordinates
 * - Encoded polyline
 * - Metadata (performance metrics)
 */
public record RouteResponse (

    @JsonProperty("algorithmUsed")
    RoutingAlgorithmType algorithmUsed,

    @JsonProperty("distanceKm")
    double distanceKm,

    @JsonProperty("durationMinutes")
    double durationMinutes,

    @JsonProperty("trafficAware")
    boolean trafficAware,

    @JsonProperty("path")
    List<Coordinate> path,

    @JsonProperty("encodedPolyline")
    String encodedPolyline,

    @JsonProperty("metadata")
    Map<String,Object> metadata
) implements Serializable{
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private RoutingAlgorithmType algorithmUsed;
        private double distanceKm;
        private double durationMinutes;
        private boolean trafficAware=true;
        private List<Coordinate> path;
        private String encodedPolyline;
        private Map<String, Object> metadata;

        public Builder algorithmUsed(RoutingAlgorithmType algorithmUsed){
            this.algorithmUsed = algorithmUsed;
            return this;
        }

        public Builder distanceKm(double distanceKm) {
            this.distanceKm = distanceKm;
            return this;
        }
        public Builder durationMinutes(double durationMinutes){
            this.durationMinutes = durationMinutes;
            return this;
        }

        public Builder trafficAware(boolean trafficAware){
            this.trafficAware = trafficAware;
            return this;
        }

        public Builder path(List<Coordinate> path){
            this.path = path;
            return this;
        }
        public Builder encodedPolyline(String encodedPolyline){
            this.encodedPolyline = encodedPolyline;
            return this;
        }
        public Builder metadata(Map<String, Object> metadata){
            this.metadata = metadata;
            return this;
        }

        public RouteResponse build(){
            return new RouteResponse(algorithmUsed, distanceKm, durationMinutes, trafficAware, path, encodedPolyline, metadata);
        }



    }



}
