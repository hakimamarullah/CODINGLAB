package com.codinglab.collections.binarytree;

public class BinaryTree<T extends Comparable<T>>{
    private Node<T> root;

    public void insert(T data){
        root = insert(data,root);
    }

    public Node<T> insert(T data, Node<T> current){
        if (current == null)
            current = new Node<>(data);
        if(data.compareTo((T) current.getData()) < 0)
            current.setLeft(insert(data,current.getLeft()));
        if(data.compareTo((T) current.getData()) > 0)
            current.setRight(insert(data,current.getRight()));
        else if(data.equals((T) current.getData()))
            return current;
        return current;

    }

    public Node<T> insertNonRec(T data){
        Node<T> current = root;
        if(root == null){
            root = new Node<>(data);
            return root;
        }

        Node<T> parent = root;
        while(current != null){
            parent = current;
            if(data.compareTo(current.getData()) == 0)
                return current;
            current = data.compareTo(current.getData()) > 0 ? current.getRight(): current.getLeft();
        }

        Node<T> toInsert = new Node<>(data);
        if(data.compareTo(parent.getData()) > 0)
            parent.setRight(toInsert);
        else
            parent.setLeft(toInsert);
        return toInsert;
    }

    int height(){
        return height(root);
    }

    int height(Node<T> node){
        if (node == null) return -1;
        return Math.max(height(node.getLeft()) + 1, height(node.getRight()) + 1);
    }

    public T findMin(){
        return findMin(root);
    }
    public T findMin(Node<T> current){
        if(current == null){
            return null;
        }

        while(current.getLeft() != null)
            current = current.getLeft();
        return (T) current.getData();
    }

    public Node<T> removeMin(){
        return removeMin(root);
    }

    private Node<T> removeMin(Node<T> current){
        if(current == null){
            return null;
        }

        else if(current.getLeft() != null){
            current.setLeft(removeMin(current.getLeft()));
            return current;
        }

        else
            return current.getRight();
    }

    public Node<T> remove(T data){
        return remove(data, root);
    }

    private Node<T> remove(T data, Node<T> current){
        if(current == null)
            return null;
        if(data.compareTo(current.getData()) < 0)
            current.setLeft(remove(data,current.getLeft()));
        else if(data.compareTo(current.getData()) > 0)
            current.setRight(remove(data,current.getRight()));
        else if(current.getLeft() != null && current.getRight() != null){
            //USE SUCCESSOR IN-ORDER
            current.setData(findMin(current.getRight()));
            current.setRight(removeMin(current.getRight()));
        }
        else{
            Node<T> tmp = current;
            current = current.getRight() != null ? current.getRight() : current.getLeft();
            if(tmp == root)
                root = current;

        }

        return current;
    }

    public T findMax(){
        return findMax(root);
    }
    public T findMax(Node<T> current){
        if(current == null){
            return null;
        }

        while(current.getRight() != null)
            current = current.getRight();
        return (T) current.getData();
    }


    public Node<T> search(T data){
        Node<T> current = this.root;
        while(current != null && current.getData().compareTo(data) != 0) {
            current = data.compareTo(current.getData()) > 0 ? current.getRight() : current.getLeft();
        }
        return current;
    }

    public void printInOrder(){
        if(root != null)
            root.printInOrder();
        System.out.println("");
    }

    public void printPostOrder(){
        if(root != null)
            root.postOrder();
        System.out.println("");
    }

    public void printPreOrder(){
        if(root != null)
            root.preOrder();
        System.out.println("");
    }

    public Node<T> getRoot(){
        return root;
    }


}