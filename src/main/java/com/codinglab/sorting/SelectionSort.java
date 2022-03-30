package com.codinglab.sorting;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int indexMin = i;

            for (int j = i + 1; j < n; j++) {
                if (array[indexMin].compareTo(array[j]) > 0)
                    indexMin = j;

            }
            T temp = array[i];
            array[i] = array[indexMin];
            array[indexMin] = temp;
        }
    }


}
