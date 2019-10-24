package com.github.hh.je.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {

    @Test
    public void test() {
        List emptyList = Collections.EMPTY_LIST;
        boolean add = emptyList.add(1);
        System.out.println(emptyList.contains(1));;
        Arrays.asList(1, 2, 3);

    }
}
