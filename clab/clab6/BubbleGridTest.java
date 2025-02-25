import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic() {

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] grid2 = {{1, 1, 0},
                         {1, 0, 0},
                         {1, 1, 0},
                         {1, 1, 1}};
        int[][] darts = {{1, 0}};
        int[][] darts2 = {{2, 2}, {2, 0}};
        int[] expected = {2};
        int[] expected2 = {0, 4};

        validate(grid, darts, expected);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }
}
