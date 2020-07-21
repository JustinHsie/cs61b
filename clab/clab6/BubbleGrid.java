import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BubbleGrid {
    private int[][] grid;
    WeightedQuickUnionUF uf;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    private int checkBelow(int row, int col) {
        if (row + 1 < grid.length) {
            if (grid[row + 1][col] == 1) {
                int p = row * grid[0].length + col;
                int q = row * grid[0].length + grid[0].length + col;
                uf.union(p, q);
                return findStuck(row + 1, col);
            }
        }
        return 0;
    }

    private int checkLeft(int row, int col) {
        // Ignore topmost row
        if (row == 0) {
            return 0;
        }
        if (col - 1 >= 0) {
            if (grid[row][col - 1] == 1) {
                int p = row * grid[0].length + col;
                int q = row * grid[0].length + col - 1;
                if (uf.find(p) != uf.find(q)) {
                    uf.union(p, q);
                    return findStuck(row, col - 1);
                }
            }
        }
        return 0;
    }

    private int checkRight(int row, int col) {
        // Ignore topmost row
        if (row == 0) {
            return 0;
        }
        if (col + 1 < grid[0].length) {
            if (grid[row][col + 1] == 1) {
                int p = row * grid[0].length + col;
                int q = row * grid[0].length + col + 1;
                if (uf.find(p) != uf.find(q)) {
                    uf.union(p, q);
                    return findStuck(row, col + 1);
                }
            }
        }
        return 0;
    }

    private int findStuck(int row, int col) {
        // Confine to grid
        if (row > grid.length - 1 || col < 0 || col > grid[0].length - 1) {
            return 0;
        }
        checkBelow(row, col);
        checkLeft(row, col);
        checkRight(row, col);
        return 0;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        uf = new WeightedQuickUnionUF(grid.length * grid[0].length);
        int row = 0;
        for (int col = 0; col < grid[0].length; col++) {
            if (grid[row][col] == 1) {
                findStuck(row, col);
            }
        }

        int[] popped = new int[darts.length];
        for (int i = 0; i < darts.length; i ++ ) {
            int target = darts[i][0] * grid[0].length + darts[i][1];
            int count = 0;
            for (int bubbles = 0; bubbles < grid.length * grid[0].length; bubbles++) {
                if (uf.find(bubbles) == uf.find(target) && bubbles > target) {
                    count++;
                }
            }
            popped[i] = count;
        }
        return popped;
    }
}
