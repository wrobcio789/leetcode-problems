import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static final int COLORS_COUNT = 'z' - 'a' + 1;

    public int largestPathValue(String colors, int[][] edges) {
        try {
            final int n = colors.length();
            final var graph = createGraph(n, edges);
            final var topoOrder = topoSort(n, graph);
            return calculateResult(n, colors, topoOrder, graph);
        } catch (CycleException e) {
            return -1;
        }
    }

    private int calculateResult(int n, String colors, List<Integer> topoOrder, List<List<Integer>> graph) {
        final var dp = new int[n][COLORS_COUNT];
        for (final var v : topoOrder) {
            final var neighbours = graph.get(v);

            for (final var u : neighbours) {
                for (int color = 0; color < COLORS_COUNT; color++) {
                    dp[v][color] = Math.max(dp[v][color], dp[u][color]);
                }
            }

            final var color = colors.charAt(v) - 'a';
            dp[v][color]++;
        }

        return Arrays.stream(dp).flatMapToInt(Arrays::stream).max().orElse(0);
    }

    private List<Integer> topoSort(int n, List<List<Integer>> graph) {
        final var result = new ArrayList<Integer>(n);
        final var status = new Status[n];
        Arrays.fill(status, Status.NOT_VISITED);
        for (int i = 0; i < n; i++) {
            if (status[i] == Status.NOT_VISITED) {
                topoSortDfs(i, graph, status, result);
            }
        }
        return result;
    }

    private void topoSortDfs(int v, List<List<Integer>> graph, Status[] status, List<Integer> result) {
        status[v] = Status.CURRENT;

        for (final var u : graph.get(v)) {
            if (status[u] == Status.NOT_VISITED) {
                topoSortDfs(u, graph, status, result);
            } else if (status[u] == Status.CURRENT) {
                throw new CycleException();
            }
        }

        result.add(v);
        status[v] = Status.VISITED;
    }

    private List<List<Integer>> createGraph(int n, int[][] edges) {
        final var graph = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (final var edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        return graph;
    }

    private enum Status {
        NOT_VISITED,
        VISITED,
        CURRENT;
    }

    private static class CycleException extends RuntimeException {
    }
}