package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {
    static Percolation test = new Percolation(5);

    @Test
    public void testBasics() {
        assertFalse(test.isOpen(1, 3));
        test.open(1, 3);
        assertTrue(test.isOpen(1, 3));
        assertEquals(1, test.numberOfOpenSites);
    }
}
