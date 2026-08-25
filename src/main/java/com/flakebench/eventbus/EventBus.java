package com.flakebench.eventbus;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBus {
    private final List<Runnable> listeners = new CopyOnWriteArrayList<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(2);
    public volatile boolean delivered = false;

    public void subscribe(Runnable r) {
        listeners.add(r);
    }

    public void publish(String event) {
        pool.submit(() -> dispatch(event));
    }

    private void dispatch(String event) {
        route(event);
    }

    private void route(String event) {
        for (Runnable r : listeners) {
            r.run();
        }
        deliver();
    }

    private void deliver() {
        delivered = true;
    }
}