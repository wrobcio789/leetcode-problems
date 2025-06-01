import java.util.*;

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        final var graph = buildGraph(equations, values);

        return queries.stream()
                .mapToDouble(query -> solve(graph, query.get(0), query.get(1)))
                .toArray();
    }

    private double solve(Map<String, List<Edge>> graph, String a, String b) {
        final Set<String> visited = new HashSet<>();
        final var dfsResult = dfsUntilFound(a, b, graph, visited);

        return Double.isNaN(dfsResult) ? -1.0 : dfsResult;
    }

    private double dfsUntilFound(final String current, final String meta, final Map<String, List<Edge>> graph, final Set<String> visited) {
        visited.add(current);

        if (!graph.containsKey(current)) {
            return Double.NaN;
        }

        if (Objects.equals(current, meta)) {
            return 1.0;
        }

        final var neighbours = graph.get(current);
        return neighbours.stream()
                .filter(edge -> !visited.contains(edge.vertex()))
                .mapToDouble(edge -> edge.cost() * dfsUntilFound(edge.vertex(), meta, graph, visited))
                .filter(Double::isFinite)
                .findFirst()
                .orElse(Double.NaN);

    }

    private Map<String, List<Edge>> buildGraph(List<List<String>> equations, double[] values) {
        final Map<String, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            final var a = equations.get(i).get(0);
            final var b = equations.get(i).get(1);
            final var cost = values[i];

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            graph.get(a).add(new Edge(b, cost));
            graph.get(b).add(new Edge(a, 1.0 / cost));
        }

        return graph;
    }

    record Edge(String vertex, double cost) {
    }
}