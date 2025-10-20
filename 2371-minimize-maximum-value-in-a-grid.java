import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[][] minScore(int[][] grid) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        final var values = new GridValue[rows * cols];
        for (int row = 0, index = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                values[index++] = new GridValue(grid[row][col], row, col);
            }
        }

        Arrays.sort(values, Comparator.comparingInt(GridValue::value));

        final var rowsMin = new int[rows];
        final var colsMin = new int[cols];
        for (final var value : values) {
            final var candidate = Math.max(1, 1 + Math.max(rowsMin[value.row()], colsMin[value.col()]));
            grid[value.row()][value.col()] = candidate;
            rowsMin[value.row()] = colsMin[value.col()] = candidate;
        }

        return grid;
    }

    private record GridValue(int value, int row, int col) {
    }
}