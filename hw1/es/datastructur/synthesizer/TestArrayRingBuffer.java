package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(4);
        Iterator seer = arb.iterator();
        assertTrue(arb.isEmpty());

        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        assertFalse(arb.isFull());

        arb.enqueue(-3.1);
        assertTrue(arb.isFull());

        assertEquals(9.3, arb.dequeue(), 0.01);
        assertEquals(15.1, arb.peek(), 0.01);

        seer.hasNext();
        assertTrue(seer.hasNext());
        assertEquals(15.1, seer.next());

        for (double i : arb) {
            System.out.println(i);
        }

    }
}
