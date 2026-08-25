package com.flakebench.worker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BackgroundWorker {
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final Thread workerThread;
    public volatile int processedCount = 0;

    public BackgroundWorker() {
        workerThread = new Thread(this::runLoop);
        workerThread.setDaemon(true);
        workerThread.start();
    }

    private void runLoop() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
                processedCount++;
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void submit(Runnable task) {
        queue.add(task);
    }
}