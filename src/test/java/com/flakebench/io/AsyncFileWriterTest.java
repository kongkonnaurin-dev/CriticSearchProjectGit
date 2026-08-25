package com.flakebench.io;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class AsyncFileWriterTest {

    @Test
    public void testFileContentAfterAsyncWrite() throws Exception {
        Path tmp = Files.createTempFile("flakebench", ".txt");
        tmp.toFile().deleteOnExit();

        AsyncFileWriter writer = new AsyncFileWriter();
        writer.writeAsync(tmp, "hello-flakebench");

        Thread.sleep(200);

        String content = new String(Files.readAllBytes(tmp));
        assertEquals("hello-flakebench", content);
    }
}