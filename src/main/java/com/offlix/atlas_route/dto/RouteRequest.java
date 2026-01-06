package com.offlix.atlas_route.dto;

import com.offlix.atlas_route.strategy.enums.MapProviderType;
import com.offlix.atlas_route.strategy.enums.RoutingAlgorithmType;
import com.offlix.atlas_route.model.Coordinate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RouteRequest (
        @NotNull(message = "Source coordinate is required")
        @Valid
        Coordinate start,

        @NotNull(message = "End coordinate is Required")
        @Valid
        Coordinate end,

        @NotNull(message = "Algorithm is Required")
        RoutingAlgorithmType algorithm,

        @NotNull(message = "Map Provider is Required")
        MapProviderType provider){

    public String toCacheKey(){
        return String.format("%s:%s:%s:%s", provider.name(), algorithm.name(), start.toHashKey(), end.toHashKey());
    }
}
