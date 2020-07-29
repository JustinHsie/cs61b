package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    Percolation grid;
    double[] results;
    int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new Percolation(N);
        results = new double[T];
        this.T = T;

        for (int i = 0; i < T; i++) {
            while (!grid.percolates()) {
                grid.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            results[i] = (double) grid.numberOfOpenSites / ((double) N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / java.lang.Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / java.lang.Math.sqrt(T);
    }

    public static void main(String[] args) {
        PercolationStats test = new PercolationStats(200, 50);
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println(test.confidenceLow());
        System.out.println(test.confidenceHigh());
    }
}
