package com.flakebench.cache;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsyncCacheTest {

    @Test
    public void testAllKeysLoadedBeforeRead() throws InterruptedException {
        AsyncCache<String, Integer> cache = new AsyncCache<>();
        cache.loadAsync("a", k -> 1);
        cache.loadAsync("b", k -> 2);
        cache.loadAsync("c", k -> 3);

        Thread.sleep(200);

        int sum = cache.get("a") + cache.get("b") + cache.get("c");
        assertEquals(6, sum);
    }
}