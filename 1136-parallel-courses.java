import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int CURRENTLY_VISITED = -1;
    private static final int CYCLE_DETECTED = -1;

    public int minimumSemesters(int n, int[][] relations) {
        final var distances = new int[n];

        final var graph = constructGraph(n, relations);
        for (int i = 0; i < n; i++) {
            int distance = dfsDepth(i, graph, distances);

            if (distance == CYCLE_DETECTED) {
                return -1;
            }
        }

        return Arrays.stream(distances).max().orElse(0);
    }


    private int dfsDepth(int vertex, List<List<Integer>> graph, int[] distances) {
        if (distances[vertex] == CURRENTLY_VISITED) {
            return CYCLE_DETECTED;
        }

        if (distances[vertex] != 0) {
            return distances[vertex];
        }

        distances[vertex] = CURRENTLY_VISITED;

        int maxDistance = 0;
        final var neighbours = graph.get(vertex);
        for (final var neighbour : neighbours) {
            int distance = dfsDepth(neighbour, graph, distances);

            if (distance == CYCLE_DETECTED) {
                return CYCLE_DETECTED;
            }
            maxDistance = Math.max(distance, maxDistance);
        }

        return distances[vertex] = maxDistance + 1;
    }

    private List<List<Integer>> constructGraph(int n, int[][] relations) {
        final var graph = new ArrayList<List<Integer>>(n);

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (final var relation : relations) {
            graph.get(relation[0] - 1).add(relation[1] - 1);
        }

        return graph;
    }
}
