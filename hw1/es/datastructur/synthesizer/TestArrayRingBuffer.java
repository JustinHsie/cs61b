package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testMethods() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(4);
        assertTrue(arb.isEmpty());

        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        assertFalse(arb.isFull());

        arb.enqueue(-3.1);
        assertTrue(arb.isFull());

        assertEquals(9.3, arb.dequeue(), 0.01);
        assertEquals(15.1, arb.peek(), 0.01);
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(4);
        Iterator seer = arb.iterator();

        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        arb.enqueue(-3.1);
        arb.dequeue();

        assertTrue(seer.hasNext());
        assertEquals(15.1, seer.next());

        String actual = "";
        for (double i : arb) {
            actual = actual + i + " ";
        }
        assertEquals("15.1 31.2 -3.1 ", actual);
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(3);
        ArrayRingBuffer<Double> other = new ArrayRingBuffer<>(3);

        arb.enqueue(9.3);
        arb.enqueue(31.2);
        arb.enqueue(15.1);

        other.enqueue(9.3);
        other.enqueue(31.2);

        assertFalse(arb.equals(other));

        other.enqueue(15.1);
        arb.equals(other);
        assertTrue(arb.equals(other));
    }

}
