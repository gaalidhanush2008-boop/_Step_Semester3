/**
 * Week 10 - Problem 10: Busiest Bus Row
 *
 * Complexity: O(r * c) time (every seat is visited once), O(1) additional space.
 * The best row is updated only on a strictly larger total, so ties keep the smaller index.
 */
public class P10_BusiestBusRow {

    static String busiestRow(int[][] grid) {
        int bestRow = 0;
        int bestTotal = -1;

        for (int row = 0; row < grid.length; row++) {
            int total = 0;
            for (int col = 0; col < grid[row].length; col++) {
                total += grid[row][col];
            }
            if (total > bestTotal) {      // strictly larger => earliest row wins ties
                bestTotal = total;
                bestRow = row;
            }
        }
        return "Row " + bestRow + ", Total " + bestTotal;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {2, 0, 1},
                {3, 3, 1},
                {1, 1, 1}
        };
        System.out.println(busiestRow(grid)); // Row 1, Total 7
        System.out.println(busiestRow(new int[][]{{1, 1}, {2, 0}, {0, 2}})); // Row 0, Total 2 (tie -> smaller index)
    }
}
