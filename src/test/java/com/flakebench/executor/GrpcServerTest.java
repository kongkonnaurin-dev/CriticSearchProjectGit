package com.flakebench.executor;

import com.flakebench.metrics.GRPCMetrics;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrpcServerTest {

    @Test
    public void testGrpcExecutorPool() throws InterruptedException {
        GRPCMetrics gm = GRPCMetrics.getEmptyGRPCMetrics();
        GrpcThreadPoolExecutor executor = new GrpcThreadPoolExecutor(gm);

        executor.submit(() -> {});

        Thread.sleep(200);

        double activeThreads = gm.getGaugeMap().getOrDefault(GRPCMetrics.THREADS, 0.0);
        assertEquals(1.0, activeThreads, 0.0001);
    }
}