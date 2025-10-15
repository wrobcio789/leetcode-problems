import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public boolean isThereAPath(int[][] grid) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        if ((rows + cols) % 2 != 1) {
            return false;
        }

        final var dp = new ArrayList<List<Set<Integer>>>(2);
        for (int i = 0; i < 2; i++) {
            final var list = new ArrayList<Set<Integer>>(cols);
            for (int j = 0; j < cols; j++) {
                list.add(new HashSet<>());
            }
            dp.add(list);
        }

        dp.get(0).get(0).add(valueOf(grid[0][0]));

        final var firstRow = dp.get(0);
        for (int i = 1; i < cols; i++) {
            int thisNum = valueOf(grid[0][i]);
            fillToFrom(firstRow.get(i), firstRow.get(i - 1), thisNum);
        }

        for (int row = 1; row < rows; row++) {
            final var prevRow = dp.get((row - 1 + 2) % 2);
            final var thisRow = dp.get(row % 2);

            final var firstNum = valueOf(grid[row][0]);
            fillToFrom(thisRow.get(0), prevRow.get(0), firstNum);

            for (int col = 1; col < cols; col++) {
                thisRow.get(col).clear();
                final var thisNum = valueOf(grid[row][col]);
                fillToFrom(thisRow.get(col), prevRow.get(col), thisNum);
                fillToFrom(thisRow.get(col), thisRow.get(col - 1), thisNum);
            }
        }

        return dp.get((rows - 1 + 2) % 2).get(cols - 1).contains(0);
    }

    private void fillToFrom(Set<Integer> target, Set<Integer> source, int num) {
        for (final var prevSum : source) {
            target.add(prevSum + num);
        }
    }

    private static int valueOf(int num) {
        return num == 0 ? -1 : 1;
    }
}