package com.flakebench.worker;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertTrue;

public class BackgroundWorkerTest {

    @Test
    public void testTaskProcessed() throws InterruptedException {
        BackgroundWorker worker = new BackgroundWorker();
        AtomicBoolean ran = new AtomicBoolean(false);
        worker.submit(() -> ran.set(true));

        Thread.sleep(200);

        assertTrue(ran.get());
    }
}