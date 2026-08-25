package com.flakebench.metrics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GRPCMetrics {
    public static final String THREADS = "threads";
    public static final String QUEUE = "queue";

    private final Map<String, Double> gaugeMap = new ConcurrentHashMap<>();

    public static GRPCMetrics getEmptyGRPCMetrics() {
        return new GRPCMetrics();
    }

    public void incGauge(String key) {
        gaugeMap.merge(key, 1.0, Double::sum);
    }

    public void setGauge(String key, double value) {
        gaugeMap.put(key, value);
    }

    public Map<String, Double> getGaugeMap() {
        return gaugeMap;
    }
}