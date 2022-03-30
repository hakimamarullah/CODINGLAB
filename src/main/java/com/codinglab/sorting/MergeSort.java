package com.codinglab.sorting;

public class MergeSort<T extends Comparable<T>> {

    public void mergeSort(T[] arr){
        mergeSort(arr,0,arr.length -1);
    }

    private void mergeSort(T[] arr, int start, int end){

        int length = (end - start) + 1;
        int mid = (start+end)/2;

        if(length < 2){
            return;
        }

        mergeSort(arr,start,mid);
        mergeSort(arr,mid+1,end);
        merge(arr,start,mid,end);
        
    }

    private void merge(T[] arr, int start, int mid, int end){
        int i=0,j=0,k=start;
        int leftSize = mid - start + 1;
        int rightSize = (end -mid);

        T[] left = (T[]) new Comparable[leftSize];
        T[] right = (T[]) new Comparable[rightSize];

        for(int l=0,leftOld = start; l<leftSize; l++,leftOld++){
            left[l] = arr[leftOld];
        }

        for(int r=0,rightOld = mid+1; r<rightSize; r++,rightOld++){
            right[r] = arr[rightOld];
        }

        while(i < left.length && j<right.length && k<=end){
            if(left[i].compareTo(right[j]) <=0){
                arr[k] = left[i++];
            }

            else{
                arr[k] = right[j++];
            }
            k++;
        }

        while(i < left.length){
            arr[k] = left[i];
            k++;
            i++;
        }
        while(j < right.length){
            arr[k] = right[j];
            k++;
            j++;
        }


    }
}
