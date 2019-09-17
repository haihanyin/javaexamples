package com.github.hh.je.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {

    public void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();

    }

    public void newFixedThreadPool() {
        Executors.newFixedThreadPool(3);
    }

    public void newScheduledThreadPool() {
        Executors.newScheduledThreadPool(3);
    }

    public void newSingleThreadExecutor() {
        Executors.newSingleThreadExecutor();
    }

    public void newSingleThreadScheduledExecutor() {
        Executors.newSingleThreadScheduledExecutor();
    }

    public void test_callable() {
        Executors.callable(() -> {});
    }

    public void test_callableWithResult() {
        String result = null;
        Executors.callable(() -> {}, result);
    }


}
