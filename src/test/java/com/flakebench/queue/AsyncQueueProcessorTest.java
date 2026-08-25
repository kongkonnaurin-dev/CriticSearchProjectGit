package com.flakebench.queue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsyncQueueProcessorTest {

    @Test
    public void testProcessedValueIsUppercase() throws InterruptedException {
        AsyncQueueProcessor processor = new AsyncQueueProcessor();
        processor.process("  hello  ");

        Thread.sleep(200);

        assertEquals("HELLO", processor.getResult());
    }
}