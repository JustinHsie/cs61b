import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    @Test
    public void testMethods() {
        UnionFind ds = new UnionFind(10);
        ds.union(4, 5);
        assertEquals(5, ds.parent(4));
    }

    @Test
    public void testConnected() {
        UnionFind ds = new UnionFind(10);
        ds.union(4, 5);
        ds.union(3, 4);
        ds.union(6, 7);
        ds.union(1, 2);
        ds.union(2, 4);

        assertFalse(ds.connected(7, 4));
        assertTrue(ds.connected(1, 5));
        assertEquals(5, ds.find(2));
    }

    @Test
    public void testPathCompression() {
        UnionFind ds = new UnionFind(10);
        ds.union(4, 5);
        ds.union(3, 4);
        ds.union(6, 7);
        ds.union(1, 2);
        ds.union(2, 4);
        ds.union(5, 7);

        ds.find(1);
        assertEquals(7, ds.parent(1));
        assertEquals(7, ds.parent(2));
    }
}
