package com.flakebench.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskExecutor {
    private final ExecutorService pool;

    public TaskExecutor(int threads) {
        pool = Executors.newFixedThreadPool(threads);
    }

    public void submit(List<Callable<Void>> callables) throws Exception {
        List<Future<Void>> futures = new ArrayList<>();
        for (Callable<Void> c : callables) {
            futures.add(pool.submit(c));
        }
        for (Future<Void> f : futures) {
            f.get();
        }
    }
}