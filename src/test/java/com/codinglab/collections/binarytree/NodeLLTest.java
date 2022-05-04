package com.codinglab.collections.binarytree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class NodeLLTest {

    Node<Integer> node;
    @BeforeEach
    void setUp(){
        node = new Node<>(10);
        node.setLeft(new Node<Integer>(9));
        node.setRight(new Node<>(11));
    }

    @Test
    void getLeft() {
        assertEquals(9,node.getLeft().getData());
    }

    @Test
    void getRight() {
        assertEquals(11,node.getRight().getData());
    }

    @Test
    void getData() {
        assertEquals(10,node.getData());
    }
}