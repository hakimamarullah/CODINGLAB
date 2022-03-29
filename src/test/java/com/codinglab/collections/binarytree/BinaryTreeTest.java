package com.codinglab.collections.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {

    private BinaryTree<Integer> binTree;
    @BeforeEach
    public void setUp(){
        binTree = new BinaryTree<>();

    }
    @Test
    void insert() {
        binTree.insert(40);
        binTree.insert(25);
        binTree.insert(78);
        binTree.insert(10);
        binTree.insert(32);

        assertEquals(2,binTree.height());
    }

    @Test
    void findMin() {
        binTree.insert(40);
        binTree.insert(25);
        binTree.insert(78);
        binTree.insert(10);
        binTree.insert(32);
        assertEquals(10, binTree.findMin());
    }

    @Test
    void findMax() {
        binTree.insert(40);
        binTree.insert(25);
        binTree.insert(78);
        binTree.insert(10);
        binTree.insert(32);
        assertEquals(78, binTree.findMax());
    }

    @Test
    void removeRootWithTwoChild(){
        binTree.insert(40);
        binTree.insert(30);
        binTree.insert(50);

        binTree.remove(40);
        assertEquals(1,binTree.height());
    }

    @Test
    void removeWithOneChild(){
        binTree.insert(40);
        binTree.insert(30);
        binTree.insert(50);
        binTree.insert(60);
        binTree.insert(55);

        binTree.remove(50);

        assertEquals(2,binTree.height());

    }

    @Test
    void searchShouldReturnNullIfNotPresent(){
        for(int i=1; i<10000; i++){
            int tmp = 10_000 - i;
            if(tmp != 977)
                binTree.insert(tmp);
        }
        assertEquals(null,binTree.search(977));
    }

    @Test
    void searchShouldReturnTheNodeIfPresent(){
        for(int i=1; i<10_0000; i++){
           binTree.insertNonRec(10_0000-i);
        }
        assertEquals(1,binTree.search(1).getData());

    }
}