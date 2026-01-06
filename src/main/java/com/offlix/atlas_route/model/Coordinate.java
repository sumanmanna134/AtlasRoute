package com.offlix.atlas_route.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record Coordinate(
        @NotNull
        @Min(value = -90, message = "Latitude must be >= -90")
        @Max(value = 90, message = "Longitude must be <= 90")
        @JsonProperty("latitude")
        Double latitude,
        @NotNull
        @Min(value = -180, message = "Latitude must be >= -180")
        @Max(value = 180, message = "Longitude must be <= 180")
        @JsonProperty("longitude")
        Double longitude
) implements Serializable {
    public String toHashKey(){
        return String.format("%.6f_%.6f", latitude, longitude);
    }

    @Override
    public String toString() {
        return String.format("(%.6f, %.6f)", latitude, longitude);
    }


}
