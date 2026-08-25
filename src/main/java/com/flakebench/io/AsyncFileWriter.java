package com.flakebench.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncFileWriter {
    private final ExecutorService pool = Executors.newSingleThreadExecutor();
    public volatile boolean writeComplete = false;

    public void writeAsync(Path path, String content) {
        pool.submit(() -> doWrite(path, content));
    }

    private void doWrite(Path path, String content) {
        try {
            Files.write(path, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writeComplete = true;
        }
    }
}