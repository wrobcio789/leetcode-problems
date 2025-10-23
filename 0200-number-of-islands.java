import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int numIslands(char[][] grid) {
        final int rows = grid.length, cols = grid[0].length;
        final var visited = new boolean[rows][cols];

        int islandNum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    spill(grid, i, j, visited);
                    islandNum++;
                }
            }
        }

        return islandNum;
    }

    private void spill(char[][] grid, int i, int j, boolean[][] visited) {
        final int rows = grid.length, cols = grid[0].length;

        final var q = new LinkedList<Point>();
        q.add(new Point(i, j));

        while (!q.isEmpty()) {
            final var point = q.poll();
            final int row = point.row(), col = point.col();

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                continue;
            }

            if (grid[row][col] == '0' || visited[row][col]) {
                continue;
            }

            visited[point.row()][point.col()] = true;

            q.add(new Point(row + 1, col));
            q.add(new Point(row - 1, col));
            q.add(new Point(row, col + 1));
            q.add(new Point(row, col - 1));
        }
    }

    private record Point(int row, int col) {
    }
}