package farhan.codinglab.TP2;

import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.function.Consumer;

public class TP2 {
    private static InputReader in;
    public static OutputStream outputStream = System.out;
    public static PrintWriter out = new PrintWriter(outputStream);
    public static BinaryTree pulau;
    public static LinkedList<Paket> paket = new LinkedList<>();
    public static int[] distance;

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);

        int P = in.nextInt();
        pulau = new BinaryTree(P);
        for (int i = 1; i < P + 1; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            pulau.insertLR(left, right, i);
        }

        distance = pulau.getDistance();

        int Q = in.nextInt();
        while (Q-- > 0) {
            int cmd = in.nextInt();
            if (cmd == 1) {
                String nama = in.next();
                int kodePrioritas = in.nextInt();
                int pulauTujuan = in.nextInt();
                paket.add(new Paket(nama, kodePrioritas, pulauTujuan,distance[pulauTujuan]));


            }
            if (cmd == 2) {
                String nama = in.next();
                Paket tmp = paket.getByName(nama);
                if (paket.remove(tmp))
                    out.println("BERHASIL");
                else
                    out.println("GAGAL");

            }

            if (cmd == 3) {
                int amount = in.nextInt();
                sendPackage(paket, amount);
            }

        }

        out.flush();

    }

    static void sendPackage(LinkedList<Paket> paket, int amount) {
        int amountToSend = Math.min(amount, paket.size);
        Paket[] paketToSend = new Paket[amountToSend];
        int counter = 0;
        for (Paket x : paket) {
            if (counter == amountToSend)
                break;

            paketToSend[counter] = x;
            paket.remove(x);
            counter++;
        }

        SortPaket.mergeSort(paketToSend);

        for (Paket x : paketToSend) {
            out.println(x.getNama());
        }


    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

}

class Paket implements Comparable<Paket> {
    String nama;
    int kodePrioritas;
    int pulauTujuan;
    int distance;


    public Paket(String nama, int kodePrioritas, int pulauTujuan, int distance) {
        this.nama = nama;
        this.kodePrioritas = kodePrioritas;
        this.pulauTujuan = pulauTujuan;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return this.nama + " " + this.distance;
    }

    @Override
    public int compareTo(Paket o) {
        return Integer.compare(this.kodePrioritas, o.kodePrioritas);
    }

    public String getNama() {
        return nama;
    }

}

class BinaryTree {
    private final Node root;
    private final Node[] pulau;
    private final int[] distance;

    public BinaryTree(int size) {
        root = new Node(1);
        pulau = new Node[size + 1];
        distance = new int[size + 1];
        for (int i = 1; i < size + 1; i++) {
            pulau[i] = null;
            distance[i] = 0;
        }
        pulau[0] = new Node(0);
        pulau[1] = root;
    }

    public int[] getDistance() {
        return this.distance;
    }

    public void insertLR(int left, int right, int parentNumber) {
        Node parent = search(parentNumber);
        Node newLeft = new Node(left);
        Node newRight = new Node(right);
        if (left != 0) {
            newLeft.parent = parent;
            pulau[left] = newLeft;
            parent.setLeft(newLeft);
        }
        if (right != 0) {
            newRight.parent = parent;
            pulau[right] = newRight;
            parent.setRight(newRight);
        }

        setDistance(newLeft, newRight);


    }


    public Node search(int data) {
        Node current = null;

        for (int i = 1; i < pulau.length; i++) {
            if (pulau[i] != null && pulau[i].getData() == data) {
                current = pulau[i];
            }

        }
        return current;
    }

    public void setDistance(Node node, Node node2) {
        Node tmp = node.parent;
        int dist = 0;
        while (tmp != null) {
            tmp = tmp.parent;
            dist++;
        }

        distance[node.getData()] = dist;
        distance[node2.getData()] = dist;
    }


}


class Node {
    Node left, right, parent;
    private final int data;

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }

}


class LinkedList<T extends Comparable<T>> implements Iterable<T> {

    NodeLL<T> base, last;
    int size;

    public LinkedList() {
        this.base = new NodeLL<>(null);
        last = base;
        size = 0;

    }

    public T getByName(String nama) {
        NodeLL<T> current = base.getNext();


        while (current != null) {
            if (current.getData() instanceof Paket)
                if (((Paket) current.getData()).getNama().equals(nama)) {
                    break;
                }
            current = current.getNext();
        }

        return current == null ? null : current.data;
    }

