package com.flakebench.notification;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncNotificationService {
    private final ExecutorService pool = Executors.newFixedThreadPool(5);
    private final AtomicInteger notifiedCount = new AtomicInteger(0);
    private final List<String> subscribers;

    public AsyncNotificationService(List<String> subscribers) {
        this.subscribers = subscribers;
    }

    public void notifyAll(String message) {
        for (String s : subscribers) {
            pool.submit(() -> notifyOne(s, message));
        }
    }

    private void notifyOne(String subscriber, String message) {
        send(subscriber, message);
    }

    private void send(String subscriber, String message) {
        notifiedCount.incrementAndGet();
    }

    public int getNotifiedCount() {
        return notifiedCount.get();
    }
}