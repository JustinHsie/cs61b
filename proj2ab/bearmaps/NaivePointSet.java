package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> list;

    public NaivePointSet(List<Point> points) {
        list = new ArrayList<>(points.size());
        list.addAll(points);
    }
    public Point nearest(double x, double y) {
        Point o = new Point(x, y);
        Point best = list.get(0);

        for (Point point : list) {
            double distNew = Math.sqrt(Point.distance(point, o));
            double distBest = Math.sqrt(Point.distance(best, o));
            if (distNew < distBest) {
                best = point;
            }
        }
        return best;
    }


    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        System.out.println(ret.getX()); // evaluates to 3.3
        System.out.println(ret.getY()); // evaluates to 4.4
    }
}
