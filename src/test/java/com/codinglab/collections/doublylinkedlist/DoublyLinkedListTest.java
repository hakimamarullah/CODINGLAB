package com.codinglab.collections.doublylinkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> dll;
    @BeforeEach
    void setUp() {
        dll = new DoublyLinkedList<>();
    }

    @Test
    void getWithIndexShouldReturnRightElement() {
        dll.add(10);
        dll.add(20);
        dll.add(30);

        assertEquals(20,dll.getByIndex(1));
    }

    @Test
    void getWithIndexShouldThrowOutOfBoundException() {
        dll.add(10);
        dll.add(20);
        dll.add(30);

        IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class,
                ()->dll.getByIndex(10));
        IndexOutOfBoundsException ex2 = assertThrows(IndexOutOfBoundsException.class,
                ()->dll.getByIndex(3));
        assertEquals("Index out of bound, Index: 10, Size: 3", ex.getMessage());
        assertEquals("Index out of bound, Index: 3, Size: 3", ex2.getMessage());
    }

    @Test
    void addLast() {
        dll.add(10);
        dll.add(20);
        dll.add(30);

        assertEquals(10,dll.getByIndex(0));
        assertEquals(20,dll.getByIndex(1));
        assertEquals(30,dll.getByIndex(2));

    }

    @Test
    void addFirst() {
        dll.addFirst(10);
        dll.addFirst(20);
        dll.addFirst(30);

        assertEquals(30,dll.getByIndex(0));
        assertEquals(20,dll.getByIndex(1));
        assertEquals(10,dll.getByIndex(2));
    }

    @Test
    void removeFirstElement() {
        dll.addFirst(10);
        dll.addFirst(20);
        dll.addFirst(30);

        dll.remove(10);
        assertEquals(null,dll.get(10));
        assertEquals(2,dll.getSize());
    }

    @Test
    void removeSecondElement() {
        dll.addFirst(10);
        dll.addFirst(20);
        dll.addFirst(30);

        dll.remove(20);
        assertEquals(null,dll.get(20));
        assertEquals(2,dll.getSize());
    }

    @Test
    void removeLastElement() {
        dll.addFirst(10);
        dll.addFirst(20);
        dll.addFirst(30);

        dll.remove(30);
        assertEquals(null,dll.get(30));
        assertEquals(2,dll.getSize());
    }



    @Test
    void getSize() {
        for(int i=0; i<1000; i++){
            dll.addFirst(i);
        }
        assertEquals(1000,dll.getSize());
    }

    @Test
    void testClear(){
        for(int i=0; i<1000; i++){
            dll.addFirst(i);
        }
        dll.clear();
        assertEquals(0,dll.getSize());
    }

    @Test
    void reverseIterate() {
        dll.addFirst(10);
        dll.addFirst(20);
        dll.addFirst(30);

        ArrayList<Integer> expected = new ArrayList<>();

        Node<Integer> curr = dll.getLast();

        while(curr != null){
            expected.add(curr.getData());
            curr = curr.prev();
        }

        assertEquals(10,expected.get(0));
        assertEquals(20,expected.get(1));
        assertEquals(30,expected.get(2));
    }
}