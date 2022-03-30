package com.codinglab.sorting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest extends SetUp{

    @Test
    void bubbleSort() {
        sort.sort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void bubbleSort2() {
        sort.sort(target);
        assertArrayEquals(exp, target);
    }

    @Test
    void reverseBubbleSort(){
        sort.reverseSort(target);
        assertArrayEquals(rev,target);
    }


    @Override
    Sort<Integer> getInstance() {
        return new BubbleSort<>();
    }

}