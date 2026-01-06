package com.offlix.atlas_route.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutionTimer {
    private final AtomicLong startTimeNanos = new AtomicLong(0);

    public void start(){
        startTimeNanos.set(System.nanoTime());
    }

    public long elapsedMillis(){
        long start = startTimeNanos.get();
        if(start==0) return 0;
        return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
    }
}
