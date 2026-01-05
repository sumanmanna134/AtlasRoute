package com.offlix.atlas_route.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class NodeIdGenerator {
    private static final AtomicInteger nodeIdCounter = new AtomicInteger(0);

    public static String generate(){
        return String.format("node_%d",nodeIdCounter.incrementAndGet());
    }
}
