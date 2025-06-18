import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        final var rows = maze.length;
        final var cols = maze[0].length;

        final var up = new int[rows][cols];
        final var down = new int[rows][cols];
        final var left = new int[rows][cols];
        final var right = new int[rows][cols];

        fillDirections(maze, right, left, down, up);

        final var distance = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        final var queue = new PriorityQueue<>(Comparator.comparingInt(DistancedPos::distance));
        queue.add(new DistancedPos(new Pos(start[0], start[1]), 0));
        distance[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            final var distancedPos = queue.poll();
            final var pos = distancedPos.pos();
            final var row = pos.row();
            final var col = pos.col();
            final var currentDistance = distancedPos.distance();

            final var leftPos = pos.moveHorizontal(left[row][col]);
            final var rightPos = pos.moveHorizontal(right[row][col]);
            final var upPos = pos.moveVertical(up[row][col]);
            final var downPos = pos.moveVertical(down[row][col]);

            Stream.of(leftPos, rightPos, upPos, downPos).filter(position -> position.isIn(rows, cols)).forEach(position -> {
                final var nextDistance = currentDistance + position.distance(pos);
                if (distance[position.row()][position.col()] > nextDistance) {
                    distance[position.row()][position.col()] = nextDistance;
                    queue.add(new DistancedPos(position, nextDistance));
                }
            });
        }

        final var expectResult = distance[destination[0]][destination[1]];
        if (expectResult == Integer.MAX_VALUE) {
            return -1;
        }
        return expectResult;
    }

    private void fillDirections(int[][] maze, int[][] right, int[][] left, int[][] down, int[][] up) {
        final var rows = maze.length;
        final var cols = maze[0].length;

        for (int i = 0; i < rows; i++) {
            int lastRight = cols - 1;
            for (int j = lastRight; j >= 0; j--) {
                if (maze[i][j] == 1) {
                    lastRight = j - 1;
                } else {
                    right[i][j] = lastRight;
                }
            }

            int lastLeft = 0;
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 1) {
                    lastLeft = j + 1;
                } else {
                    left[i][j] = lastLeft;
                }
            }
        }

        for (int i = 0; i < cols; i++) {
            int lastDown = rows - 1;
            for (int j = lastDown; j >= 0; j--) {
                if (maze[j][i] == 1) {
                    lastDown = j - 1;
                } else {
                    down[j][i] = lastDown;
                }
            }

            int lastUp = 0;
            for (int j = 0; j < rows; j++) {
                if (maze[j][i] == 1) {
                    lastUp = j + 1;
                } else {
                    up[j][i] = lastUp;
                }
            }
        }
    }

    private record Pos(int row, int col) {
        public Pos moveHorizontal(int x) {
            return new Pos(row, x);
        }

        public Pos moveVertical(int y) {
            return new Pos(y, col);
        }

        public boolean isIn(int rows, int cols) {
            return row >= 0 && col >= 0 && row < rows && col < cols;
        }

        public int distance(Pos pos) {
            return Math.abs(row - pos.row()) + Math.abs(col - pos.col());
        }
    }

    private record DistancedPos(Pos pos, int distance) {
    }
}