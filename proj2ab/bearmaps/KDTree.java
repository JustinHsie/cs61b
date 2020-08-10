package bearmaps;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class KDTree {
    private HashSet<Point> points;
    private Node root;
    int depth;

    public KDTree(List<Point> points) {
        this.points = new HashSet<>(points);
        for (Point xy : points) {
            depth = 0;
            root = put(root, xy);
        }
    }

    private Node put(Node node, Point xy) {
        if (node == null) {
            return new Node(xy);
        }

        int cmp;
        if (depth % 2 == 0) {
            cmp = Double.compare(xy.getX(), node.xy.getX());
        }
        else {
            cmp = Double.compare(xy.getY(), node.xy.getY());
        }

        depth++;
        if (cmp < 0) {
            node.left = put(node.left, xy);
        }
        else {
            node.right = put(node.right, xy);
        }
        return node;
    }

    private class Node {
        Point xy;
        Node left, right;

        public Node(Point p) {
            xy = p;
        }
    }

    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = nearest(root, goal, root);
        return best.xy;
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) return best;
        if (Point.distance(n.xy, goal) < Point.distance(best.xy, goal)) {
            best = n;
        }
        best = nearest(n.left, goal, best);
        best = nearest(n.right, goal, best);
        return best;
    }
}
