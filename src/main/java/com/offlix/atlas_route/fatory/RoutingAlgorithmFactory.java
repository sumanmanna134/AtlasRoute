package com.offlix.atlas_route.fatory;

import com.offlix.atlas_route.strategy.enums.RoutingAlgorithmType;
import com.offlix.atlas_route.strategy.routing.RoutingAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RoutingAlgorithmFactory {
    private final Map<RoutingAlgorithmType, RoutingAlgorithm> algorithmMap;


    public RoutingAlgorithmFactory(List<RoutingAlgorithm> algorithms) {
        this.algorithmMap = algorithms.stream()
                .collect(Collectors.toUnmodifiableMap(RoutingAlgorithm::getType, Function.identity()));
    }

    public RoutingAlgorithm getAlgorithm(RoutingAlgorithmType type){
        return Optional.ofNullable(type)
                .map(algorithmMap::get)
                .orElseThrow(()-> new IllegalArgumentException("Unsupported routing algorithm "+type));
    }
}
