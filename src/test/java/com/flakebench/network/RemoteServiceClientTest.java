package com.flakebench.network;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

public class RemoteServiceClientTest {

    @Test
    public void testPingLocalService() throws IOException {
        try (ServerSocket server = new ServerSocket(0)) {
            int port = server.getLocalPort();
            Thread acceptor = new Thread(() -> {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(80));
                    Socket s = server.accept();
                    s.close();
                } catch (Exception ignored) {
                }
            });
            acceptor.setDaemon(true);
            acceptor.start();

            RemoteServiceClient client = new RemoteServiceClient();
            boolean reachable = client.ping("127.0.0.1", port, 50);
            assertTrue(reachable);
        }
    }
}