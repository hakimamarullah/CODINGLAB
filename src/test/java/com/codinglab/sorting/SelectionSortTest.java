package com.codinglab.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest extends SetUp {


    @Test
    void selectionSort() {
        sort.sort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void selectionSort2() {
        sort.sort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void reverseSort(){
        sort.reverseSort(target);
        assertArrayEquals(rev,target);
    }
    @Override
    Sort<Integer> getInstance() {
        return new SelectionSort<>();
    }
}