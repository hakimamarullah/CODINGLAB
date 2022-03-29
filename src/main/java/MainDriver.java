import com.codinglab.collections.binarytree.BinaryTree;
import com.codinglab.collections.doublylinkedlist.DoublyLinkedList;

import java.util.Optional;
import java.util.Random;

public class MainDriver {
    public static void main(String[] args){
       try{
           DLLRunner();
       }catch (Exception e){
           e.printStackTrace();
       }
    }
    static void DLLRunner(){
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        Random rand = new Random();

        for(int i=0; i<4; i++){
           dll.addFirst(i);
        }

        dll.remove(3);
        dll.addFirst(200);
        dll.add(5);
        dll.clear();
        dll.add(7);
        dll.addFirst(10);
        System.out.println(dll.getByIndex(1));
        dll.forEach(item -> System.out.print(item +" "));

    }
    static void BinTreeRunner(){
        BinaryTree<Character> binTree = new BinaryTree<>();

        binTree.insertNonRec('D');
        binTree.insertNonRec('A');
        binTree.insertNonRec('E');
        binTree.insert('D');
        binTree.printInOrder();
        binTree.printPreOrder();
        binTree.printPostOrder();

        searchInTree(binTree,'D');


        System.out.println("MIN "+ binTree.findMin());
        System.out.println("MAX "+ binTree.findMax());
    }

    static <T extends Comparable<T>> void searchInTree(BinaryTree<T> binTree, T key){
        Optional.ofNullable(binTree.search(key))
                .ifPresentOrElse(
                        node-> System.out.println("FIND " + node.toString()),
                        ()-> System.out.println("NOT FOUND"));
    }
}
