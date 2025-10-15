import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int COLOR_TO_IGNORE = 1;

    public int closedIsland(int[][] grid) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        int[][] colors = new int[rows][cols];

        final Queue<Vertex> queue = new LinkedList<>();

        for (int i = 0; i < cols; i++) {
            queue.add(new Vertex(0, i, COLOR_TO_IGNORE));
            queue.add(new Vertex(rows - 1, i, COLOR_TO_IGNORE));
        }

        for (int i = 0; i < rows; i++) {
            queue.add(new Vertex(i, 0, COLOR_TO_IGNORE));
            queue.add(new Vertex(i, cols - 1, COLOR_TO_IGNORE));
        }
        runBfs(queue, colors, grid);

        int colorCounter = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols; j++) {
                if (colors[i][j] == 0 && grid[i][j] == 0) {
                    queue.add(new Vertex(i, j, (colorCounter++) + 2));
                    runBfs(queue, colors, grid);
                }
            }
        }

        return colorCounter;
    }

    private void runBfs(Queue<Vertex> queue, int[][] colors, int[][] grid) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        while (!queue.isEmpty()) {
            final var vertex = queue.poll();

            if (vertex.row() < 0 || vertex.col() < 0 || vertex.row() >= rows || vertex.col() >= cols) {
                continue;
            }

            if (grid[vertex.row()][vertex.col()] == 1) {
                continue;
            }

            if (colors[vertex.row()][vertex.col()] != 0) {
                continue;
            }

            colors[vertex.row()][vertex.col()] = vertex.num();
            queue.add(new Vertex(vertex.row() + 1, vertex.col(), vertex.num()));
            queue.add(new Vertex(vertex.row() - 1, vertex.col(), vertex.num()));
            queue.add(new Vertex(vertex.row(), vertex.col() + 1, vertex.num()));
            queue.add(new Vertex(vertex.row(), vertex.col() - 1, vertex.num()));
        }
    }

    private record Vertex(int row, int col, int num) {
    }
}