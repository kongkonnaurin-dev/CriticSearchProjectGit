package com.flakebench.schedule;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.Assert.assertTrue;

public class TaskExecutorTimingTest {

    @Test
    public void shouldBeFasterWhenRunningMultipleSlowTasks() throws Exception {
        TaskExecutor executor = new TaskExecutor(4);
        long delay = 50;
        int times = 4;

        List<Callable<Void>> callables = Arrays.asList(
                slowTask(delay), slowTask(delay), slowTask(delay), slowTask(delay));

        long start = System.currentTimeMillis();
        executor.submit(callables);
        long end = System.currentTimeMillis();

        long elapsed = end - start;
        assertTrue("expected parallel execution to be faster than " + (delay * times) + "ms",
                elapsed < delay * times);
    }

    private static Callable<Void> slowTask(long delayMs) {
        return () -> {
            Thread.sleep(delayMs);
            return null;
        };
    }
}