package com.codinglab.sorting;

public abstract class Sort<T extends Comparable<T>> {
    public abstract void sort(T[] array);

    public void reverseSort(T[] array){
        sort(array);
        int n = array.length-1;
        for(int i=0; i<=n/2; i++){
            T temp = array[i];
            array[i] = array[n-i];
            array[n-i] = temp;
        }
    }

}
