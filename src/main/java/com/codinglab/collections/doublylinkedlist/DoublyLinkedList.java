package com.codinglab.collections.doublylinkedlist;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


public class DoublyLinkedList<T> implements Iterable<T> {

    private NodeLL<T> base, last;
    private int size;

    public DoublyLinkedList() {
        this.base = new NodeLL<T>(null);
        last = base;
        size = 0;

    }

    public T getByIndex(int index) throws IndexOutOfBoundsException {
        int counter = 0;
        NodeLL<T> current = base.getNext();

        if (index >= size) {
            throw new IndexOutOfBoundsException(String
                    .format("Index out of bound, Index: %d, Size: %d",index,size));
        }

        while (counter < size && counter != index && current != null) {
            current = current.getNext();
            counter++;
        }

        return current != null ? current.getData() : null;
    }

    public void add(T data) {
        NodeLL<T> tmp = new NodeLL<T>(data);
        if (base == last) {
            base.setNext(tmp);
            tmp.setPrev(base);
        } else {
            last.setNext(tmp);
            tmp.setPrev(last);
        }
        last = tmp;
        increaseSize();
    }

    public NodeLL<T> get(T data) {
        int counter = 0;
        NodeLL<T> current = base.getNext();

        while (counter < size && current != null && !current.getData().equals(data)) {
            current = current.getNext();
            counter++;
        }

        return current;
    }

    public T remove(T data) throws NoSuchElementException {
        NodeLL<T> target = get(data);

        if (target == null) {
            throw new NoSuchElementException();
        }

        //CASE 1: DELETE FIRST ENTRY
        if (target == base.getNext()) {
            base.setNext(base.getNext().getNext());
            base.getNext().setPrev(base);
        }

        //CASE 2: DELETE LAST ENTRY
        else if (target == last) {
            last = target.getPrev();
            last.setNext(null);
        }

        //CASE 3: DELETE INTERMEDIATE NODE
        else if (target.getNext() != null && target.getPrev() != null) {
            NodeLL<T> tmp = target.getNext();

            target.getPrev().setNext(tmp);
            tmp.setPrev(target.getPrev());
        }

        decreaseSize();
        return target.getData();
    }

    public void clear(){
        base.setNext(null);
        last = base;
        size=0;
    }

    public void addFirst(T data) {
        NodeLL<T> tmp = new NodeLL<T>(data);
        if (base == last) {
            base.setNext(tmp);
            tmp.setPrev(base);
            last = tmp;
        } else {
            tmp.setNext(base.getNext());
            base.getNext().setPrev(tmp);
            tmp.setPrev(base);
            if (last.getPrev() == base) {
                last.setPrev(tmp);
            }
            base.setNext(tmp);
        }
        increaseSize();
    }

    private void increaseSize() {
        this.size++;
    }

    private void decreaseSize() {
        this.size--;
    }

    public NodeLL<T> getBase() {
        return base;
    }


    public NodeLL<T> getLast() {
        return last;
    }


    public int getSize() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoublyLinkedListIterator<T>(this);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    public NodeLL<T> getFirst() {
        return base.getNext();
    }
}
