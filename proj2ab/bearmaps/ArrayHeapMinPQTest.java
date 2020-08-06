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
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        a.add(1, 1);
        a.add(2, 2);
        a.add(3, 3);

        assertEquals(1, (int) a.getSmallest());
        a.add(4, 1);
        assertEquals(4, (int) a.getSmallest());
    }


    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
