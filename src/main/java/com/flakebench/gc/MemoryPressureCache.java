package com.flakebench.gc;

import java.util.ArrayList;
import java.util.List;

public class MemoryPressureCache {
    private final List<byte[]> cache = new ArrayList<>();
    public volatile boolean populated = false;

    public void populateAsync(int entries, int sizeKb) {
        new Thread(() -> {
            for (int i = 0; i < entries; i++) {
                cache.add(new byte[sizeKb * 1024]);
            }
            populated = true;
        }).start();
    }

    public int size() {
        return cache.size();
    }
}