package com.flakebench.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RemoteServiceClient {

    public boolean ping(String host, int port, int timeoutMs) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeoutMs);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}