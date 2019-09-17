package com.github.hh.je.concurrent.countdownlatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(JUnit4.class)
public class CountDownLatchTest {

    @Test
    public void test() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0; i<3; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println("Do task in " + Thread.currentThread().getName());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        System.out.println("work is done");
    }

}
