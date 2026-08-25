package com.flakebench.racy;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

public class UnsafeSingletonTest {

    @Test
    public void testSingletonConstructedOnce() throws InterruptedException {
        int threads = 8;
        CountDownLatch ready = new CountDownLatch(threads);
        CountDownLatch go = new CountDownLatch(1);
        Thread[] pool = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            pool[i] = new Thread(() -> {
                ready.countDown();
                try {
                    go.await();
                } catch (InterruptedException ignored) {
                }
                UnsafeSingleton.getInstance();
            });
            pool[i].start();
        }

        ready.await();
        go.countDown();
        for (Thread t : pool) {
            t.join();
        }

        assertEquals(1, UnsafeSingleton.constructionCount);
    }
}