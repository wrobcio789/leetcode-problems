import java.util.*;

class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        final var height = maze.length;
        final var width = maze[0].length;

        final var right = new int[height][width];
        final var left = new int[height][width];
        final var up = new int[height][width];
        final var down = new int[height][width];

        fillHorizontalMovements(maze, height, width, right, left);
        fillVerticalMovements(maze, height, width, up, down);

        final var graph = createGraph(maze, right, left, up, down);
        return findDestination(graph, start, destination);
    }

    private static boolean findDestination(final Map<Vertex, List<Vertex>> graph, int[] start, int[] destination) {
        final var visited = new HashSet<Vertex>();
        final var queue = new LinkedList<Vertex>();
        queue.add(new Vertex(start[0], start[1]));

        final var goal = new Vertex(destination[0], destination[1]);
        while (!queue.isEmpty()) {
            final var vertex = queue.poll();

            visited.add(vertex);
            if (Objects.equals(vertex, goal)) {
                return true;
            }

            for (final var neighbour : graph.getOrDefault(vertex, Collections.emptyList())) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }

        return false;
    }

    private static void fillVerticalMovements(int[][] maze, int height, int width, int[][] up, int[][] down) {
        for (int i = 0; i < width; i++) {
            var closestUp = 0;
            for (int j = 0; j < height; j++) {
                if (maze[j][i] == 1) {
                    closestUp = j + 1;
                } else {
                    up[j][i] = closestUp;
                }
            }

            var closestDown = height - 1;
            for (int j = height - 1; j >= 0; j--) {
                if (maze[j][i] == 1) {
                    closestDown = j - 1;
                } else {
                    down[j][i] = closestDown;
                }
            }
        }
    }

    private static void fillHorizontalMovements(int[][] maze, int height, int width, int[][] right, int[][] left) {
        for (int i = 0; i < height; i++) {
            var closestLeft = 0; //wall
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == 1) {
                    closestLeft = j + 1;
                } else {
                    left[i][j] = closestLeft;
                }
            }

            var closestRight = width - 1;
            for (int j = width - 1; j >= 0; j--) {
                if (maze[i][j] == 1) {
                    closestRight = j - 1;
                } else {
                    right[i][j] = closestRight;
                }
            }
        }
    }

    private static HashMap<Vertex, List<Vertex>> createGraph(int[][] maze, int[][] right, int[][] left, int[][] up, int[][] down) {
        final var height = maze.length;
        final var width = maze[0].length;

        final var graph = new HashMap<Vertex, List<Vertex>>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j] == 0) {
                    final var neighbours = new ArrayList<Vertex>();
                    graph.put(new Vertex(i, j), neighbours);

                    neighbours.add(new Vertex(i, left[i][j]));
                    neighbours.add(new Vertex(i, right[i][j]));
                    neighbours.add(new Vertex(up[i][j], j));
                    neighbours.add(new Vertex(down[i][j], j));
                }
            }
        }
        return graph;
    }


    private record Vertex(int y, int x) {
    }
}