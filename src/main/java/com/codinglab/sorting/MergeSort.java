package com.codinglab.sorting;

public class MergeSort<T extends Comparable<T>> {

    public static <T extends Comparable<T>> void mergeSort(T[] arr){
        mergeSort(arr,0,arr.length -1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] arr, int start, int end){

        int length = (end - start) + 1;
        int mid = length/2;
        
    }

    private static <T extends Comparable<T>> void merge(T[] arr, int left, int right){

    }
}
