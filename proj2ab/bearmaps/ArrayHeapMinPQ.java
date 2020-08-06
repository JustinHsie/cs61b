package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayHeapMinPQ.PriorityNode<T>[] minHeap; // store items at indices 1 to n
    private int numItems; // number of items on priority queue
    private HashMap<T, Double> itemSet;

    /**
     * Initializes an empty priority queue with given initial capacity
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public ArrayHeapMinPQ(int initCapacity) {
        minHeap = (PriorityNode<T>[]) new PriorityNode[initCapacity + 1];
        numItems = 0;
        itemSet = new HashMap<>(numItems);
    }

    /**
     * Initializes an empty priority queue
     */
    public ArrayHeapMinPQ() {
        this(1);
    }

    /**
     * @source Josh Hug
     */
    private static class PriorityNode<T> implements Comparable<ArrayHeapMinPQ.PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(ArrayHeapMinPQ.PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((ArrayHeapMinPQ.PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already present");
        }
        // Resize if necessary
        else if (numItems == minHeap.length - 1) {
            resize(2 * minHeap.length);
        }
        minHeap[++numItems] = new PriorityNode<>(item, priority);
        itemSet.put(item, priority);
        swim(numItems);
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= numItems) {
            int j = 2*k;
            if (j < numItems && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return (minHeap[i].compareTo(minHeap[j])) >= 0;
    }

    private void exch(int i, int j) {
        PriorityNode<T> swap = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = swap;
    }


    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return itemSet.containsKey(item);
    }


    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("Priority Queue is empty");
        }
        return minHeap[1].item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("Priority Queue is empty");
        }
        PriorityNode<T> min = minHeap[1];
        itemSet.remove(min.item);
        exch(1, numItems--);
        sink(1);

        minHeap[numItems + 1] = null;
        if ((numItems > 0) && (numItems == (minHeap.length - 1) / 4)) resize(minHeap.length / 2);
        return min.item;
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return numItems;
    }

    private void resize(int capacity) {
        ArrayHeapMinPQ.PriorityNode<T>[] temp = (PriorityNode<T>[]) new PriorityNode[capacity];
        for (int i = 1; i <= numItems; i++) {
            temp[i] = minHeap[i];
        }
        minHeap = temp;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {}
}
