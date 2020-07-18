package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    /**
     * Returns Iterator with next/hasNext methods
     */
    Iterator iterator();
    /**
     * Returns size of buffer
     */
    int capacity();

    /**
     * Returns number of items currently in buffer
     */
    int fillCount();

    /**
     * Add item x to the end
     */
    void enqueue(T x);

    /**
     * Delete and return item from the front
     */
    T dequeue();
    /**
     * Return (but do not delete) item from the front
     */
    T peek();

    /**
     * Is the buffer empty? (fillCount equals zero)
     */
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Is the buffer full? (fillCount is same as capacity)
     */
    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }
}