    public void add(T data) {
        NodeLL<T> start = base.next;
        NodeLL<T> afterBase = base.next;

        // Create new Node
        NodeLL<T> temp = new NodeLL<>(data);

        if (afterBase == null) {
            base.next = temp;

        }
        //https://www.geeksforgeeks.org/priority-queue-using-linked-list/
        // Special Case: The head of list has lesser
        // priority than new node. So insert new
        // node before head node and change head node.
        else if (afterBase.getData().compareTo(temp.data) > 0) {
            temp.next = base.next;
            base.next = temp;
        } else {
            // Traverse the list and find a
            // position to insert new node
            while (start.next != null &&
                    start.next.getData().compareTo(temp.data) <= 0) {
                start = start.next;
            }

            // Either at the ends of the list
            // or at required position
            temp.next = start.next;
            start.next = temp;
        }
        size++;
    }


    public boolean remove(T data) {
        NodeLL<T> current = base.next;
        NodeLL<T> prev = null;

        if (data == null)
            return false;
        if (current != null && current.data.equals(data)) {
            base.next = current.next;
            size--;
            return true;
        }

        while (current != null && !current.data.equals(data)) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        assert prev != null;
        if (current.equals(last)) {
            prev.next = null;
            last = prev;
        } else {
            prev.next = current.next;
        }
        size--;

        return true;
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

}


class NodeLL<T> {
    NodeLL<T> next;
    T data;

    public NodeLL(T data) {
        this.data = data;
    }


    public NodeLL<T> getNext() {
        return next;
    }

    public T getData() {
        return data;
    }


}

class LinkedListIterator<T extends Comparable<T>> implements Iterator<T> {
    NodeLL<T> current;
    LinkedList<T> dll;

    public LinkedListIterator(LinkedList<T> dll) {
        this.dll = dll;
        current = dll.base;
    }

    @Override
    public boolean hasNext() {
        return (current.next != null);
    }

    @Override
    public T next() {
        T data;
        current = current.getNext();

        data = current == dll.base ? current.next.getData() : current.getData();

        return data;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}


class SortPaket {
    public static void mergeSort(Paket[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    //awal dan akhir menandakan subarray-nya
    public static void mergeSort(Paket[] array, int awal, int akhir) {

        int length = (akhir - awal) + 1;
        int tengah = awal + (akhir - awal) / 2;

        if (length <= 1)
            return;

        //divide lalu rekursif ke kiri dan ke kanan
        mergeSort(array, awal, tengah);//kiri
        mergeSort(array, tengah + 1, akhir);//kanan

        merge(array, awal, tengah, akhir);// O(N)
    }

    public static void merge(Paket[] array, int awal, int tengah, int akhir) {
        int kiriLength = tengah - awal + 1;
        int kananLength = akhir - tengah;

        //copy dulu sub-array kiri dan kanan
        Paket[] arrayKiri = new Paket[kiriLength];
        Paket[] arrayKanan = new Paket[kananLength];
        for (int kr = 0, kiriAsli = awal; kr < kiriLength; kr++, kiriAsli++)
            arrayKiri[kr] = array[kiriAsli];
        for (int kn = 0, kananAsli = tengah + 1; kn < kananLength; kn++, kananAsli++)
            arrayKanan[kn] = array[kananAsli];

        //merge arrayKiri dan arrayKanan, hasilnya masukkan ke array asli
        //3 buah counter: i adalah indeks subarray yang terurut
        int kiri = 0, kanan = 0, i = awal;
        //compare-compare kiri dan kanan, siapa yang di-copy ke array asli
        while (i <= akhir && kiri <= kiriLength - 1 && kanan <= kananLength - 1) {
            Paket left = arrayKiri[kiri];
            Paket right = arrayKanan[kanan];
            if (left.distance < right.distance) {
                array[i++] = arrayKiri[kiri++];
            } else if (left.distance > right.distance) {
                array[i++] = arrayKanan[kanan++];
            } else if (left.pulauTujuan < right.pulauTujuan) {
                array[i++] = arrayKiri[kiri++];
            } else if (left.pulauTujuan > right.pulauTujuan) {
                array[i++] = arrayKanan[kanan++];
            } else if (left.nama.compareTo(right.nama) < 0) {
                array[i++] = arrayKiri[kiri++];
            } else {
                array[i++] = arrayKanan[kanan++];
            }


        }

        //copy sisa dari arrayKiri jika ada
        while (kiri <= kiriLength - 1)
            array[i++] = arrayKiri[kiri++];

        //copy sisa dari arrayKanan jika ada
        while (kanan <= kananLength - 1)
            array[i++] = arrayKanan[kanan++];
    }
}