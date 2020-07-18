import java.util.Arrays;

public class UnionFind {
    int[] dsArray;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        dsArray = new int[n];
        Arrays.fill(dsArray, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= dsArray.length || vertex < 0) {
            throw new IllegalArgumentException("Out of bounds");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return dsArray[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        if (parent(rootV1) < parent(rootV2)) {
            dsArray[v2] = rootV1;
        }
        else {
            dsArray[v1] = rootV2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int root;
        int vOrig = vertex;
        while(parent(vertex) >= 0) {
            vertex = parent(vertex);
        }
        root = vertex;

        // Path-compression
        while(parent(vOrig) >= 0) {
            int temp = parent(vOrig);
            dsArray[vOrig] = root;
            vOrig = temp;
        }
        return root;
    }

}
