package com.github.hh.je.concurrent.cyclicbarrier;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    @Test
    public void test() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0; i<3; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println("Do task in " + Thread.currentThread().getName());
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        cyclicBarrier.
        System.out.println("Work is done");
    }
}
