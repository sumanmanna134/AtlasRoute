package com.offlix.atlas_route.utils;

import com.offlix.atlas_route.dto.Coordinate;

public class DistanceCalculator {
    private static final double EARTH_RADIUS_KM = 6371.0;

    private DistanceCalculator(){}

    public static double harvesineDistance(Coordinate from, Coordinate to){
        // convert degree to radian (source : lat, dest: lat)
        double fromLatRad = toRadians(from.latitude());
        double toLatRad = toRadians(to.latitude());

        double deltaLatRad = deltaLatitudeRad(from, to);
        double deltaLonRad = deltaLongitudeRad(from, to);

        /**a : How much of the Earth’s curvature is involved between these two points?
         * a = 0 → same location
         * a = 1 → opposite sides of Earth
         */
        double a = harvesineFormula(deltaLatRad, fromLatRad, toLatRad, deltaLonRad);

        double c = angularDistance(a);

        /**
         * R = radius of Earth (≈ 6371 km)
         * c = angular distance (in radians)
         * distance=R×c
         */
        return EARTH_RADIUS_KM * c;
    }

    private static double harvesineFormula(double deltaLatRad, double fromLatRad, double toLatRad, double deltaLonRad) {
        return square(Math.sin(deltaLatRad / 2))
                + multiply(fromLatRad,toLatRad)
                * square(Math.sin(deltaLonRad / 2));
    }

    private static double deltaLatitudeRad(Coordinate from, Coordinate to){
        return toRadians(to.latitude() - from.latitude());
    }

    private static double multiply(double fromLatRad, double toLatRad){
        return Math.cos(fromLatRad) * Math.cos(toLatRad);
    }

    private static double deltaLongitudeRad(Coordinate from, Coordinate to){
        return toRadians(to.longitude() - from.longitude());
    }
    private static double angularDistance(double a){
        return 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
    }
    private static double toRadians(double value){
        return Math.toRadians(value);
    }
    private static double square(double value){
        return value * value;
    }

}
