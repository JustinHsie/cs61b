package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Variable for capacity. */
    private int capacity;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* Pointer for iteration */
    private int pos;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Implements ArrayRingBufferIterator
     */
    private class ArrayRingBufferIterator implements Iterator {

        public ArrayRingBufferIterator() {
            pos = first;
        }
        public boolean hasNext() {
            return pos != last;
        }
        public T next() {
            T returnItem = rb[pos];
            pos = plusOne(pos);
            return returnItem;
        }
    }

    /**
     * Returns iterator
     */
    @Override
    public Iterator iterator() {
        return new ArrayRingBufferIterator();
    }
    /**
     * Returns size of buffer
     */
    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    private int plusOne(int index) {
        if (index + 1 > capacity - 1) {
            return 0;
        }
        return index + 1;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {

        if (fillCount == capacity) {
            throw new RuntimeException(("Ring buffer overflow"));
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {

        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        rb[first] = null;
        first = plusOne(first);
        pos = first;
        fillCount -= 1;

        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {

        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
