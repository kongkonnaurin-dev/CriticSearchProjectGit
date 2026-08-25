package com.flakebench.gc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MemoryPressureCacheTest {

    @Test
    public void testCachePopulated() throws InterruptedException {
        MemoryPressureCache cache = new MemoryPressureCache();
        cache.populateAsync(500, 256);

        Thread.sleep(200);

        assertEquals(500, cache.size());
    }
}