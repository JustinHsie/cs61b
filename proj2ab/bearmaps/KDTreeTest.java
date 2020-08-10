package bearmaps;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Test
    public void testNearest() {
        Random r = new Random(123);
        List<Point> list = new ArrayList<>();
        List<Point> nearestNaive = new ArrayList<>();
        List<Point> nearestKD = new ArrayList<>();

        for (int i = 0; i < 3000; i++) {
            double x = r.nextDouble();
            double y = r.nextDouble();

            list.add(new Point(x, y));
        }

        NaivePointSet naive = new NaivePointSet(list);
        KDTree kd = new KDTree(list);

        for (int j = 0; j < 300; j++) {
            double x = r.nextDouble();
            double y = r.nextDouble();

            nearestNaive.add(naive.nearest(x, y));
            nearestKD.add(kd.nearest(x, y));
        }

        assertEquals(nearestNaive, nearestKD);

    }
}
