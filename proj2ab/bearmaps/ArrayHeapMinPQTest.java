package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void testGenerics() {
        ArrayHeapMinPQ<Integer> a = new ArrayHeapMinPQ<>();
        assertEquals(0, a.size());
        ArrayHeapMinPQ<Integer> b = new ArrayHeapMinPQ<>(10);
        assertEquals(0, b.size());
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
