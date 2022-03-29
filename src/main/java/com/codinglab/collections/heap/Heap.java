package com.codinglab.collections.heap;

import java.util.Vector;

public abstract class Heap<T extends Comparable<T>>{
    protected Vector<T> storage;

    public Heap(){
        storage = new Vector<>();
    }

    public int parentOf(int i){
        return (int) Math.floor((i-1)/2);
    }

    public int leftChild(int i){
        return (2*i)+1;
    }

    public int rightChild(int i){
        return (2*i) + 2;
    }

    public T peek(){
        return storage.get(0);
    }

    public T remove(){
        T value = peek();

        storage.setElementAt(storage.get(storage.size() - 1), 0);
        storage.setSize(storage.size() - 1);

        if(storage.size() > 1)
            percolateDown(0);
        return value;
    }

    public void insert(T data){
        storage.add(data);
        percolateUp(storage.size()-1);
    }

    public abstract void percolateUp(int leaf);
    public abstract void percolateDown(int root);
}
