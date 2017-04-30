/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

/**
 * Implements ADT Heap
 *
 * @author Cate Sheller
 * @version 23 November 2012
 */
public class Heap<E> {

    // invariant: data contains the data in an array, arranged according
    // to heap rules; numItems keeps track of the number of items in the heap
    private Object[] data;
    private int numItems;
    public static int DEFAULT_SIZE = 31;
    private E hData;
    private Heap<E> leftData, rightData;

    /**
     * Default constructor: creates empty heap
     */
    public Heap() {
        data = new Object[DEFAULT_SIZE];
        numItems = 0;
    }

    /**
     * Constructor: sets heap data for traversal
     */
    public Heap(E initialData, Heap<E> initialLeft, Heap<E> initialRight) {
        hData = initialData;
        leftData = initialLeft;
        rightData = initialRight;
    }

    /**
     * Constructor: builds heap from array of Objects
     */
    public Heap(E... items) {
        data = new Object[items.length];
        System.arraycopy(items, 0, data, 0, items.length);
        numItems = items.length;
        data = buildHeap(data);
    }

    /**
     * Courtesy method to export heaps to the wider world
     *
     * @param array: array of unsorted data
     * @return: same array, arranged as a heap
     */
    public static Object[] buildHeap(Object[] array) {
        Object[] heapData = new Object[array.length];
        Heap shaper = new Heap();
        for (int x = 0; x < array.length; x++) {
            shaper.addEntry(array[x]);
        }
        for (int y = 0; y < array.length; y++) {
            heapData[y] = shaper.getTop();
        }
        return heapData;
    }

    /**
     * @param n: index of current node
     * @return: index of current node's parent (if it exists)
     */
    public int parent(int n) {
        return (n - 1) / 2;
    }

    /**
     * @param n: index of current node
     * @return: index of current node's left child (if it exists)
     */
    public int leftChild(int n) {
        return 2 * n + 1;
    }

    /**
     * @param n: index of current node
     * @return: index of current node's right child (if it exists)
     */
    public int rightChild(int n) {
        return 2 * n + 2;
    }

    /**
     * @param entry: item to be added to Heap Adds data item to heap and adjusts
     * tree as necessary to maintain heap condition; resizes array if necessary
     * to accommodate entry
     */

    public void inorderPrint() {
        if (leftData != null) {
            leftData.inorderPrint();
        }
        System.out.println(data);
        if (rightData != null) {
            rightData.inorderPrint();
        }
    }

    public void preorderPrint() {
        System.out.println(data);
        if (leftData != null) {
            leftData.preorderPrint();
        }
        if (rightData != null) {
            rightData.preorderPrint();
        }
    }

    public void postorderPrint() {
        if (leftData != null) {
            leftData.postorderPrint();
        }
        if (rightData != null) {
            rightData.postorderPrint();
        }
        System.out.println(data);
    }

    public void addEntry(E entry) {
        if (numItems == data.length) {
            Object[] temp = new Object[data.length * 2];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
        }
        data[numItems] = entry;
        reHeapUp(numItems);
        numItems++;
    }

    /**
     * Restores heap condition when addition of new data results in invalid heap
     *
     * @param n: index of item just added
     */
    public void reHeapUp(int n) {
        int x = n;
        Comparable d1 = (Comparable) data[x];
        Comparable d2 = (Comparable) data[parent(x)];

        while (x > 0 && d1.compareTo(d2) > 0) {
            E tmp = (E) data[x];
            data[x] = data[parent(x)];
            data[parent(x)] = tmp;
            x = parent(x);
            d1 = (Comparable) data[x];
            d2 = (Comparable) data[parent(x)];
        }
    }

    /**
     * Returns value from top of heap, then restores heap condition
     *
     * @return: item at top of heap
     */
    public E getTop() {
        E value = (E) data[0]; // save return value
        numItems--;
        data[0] = data[numItems];  // swap top & bottom
        reHeapDown(numItems); // restore heap
        return value;
    }

    /**
     * Restores heap condition after top item has been removed
     */
    public void reHeapDown(int n) {
        int current = 0, bigChild;
        boolean heapOK = false;
        Comparable d1, d2;
        while ((!heapOK) && (leftChild(current) < n)) {
            if (rightChild(current) >= n) {
                bigChild = leftChild(current);
            } else {
                d1 = (Comparable) data[leftChild(current)];
                d2 = (Comparable) data[rightChild(current)];
                if (d1.compareTo(d2) > 0) {
                    bigChild = leftChild(current);
                } else {
                    bigChild = rightChild(current);
                }
            }
            d1 = (Comparable) data[current];
            d2 = (Comparable) data[bigChild];
            if (d1.compareTo(d2) < 0) {
                E tmp = (E) data[current];
                data[current] = data[bigChild];
                data[bigChild] = tmp;
                current = bigChild;
            } else {
                heapOK = true;
            }
        } // end of while loop
    } // end of method
}
