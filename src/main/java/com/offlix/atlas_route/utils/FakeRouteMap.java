package com.offlix.atlas_route.utils;

import com.offlix.atlas_route.model.Coordinate;

import java.util.List;

public class FakeRouteMap {
    public static Coordinate fakeStartCoordinate(){
        return new Coordinate(28.6315, 77.2167);
    }

    public static Coordinate fakeEndCoordinate(){
        return new Coordinate(28.3515, 76.9428);
    }
    public static List<Coordinate> fetchRoute(){
       return List.of(new Coordinate(22.430955, 87.323134),        // Start
               new Coordinate(28.6315, 77.2167), // Delhi (Start)
               new Coordinate(28.5921, 77.1622), // Dhaula Kuan
               new Coordinate(28.5020, 77.0875), // Gurgaon (DLF Phase 3)
               new Coordinate(28.4595, 77.0266), // Gurgaon Sector 44
               new Coordinate(28.4111, 77.0423), // Kherki Daula Toll
               new Coordinate(28.3515, 76.9428)  // Manesar (Destination)
       );
    }
}
