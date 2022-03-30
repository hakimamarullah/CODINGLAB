package com.codinglab.sorting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    private MergeSort<Integer> sort;
    private ArrayList<Integer> expected;
    private Integer[] target;
    private Integer[] exp;

    @BeforeEach
    void setUp() {
        sort = new MergeSort<>();
        Random rand = new Random();
        expected = new ArrayList<>();
        target = new Integer[1000_000];



        for(int i=0; i<1000_000; i++){
            int element = rand.nextInt(i,1000_000);
            expected.add(element);
            target[i] = element;
        }
        Collections.sort(expected);
        exp = expected.toArray(new Integer[0]);


    }

    @Test
    void mergeSort() {
        sort.mergeSort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void mergeSort2() {
        sort.mergeSort(target);
        assertArrayEquals(exp, target);
    }

    @AfterEach
    void tearDown(){
        sort = null;
        Random rand = null;
        expected = null;
        target = null;

    }
}