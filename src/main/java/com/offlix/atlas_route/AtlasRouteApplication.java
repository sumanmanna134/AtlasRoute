package com.offlix.atlas_route;

import com.offlix.atlas_route.dto.Coordinate;
import com.offlix.atlas_route.dto.graph.GraphEdge;
import com.offlix.atlas_route.dto.graph.GraphNode;
import com.offlix.atlas_route.utils.DistanceCalculator;
import com.offlix.atlas_route.utils.NodeIdGenerator;
import com.offlix.atlas_route.utils.RoadGraphBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class AtlasRouteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AtlasRouteApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Coordinate loc1 = new Coordinate(28.6315, 77.2167);
        Coordinate loc2 = new Coordinate(28.6129,77.2295);
        Coordinate loc3 = new Coordinate(28.6129,78.2295);
        Coordinate loc4 = new Coordinate(25.6129,77.2295);

        RoadGraphBuilder roadGraphBuilder = new RoadGraphBuilder();

        GraphNode node1 = new GraphNode(NodeIdGenerator.generate(), loc1);
        GraphNode node2 = roadGraphBuilder.findOrCreateNode(loc2);
        GraphNode node3 = roadGraphBuilder.findOrCreateNode(loc3);
        GraphNode node4 = roadGraphBuilder.findOrCreateNode(loc4);
        GraphNode node5 = roadGraphBuilder.findOrCreateNode(loc2);
        System.out.println(roadGraphBuilder.toString());




    }
}
