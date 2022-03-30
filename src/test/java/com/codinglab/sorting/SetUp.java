package com.codinglab.sorting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class SetUp {
    protected Sort<Integer> sort;
    protected ArrayList<Integer> expected;
    protected ArrayList<Integer> reversed;
    protected Integer[] target;
    protected Integer[] exp;
    protected Integer[] rev;

    @BeforeEach
    public void setUp() {
        sort = getInstance();
        String algoName = sort.getClass().getSimpleName();
        int n = algoName.contains("Merge")? 1000_000 : 100_000;

        Random rand = new Random();
        int randomizeSize = rand.nextInt(1,n);
        expected = new ArrayList<>();
        reversed = new ArrayList<>();
        target = new Integer[randomizeSize];



        for(int i=0; i<randomizeSize; i++){
            int element = rand.nextInt(i,randomizeSize);
            expected.add(element);
            reversed.add(element);
            target[i] = element;
        }
        Collections.sort(expected);
        Collections.sort(reversed, Collections.reverseOrder());
        exp = expected.toArray(new Integer[0]);
        rev = reversed.toArray(new Integer[0]);


    }

    abstract Sort<Integer> getInstance();


    @AfterEach
    void tearDown(){
        sort = null;
        Random rand = null;
        expected = null;
        target = null;

    }
}
