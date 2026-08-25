package com.flakebench.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class AsyncQueueProcessor {
    private final ExecutorService pool = Executors.newSingleThreadExecutor();
    private final AtomicReference<String> result = new AtomicReference<>();

    public void process(String input) {
        pool.submit(() -> stageOne(input));
    }

    private void stageOne(String input) {
        stageTwo(input.trim());
    }

    private void stageTwo(String input) {
        stageThree(input.toUpperCase());
    }

    private void stageThree(String input) {
        stageFour(validate(input));
    }

    private String validate(String input) {
        return input.isEmpty() ? "EMPTY" : input;
    }

    private void stageFour(String input) {
        persist(input);
    }

    private void persist(String input) {
        result.set(input);
    }

    public String getResult() {
        return result.get();
    }
}