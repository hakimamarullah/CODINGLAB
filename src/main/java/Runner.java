import com.codinglab.collections.binarytree.BinaryTree;
import com.codinglab.collections.doublylinkedlist.DoublyLinkedList;
import com.codinglab.collections.heap.Heap;
import com.codinglab.collections.heap.MaxHeap;
import com.codinglab.collections.heap.MinHeap;
import com.codinglab.encryption.des.DES;

import java.util.Locale;
import java.util.Optional;
//
public class Runner {
    public static void main(String[] args) {
        try {
//            maxHeapRunner();
//            minHeapRunner();
            DESRunner();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void DLLRunner() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        for (int i = 0; i < 4; i++) {
            dll.addFirst(i);
        }

        dll.remove(3);
        dll.addFirst(200);
        dll.add(5);
        dll.clear();
        dll.add(7);
        dll.addFirst(10);
        System.out.println(dll.getByIndex(1));
        dll.forEach(item -> System.out.print(item + " "));

    }

    static void DESRunner(){
        DES des = new DES();
        String hex = "4CC5D29C563B";
        String bin = des.hexToBin(hex);

//        String expand = des.expandR(bin);
//        System.out.println(des.binToHex(expand));
        String sboxed = des.transformRXORKeySBox(bin);
        System.out.println(sboxed.toUpperCase(Locale.ROOT));
        System.out.println(des.binToHex(des.finalStageFPermutation(des.hexToBin(sboxed))));


    }

    static void binTreeRunner() {
        BinaryTree<Character> binTree = new BinaryTree<>();

        binTree.insertNonRec('D');
        binTree.insertNonRec('A');
        binTree.insertNonRec('E');
        binTree.insert('D');
        binTree.printInOrder();
        binTree.printPreOrder();
        binTree.printPostOrder();

        searchInTree(binTree, 'D');


        System.out.println("MIN " + binTree.findMin());
        System.out.println("MAX " + binTree.findMax());
    }

    static void maxHeapRunner() {
        System.out.println("Run MAX HEAP RUNNER");
        Heap<Integer> maxHeap = new MaxHeap<>();
        heapInsertRemoveAndPeek(maxHeap);
        System.out.println("END MAX HEAP RUNNER");
    }

    static void heapInsertRemoveAndPeek(Heap heap) {
        int[] input = new int[]{100, 50, 40, 120, -5, 45, 30};
        for (int i : input)
            heap.insert(i);
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        heap.insert(99);
        System.out.println(heap.peek());
    }

    static void minHeapRunner() {
        System.out.println("Run MIN HEAP RUNNER");
        Heap<Integer> minHeap = new MinHeap<>();
        heapInsertRemoveAndPeek(minHeap);
        System.out.println("END MIN HEAP RUNNER");
    }

    static <T extends Comparable<T>> void searchInTree(BinaryTree<T> binTree, T key) {
        Optional.ofNullable(binTree.search(key))
                .ifPresentOrElse(
                        node -> System.out.println("FIND " + node.toString()),
                        () -> System.out.println("NOT FOUND"));
    }
}
