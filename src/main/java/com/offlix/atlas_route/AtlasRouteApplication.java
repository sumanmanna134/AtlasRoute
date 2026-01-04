package com.offlix.atlas_route;

import com.offlix.atlas_route.dto.Coordinate;
import com.offlix.atlas_route.utils.DistanceCalculator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtlasRouteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AtlasRouteApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Coordinate from = new Coordinate(28.6315, 77.2167);
        Coordinate to = new Coordinate(28.6129,77.2295);
        System.out.println(from.toString());
        System.out.println(from.toHashKey());

        double v = DistanceCalculator.harvesineDistance(to, from);
        System.out.println("Distance: "+v);
    }
}
