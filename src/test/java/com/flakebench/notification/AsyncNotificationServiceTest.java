package com.flakebench.notification;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AsyncNotificationServiceTest {

    @Test
    public void testAllSubscribersNotified() throws InterruptedException {
        AsyncNotificationService svc = new AsyncNotificationService(
                Arrays.asList("alice", "bob", "carol", "dave", "erin"));

        svc.notifyAll("system.update");

        Thread.sleep(200);

        assertEquals(5, svc.getNotifiedCount());
    }
}