package com.codinglab.sorting;

public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] array) {
        int n = array.length;
        boolean swap = false;
        for(int i=0; i<n; i++){
            swap = false;
            for(int j=i+1; j<n; j++){
                if(array[i].compareTo(array[j]) > 0){
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    swap = true;
                }

            }
            if(!swap)
                break;
        }

    }


}
