package com.github.hh.je.concurrent.semaphor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Semaphore;

public class SemephorTest {

    @Test
    public void test() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Object>> callableList = new ArrayList(20);
        for (int i = 0; i<20; i++) {
            callableList.add(createRunnable(semaphore));
        }
        executorService.invokeAll(callableList);
    }

    private static Callable createRunnable(Semaphore semaphore) {
        return new Callable() {

            @Override
            public Object call() throws Exception {
                try {
                    semaphore.acquire();
                    System.out.println("waiting queue: " + semaphore.getQueueLength());
                    System.out.println(Thread.currentThread().getName() + " is executing...");
                    Thread.sleep(300L);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

        };
    }
}
