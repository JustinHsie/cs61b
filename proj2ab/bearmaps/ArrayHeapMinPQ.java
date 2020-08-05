package bearmaps;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private T[] minHeap; // store items at indices 1 to n
    private int numItems; // number of items on priority queue

    /**
     * Initializes an empty priority queue with given initial capacity
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public ArrayHeapMinPQ(int initCapacity) {
        minHeap = (T[]) new Object[initCapacity + 1];
        numItems = 0;
    }

    /**
     * Initializes an empty priority queue
     */
    public ArrayHeapMinPQ() {
        this(1);
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {}

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {return false;}

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {return null;}

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {return null;}

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {return 0;}

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {}
}
