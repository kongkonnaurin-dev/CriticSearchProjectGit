package com.flakebench.counter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConcurrentCounterTest {

    @Test
    public void testCounterReachesTarget() throws InterruptedException {
        ConcurrentCounter counter = new ConcurrentCounter();
        counter.incrementNTimes(1000);

        Thread.sleep(500);

        assertEquals(1000, counter.getValue());
    }
}