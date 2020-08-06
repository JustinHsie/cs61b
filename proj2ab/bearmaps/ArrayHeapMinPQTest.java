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
    public void testGetSmallest() {
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        a.add(3, 3);
        a.add(2, 2);
        a.add(4, 4);
        assertEquals(2, (int) a.getSmallest());
    }

    @Test
    public void testSize() {
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        assertEquals(0, a.size());
        for (int i = 0; i < 365; i++) {
            a.add(i, i);
        }
        assertEquals(365, a.size());
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

        a.add("d", 4);
        a.changePriority("a", 1);
        assertEquals("a", a.getSmallest());
    }


    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
