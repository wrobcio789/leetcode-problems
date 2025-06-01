import java.util.LinkedList;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        final var rows = maze.length;
        final var cols = maze[0].length;

        final var distances = new short[rows][cols];
        return findDistance(maze, entrance, distances);
    }

    private static short findDistance(char[][] maze, int[] entrance, short[][] distances) {
        final var rows = maze.length;
        final var cols = maze[0].length;

        final var queue = new LinkedList<Pos>();
        queue.add(new Pos(entrance[0], entrance[1], (short) 0));

        var result = Short.MAX_VALUE;

        while (!queue.isEmpty()) {
            final var pos = queue.poll();
            final var row = pos.row;
            final var col = pos.col;
            final var distance = pos.distance;

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                continue;
            }

            if (maze[row][col] == '+') {
                continue;
            }

            if (distances[row][col] != 0) {
                continue;
            }

            distances[row][col] = distance;
            if ((row != entrance[0] || col != entrance[1]) && (row == 0 || row == rows - 1 || col == 0 || col == cols - 1)) {
                result = (short) Math.min(result, distance);
            }

            final var neighborDistance = (short) (distance + 1);
            queue.add(new Pos(row - 1, col, neighborDistance));
            queue.add(new Pos(row + 1, col, neighborDistance));
            queue.add(new Pos(row, col - 1, neighborDistance));
            queue.add(new Pos(row, col + 1, neighborDistance));
        }

        return result == Short.MAX_VALUE ? -1 : result;
    }

    record Pos(int row, int col, short distance) {
    }
}