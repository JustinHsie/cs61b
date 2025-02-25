package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {


    @Test
    public void testBasics() {
        Percolation test = new Percolation(5);
        assertFalse(test.isOpen(1, 3));
        test.open(1, 3);
        assertTrue(test.isOpen(1, 3));
        assertEquals(1, test.numberOfOpenSites);
    }
    @Test
    public void testUnion() {
        Percolation test = new Percolation(5);
        test.open(1, 3);
        test.open(0, 3);
        assertTrue(test.isFull(1, 3));
    }
    @Test
    public void testPercolates() {
        Percolation test = new Percolation(5);
        test.open(1, 3);
        test.open(0, 3);
        test.open(4, 3);
        assertFalse(test.percolates());
        test.open(3, 3);
        test.open(2, 3);
        assertTrue(test.percolates());
    }
}
