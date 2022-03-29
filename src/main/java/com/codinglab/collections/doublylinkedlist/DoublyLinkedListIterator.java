package com.codinglab.collections.doublylinkedlist;

import java.util.Iterator;

public class DoublyLinkedListIterator<T> implements Iterator<T> {
    Node<T> current;
    DoublyLinkedList<T> dll;

    public DoublyLinkedListIterator(DoublyLinkedList<T> dll){
        this.dll = dll;
        current = dll.getBase();
    }
    @Override
    public boolean hasNext() {
        if(current == null){
            return false;
        }

        else if(current.getNext() == null && current != dll.getLast()){
            return false;
        }
        return true;
    }

    @Override
    public T next() {
        if(current == dll.getBase()){
            if(current.getNext() != null)
                current = current.getNext();
        }
        T data = (T) current.getData();
        current = current.getNext();

        return data;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
