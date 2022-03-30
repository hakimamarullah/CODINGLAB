package com.codinglab.collections.binarytree;

public class Node<T extends Comparable<T>>{
    private Node<T> left,right;
    private T data;

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }

    public Node(T data){
        this.data = data;
    }

    public void printInOrder(){
        if(left !=null)
            left.printInOrder();

        System.out.print(data + " ");

        if(right != null)
            right.printInOrder();
    }

    public void preOrder(){
        System.out.print(data + " ");
        if(left !=null)
            left.preOrder();

        if(right != null)
            right.preOrder();
    }

    public void postOrder(){
        if(left !=null)
            left.postOrder();

        if(right != null)
            right.postOrder();
        System.out.print(data + " ");
    }

    public int degree1() {
        int count = left != null ^ right != null ? 1 : 0;
        if (left != null) count += left.degree1();
        if (right != null) count += right.degree1();
        return count;
    }

    @Override
    public String toString(){
        return String.valueOf(this.data);
    }

}