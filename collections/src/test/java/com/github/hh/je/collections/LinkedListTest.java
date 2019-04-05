package com.github.hh.je.collections;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class LinkedListTest {

    @Test
    public void test_withoutSynchronizedList() throws Exception {
        final List<String> strings = new LinkedList<String>();
        IntStream.range(0, 1000000).forEach(
                i -> strings.add(UUID.randomUUID().toString())
        );

        Thread thread1 = new Thread(() -> {
            for(int i=0; i<1000; i++) {
                for(String string : strings) {
                    System.out.println(string);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i=0; i< 1000; i++) {
                ((LinkedList<String>) strings).removeFirst();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        );

        // expect to throw ConcurrentModificationException
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

    @Test
    public void test_synchronizedList() throws Exception {
        final List<String> strings = new LinkedList<String>();
        IntStream.range(0, 1000000).forEach(
                i -> strings.add(UUID.randomUUID().toString())
        );

        List<String> synchronizedList = Collections.synchronizedList(strings);
        Thread thread1 = new Thread(() -> {
            for(int i=0; i<1000; i++) {
                for(String string : synchronizedList) {
                    System.out.println(string);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int i=0; i< 1000; i++) {
                if (synchronizedList.size() > 1) {
                    ((LinkedList<String>) synchronizedList).removeFirst();
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        );

        // expect to throw ConcurrentModificationException
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

    @Test
    public void test_unmodifiableList() {
        List<String> list = new LinkedList<>();
        list.add("123");
        List<String> unmodifiableList = Collections.unmodifiableList(list);
        list.add("456"); // should fail, UnsupportedOperationException
        unmodifiableList.forEach(System.out::println);
    }

}
