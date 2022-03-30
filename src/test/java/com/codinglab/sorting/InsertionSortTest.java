package com.codinglab.sorting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest extends SetUp {
    @Test
    void insertionSort() {
        sort.sort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void insertionSort2() {
        sort.sort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void reverseInsertionSort(){
        sort.reverseSort(target);
        assertArrayEquals(rev,target);
    }


    @Override
    Sort<Integer> getInstance() {
        return new InsertionSort<>();
    }
}