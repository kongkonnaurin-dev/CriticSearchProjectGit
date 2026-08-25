package com.flakebench.counter;

public class ConcurrentCounter {
    private int value = 0;
    public volatile boolean writerDone = false;

    public void incrementNTimes(int n) {
        new Thread(() -> {
            for (int i = 0; i < n; i++) {
                value++;
            }
            writerDone = true;
        }).start();
    }

    public int getValue() {
        return value;
    }
}