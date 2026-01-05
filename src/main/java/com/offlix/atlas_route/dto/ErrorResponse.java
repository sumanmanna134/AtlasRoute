package com.offlix.atlas_route.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
        @JsonProperty("timestamp")
        Instant timestamp,
        @JsonProperty("error")
        String error,
        @JsonProperty("message")
        String message,
        @JsonProperty("details")
        List<String> details,
        @JsonProperty("path")
        String path
) {

    public static ErrorResponse of(String error, String message){
        return new ErrorResponse(Instant.now(), error, message, Collections.emptyList(),null);
    }

    public static ErrorResponse of(String error,String message, List<String> details){
        return new ErrorResponse(Instant.now(), error,message, details,null);
    }

    public static ErrorResponse of(String error, String message, String path){
        return new ErrorResponse(Instant.now(), error, message, Collections.emptyList(), path);
    }

    public static ErrorResponse of(String error, String message,List<String> details, String path){
        return new ErrorResponse(Instant.now(), error, message, details, path);
    }



}
