package com.flakebench.racy;

public class UnsafeSingleton {
    private static UnsafeSingleton instance;
    public static int constructionCount = 0;

    private UnsafeSingleton() {
        constructionCount++;
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {
        }
    }

    public static UnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new UnsafeSingleton();
        }
        return instance;
    }
}