import java.util.LinkedList;
import java.util.function.BiConsumer;

class Solution {
    private static final int NOT_VISITED = -1;
    private static final int EMPTY_LAND = 0;
    private static final int FRIEND_HOUSE = 1;

    public int shortestDistance(int[][] grid) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        final var totalDistance = new int[rows][cols];
        final var visitCount = new int[rows][cols];

        int totalFriends = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                final var field = grid[i][j];
                if (field == FRIEND_HOUSE) {
                    totalFriends++;
                    runBfs(i, j, grid, totalDistance, visitCount);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == EMPTY_LAND && visitCount[i][j] == totalFriends) {
                    result = Math.min(totalDistance[i][j], result);
                }
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void runBfs(int startY, int startX, int[][] grid, int[][] totalDistance, int[][] visitCount) {
        final var rows = grid.length;
        final var cols = grid[0].length;

        final var visited = new boolean[rows][cols];

        final var q = new LinkedList<DistancedPoint>();
        q.add(new DistancedPoint(startY, startX, 0));

        while (!q.isEmpty()) {
            final var pos = q.poll();

            if (!(pos.x >= 0 && pos.x < cols && pos.y >= 0 && pos.y < rows)) {
                continue;
            }

            if (visited[pos.y][pos.x]) {
                continue;
            }

            if (grid[pos.y][pos.x] != EMPTY_LAND && !(pos.x == startX && pos.y == startY)) {
                continue;
            }

            visited[pos.y][pos.x] = true;
            totalDistance[pos.y][pos.x] += pos.distance;

            q.add(new DistancedPoint(pos.y, pos.x + 1, pos.distance + 1));
            q.add(new DistancedPoint(pos.y, pos.x - 1, pos.distance + 1));
            q.add(new DistancedPoint(pos.y + 1, pos.x, pos.distance + 1));
            q.add(new DistancedPoint(pos.y - 1, pos.x, pos.distance + 1));
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j]) {
                    visitCount[i][j]++;
                }
            }
        }
    }

    private record DistancedPoint(int y, int x, int distance) {
    }
}