package com.github.hh.je.concurrent.phaser;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    @Test
    public void test() throws InterruptedException {
        Phaser phaser = new Phaser(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(createRunnable(phaser));
        executorService.submit(createRunnable(phaser));
        executorService.submit(createRunnable(phaser));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

    private Runnable createRunnable(Phaser phaser) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": phase1");
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + ": phase2");
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + ": phase3");
            }
        };
    }
}
