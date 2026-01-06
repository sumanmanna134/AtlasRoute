package com.offlix.atlas_route.model;

import java.util.Objects;

public record GraphEdge(
        GraphNode from,
        GraphNode to,
        double distanceKm,
        double baseWeight,
        double trafficMultiplier) {
    public GraphEdge{
        Objects.requireNonNull(from, "From node cannot be null");
        Objects.requireNonNull(to, "To node cannot be null");
        if(distanceKm<0){
            throw new IllegalArgumentException("Distance must be non-negative");
        }

        if(baseWeight<0){
            throw new IllegalArgumentException("BaseWeight must be non-negative");
        }

        if(trafficMultiplier<1.0){
            throw new IllegalArgumentException("Traffic Multiplier must be >= 1.0");
        }
    }

    /**
     * Calculates the effective weight considering traffic.
     * Used by routing algorithms for path finding.
     */
    public double getEffectiveWeight(){
        return baseWeight * trafficMultiplier;
    }

    @Override
    public String toString(){
        return String.format("Edge[%s -> %s, %.2fkm, weight=%.2f]", from.getId(), to.getId(), distanceKm, getEffectiveWeight());
    }

}
