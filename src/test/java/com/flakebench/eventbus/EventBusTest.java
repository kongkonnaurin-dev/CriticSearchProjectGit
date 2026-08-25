package com.flakebench.eventbus;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class EventBusTest {

    @Test
    public void testEventDeliveredToAllSubscribers() throws InterruptedException {
        EventBus bus = new EventBus();
        AtomicInteger counter = new AtomicInteger();
        bus.subscribe(counter::incrementAndGet);
        bus.subscribe(counter::incrementAndGet);
        bus.subscribe(counter::incrementAndGet);

        bus.publish("order.created");

        Thread.sleep(200);

        assertEquals(3, counter.get());
    }
}