import java.util.ArrayList;
import java.util.List;

class Solution {
    private static final int NOT_VISITED = 0;
    private static final int CURRENTLY_PROCESSED = 1;
    private static final int PROCESSED = 2;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        final var graph = constructGraph(n, edges);
        final var visited = new int[n];
        return isGraphValid(graph, visited, source, destination);
    }

    private boolean isGraphValid(List<List<Integer>> graph, int[] states, int root, int dest) {
        states[root] = CURRENTLY_PROCESSED;

        final var neighbours = graph.get(root);
        
        if (neighbours.isEmpty() && root != dest) {
            return false;
        }

        for (final var neighbour : graph.get(root)) {
            if (states[neighbour] == CURRENTLY_PROCESSED) {
                return false;
            }

            if (states[neighbour] == NOT_VISITED) {
                if (!isGraphValid(graph, states, neighbour, dest)) {
                    return false;
                }
            }
        }
        
        states[root] = PROCESSED;
        return true;
    }

    private List<List<Integer>> constructGraph(int n, int[][] edges) {
        final List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (final var edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        return graph;
    }
}