package com.flakebench.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class AsyncCache<K, V> {
    private final Map<K, V> store = new ConcurrentHashMap<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    public volatile int loadCount = 0;

    public void loadAsync(K key, Function<K, V> loader) {
        pool.submit(() -> {
            V v = loader.apply(key);
            store.put(key, v);
            loadCount++;
        });
    }

    public V get(K key) {
        return store.get(key);
    }
}