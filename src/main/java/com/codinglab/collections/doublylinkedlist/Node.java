package com.codinglab.collections.doublylinkedlist;


public class Node<T> {
    private Node<T> prev, next;
    private T data;

    public Node(T data){
        this.data = data;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> next() {
        return this.next;
    }

    public Node<T> prev() {
        return this.prev;
    }
}
