import java.util.LinkedList;

class Solution {

    public int orangesRotting(int[][] grid) {

        final var rows = grid.length;
        final var cols = grid[0].length;

        final var distances = new short[rows][cols];
        findDistance(grid, distances);

        var result = Short.MIN_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    if (distances[i][j] == 0) {
                        return -1;
                    }
                    result = (short) Math.max(result, distances[i][j]);
                }
            }
        }

        return result == Short.MIN_VALUE ? 0 : result;
    }

    private static void findDistance(int[][] grid, short[][] distances) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        final var queue = new LinkedList<Pos>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Pos(i, j, (short) 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            final var pos = queue.poll();
            final var row = pos.row;
            final var col = pos.col;
            final var distance = pos.distance;

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                continue;
            }

            if (grid[row][col] == 0) {
                continue;
            }

            if (distances[row][col] != 0) {
                continue;
            }


            distances[row][col] = distance;
            final var neighborDistance = (short) (distance + 1);
            queue.add(new Pos(row - 1, col, neighborDistance));
            queue.add(new Pos(row + 1, col, neighborDistance));
            queue.add(new Pos(row, col - 1, neighborDistance));
            queue.add(new Pos(row, col + 1, neighborDistance));
        }
    }

    record Pos(int row, int col, short distance) {
    }
}