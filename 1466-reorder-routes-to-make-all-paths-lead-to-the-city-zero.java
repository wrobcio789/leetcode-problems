import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public int minReorder(int n, int[][] connections) {
        final List<List<Neighbour>> graph = new ArrayList<>(n);
        IntStream.range(0, n).forEach(index -> graph.add(new ArrayList<>()));

        for(final var connection : connections) {
            final var a = connection[0];
            final var b = connection[1];

            graph.get(a).add(new Neighbour(b, 1));
            graph.get(b).add(new Neighbour(a, 0));
        }

        return dfs(0, -1, graph);
    }

    private int dfs(final int i, final int parent, final List<List<Neighbour>> graph) {
        return graph.get(i).stream()
                .filter(neighbour -> neighbour.i() != parent)
                .mapToInt(neighbour -> neighbour.cost() + dfs(neighbour.i(), i, graph))
                .sum();
    }

    record Neighbour(int i, int cost) {}
}