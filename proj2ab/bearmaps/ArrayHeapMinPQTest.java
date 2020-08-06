package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testGenerics() {
        try {
            ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
            ArrayHeapMinPQ<String> b = new ArrayHeapMinPQ<>();
        } catch (Exception e) {
            fail();
        }
    }

    // Assumes getSmallest works
    @Test
    public void testAdd() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        a.add("a", 1);
        a.add("b", 2);
        a.add("c", 3);

        assertEquals("a", a.getSmallest());
        a.add("d", 1);
        assertEquals("d", a.getSmallest());
    }

    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Character> a = new ArrayHeapMinPQ<>();
        a.add('a', 1);
        a.add('b', 2);
        a.add('c', 3);

        assertEquals(3, a.size());

        a.removeSmallest();

        assertEquals('b', (char) a.getSmallest());
        assertEquals(2, a.size());
    }

    @Test
    public void testContains() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        a.add("a", 1);
        a.add("b", 2);
        a.add("c", 3);

        assertTrue(a.contains("a"));

        a.removeSmallest();

        assertFalse(a.contains("a"));
    }

    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        a.add("a", 1);
        a.add("b", 2);
        a.add("c", 3);

        a.changePriority("a", 4);
        assertEquals("b", a.getSmallest());
        a.changePriority("c", 1);
        assertEquals("c", a.getSmallest());
    }


    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
