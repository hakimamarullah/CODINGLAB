package com.codinglab.sorting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeSortTest extends SetUp{
    @Test
    void mergeSort() {
        sort.sort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void mergeSort2() {
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
        return new MergeSort<>();
    }
}