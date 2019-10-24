package com.github.hh.je.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.stream().filter(i -> i.startsWith("a"))
                .map(String::trim)
                .forEach(System.out::println);
    }
}
