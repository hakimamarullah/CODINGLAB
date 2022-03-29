package com.codinglab.collections.heap;

public class MinHeap<T extends Comparable<T>> extends Heap<T>{
    @Override
    public void percolateUp(int leaf) {
        int parent = parentOf(leaf);

        //value yang tadi di leaf, kita simpan dulu ke temp
        T value = storage.get(leaf);
        int now = leaf;

        //traverse dari leaf ke atas, maksimal nanti sampai root
        while(now > 0 && storage.get(parent).compareTo(value) > 0){
            //data di parent digeser ke bawah
            storage.setElementAt(storage.get(parent), now);
            now = parent;
            parent = parentOf(now);
        }

        //sudah ketemu posisinya
        storage.setElementAt(value, now);
    }

    @Override
    public void percolateDown(int root) {
        T value = storage.get(root);

        //simpan heap size supaya mudah saat pengecekan berikutnya
        int heapSize = storage.size();

        int now = root;// now ini seperti pointer current

        while(now < heapSize){
            //mesti cari jalur percolate down nya, mana yang lebih kecil
            //dari anak kiri atau anak kanan

            int leastChildPos = leftChild(now);
            if(leastChildPos < heapSize){
                //cari anak kanan
                int rightChildPos = leastChildPos + 1;
                if(rightChildPos < heapSize
                        && storage.get(rightChildPos).compareTo(storage.get(leastChildPos)) < 0)
                    leastChildPos = rightChildPos;

                //di sini, kita sudah tau jalur percolate down nya ke kiri ataukah ke kanan, yaitu ke leastChildPos
                //compare si least tadi dengan value
                if(storage.get(leastChildPos).compareTo(value) < 0){
                    //kalo lebih kecil dari si value, maka geser ke atas
                    storage.setElementAt(storage.get(leastChildPos), now);
                    now = leastChildPos; // si now turun lagi
                }
                // kasus ketika percolate down nya berhenti sebelum mencapai leaf
                else{
                    storage.setElementAt(value, now);
                    return;
                }

            }
            //si now itu sudah merupakan leaf
            else{
                storage.setElementAt(value, now);
                return;
            }
        }
    }
}
