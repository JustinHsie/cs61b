package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.List;

public class KDTreeTest {
    @Test
    public void testPut() {
        Point a = new Point(2, 3);
        Point b = new Point(4, 2);
        Point c = new Point(4, 5);
        Point d = new Point(3, 3);
        Point e = new Point(1, 5);
        Point f = new Point(4, 4);

        KDTree tree = new KDTree(List.of(a, b, c, d, e, f));
    }
}
