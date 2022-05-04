package com.codinglab.collections.doublylinkedlist;


public class NodeLL<T> {
    private NodeLL<T> prev, next;
    private T data;

    public NodeLL(T data){
        this.data = data;
    }

    public NodeLL<T> getPrev() {
        return prev;
    }

    public void setPrev(NodeLL<T> prev) {
        this.prev = prev;
    }

    public NodeLL<T> getNext() {
        return next;
    }

    public void setNext(NodeLL<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeLL<T> next() {
        return this.next;
    }

    public NodeLL<T> prev() {
        return this.prev;
    }
}
