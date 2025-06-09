import java.util.*;

class Solution {
    public int numDistinctIslands(int[][] grid) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        final Set<List<Long>> islandPatterns = new HashSet<>();

        final var visited = new boolean[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    final var pattern = floodDown(grid, visited, row, col);
                    islandPatterns.add(pattern);
                }
            }
        }

        return islandPatterns.size();
    }

    private List<Long> floodDown(int[][] grid, boolean[][] visited, int row, int col) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        final var masks = new long[rows];
        Arrays.fill(masks, 0);

        final var q = new LinkedList<Point>();
        q.add(new Point(row, col));

        while (!q.isEmpty()) {
            final var point = q.poll();
            final var y = point.row();
            final var x = point.col();

            if (y < 0 || y >= rows || x < 0 || x >= cols || visited[y][x] || grid[y][x] == 0) {
                continue;
            }
            visited[y][x] = true;
            masks[y] ^= (1L << x);

            q.add(new Point(y + 1, x));
            q.add(new Point(y - 1, x));
            q.add(new Point(y, x + 1));
            q.add(new Point(y, x - 1));
        }

        return parseResult(masks);
    }

    private List<Long> parseResult(long[] result) {
        final var referencePoint = Arrays.stream(result)
                .filter(value -> value != 0)
                .findAny()
                .orElseThrow();

        final var offset = calcOffset(referencePoint);

        return Arrays.stream(result)
                .filter(value -> value != 0)
                .map(value -> Long.rotateRight(value, offset))
                .boxed()
                .toList();
    }

    private int calcOffset(long value) {
        for(int i = 0; i< Long.SIZE; i++) {
            if((value & 1) == 1) {
                return i;
            }
            value >>= 1;
        }

        return 0;
    }

    private record Point(int row, int col) {
    }
}