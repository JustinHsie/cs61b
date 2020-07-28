package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {

    WeightedQuickUnionUF grid;
    boolean[][] sites;
    int N;
    int numberOfOpenSites = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IndexOutOfBoundsException("N must be greater than 0");
        }
        this.N = N;
        grid = new WeightedQuickUnionUF(N * N + 2);
        sites = new boolean[N][N];
    }

    // converts array location to grid location
    private int rcTo1D(int row, int col) {
        return row * N + col;
    }

    // checks if neighbors are open and unions if they are
    private void checkNeighbor(int row, int col) {
        // upper neighbor
        if (isOpen(row - 1, col) && row - 1 >= 0) {
            grid.union(rcTo1D(row, col), rcTo1D(row - 1, col));
        }
        // left neighbor
        if (isOpen(row, col - 1) && col - 1 >= 0) {
            grid.union(rcTo1D(row, col), rcTo1D(row, col - 1));
        }
        // bottom neighbor
        if (isOpen(row + 1, col) && row + 1 <= N - 1) {
            grid.union(rcTo1D(row, col), rcTo1D(row + 1, col));
        }
        // right neighbor
        if (isOpen(row, col + 1) && col + 1 <= N - 1) {
            grid.union(rcTo1D(row, col), rcTo1D(row, col + 1));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row >= N - 1 || col <= 0 || col >= N - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            sites[row][col] = true;
            checkNeighbor(row, col);
            numberOfOpenSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (sites[row][col]) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        // TODO
        return false;
    }

    // use for unit testing
    public static void main(String[] args) {}
}
