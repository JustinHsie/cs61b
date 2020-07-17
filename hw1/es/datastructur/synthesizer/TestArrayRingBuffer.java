package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());

        arb.enqueue(9.3);
        arb.enqueue(15.1);
        arb.enqueue(31.2);
        assertFalse(arb.isFull());

        arb.enqueue(-3.1);
        assertTrue(arb.isFull());

        assertEquals(9.3, arb.dequeue());
        assertEquals(15.1, arb.peek());


    }
}
