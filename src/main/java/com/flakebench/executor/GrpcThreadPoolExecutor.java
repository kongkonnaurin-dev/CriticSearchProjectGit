package com.flakebench.executor;

import com.flakebench.metrics.GRPCMetrics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GrpcThreadPoolExecutor extends ThreadPoolExecutor {

    public volatile boolean hasExecuted = false;

    private final GRPCMetrics gm;

    public GrpcThreadPoolExecutor(GRPCMetrics gm) {
        super(2, 2, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        this.gm = gm;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        gm.incGauge(GRPCMetrics.THREADS);
        gm.setGauge(GRPCMetrics.QUEUE, getQueue().size());
        hasExecuted = true;
        super.beforeExecute(t, r);
    }
}