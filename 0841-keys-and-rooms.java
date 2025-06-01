import java.util.List;

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        final var n = rooms.size();
        final boolean[] visited = new boolean[n];

        dfs(0, rooms, visited);

        for(final var visit : visited) {
            if(!visit) {
                return false;
            }
        }

        return true;
    }

    private void dfs(int vertex, List<List<Integer>> graph, boolean[] visited) {
        visited[vertex] = true;

        final var neighbours = graph.get(vertex);
        for (final var i : neighbours) {
            if (!visited[i]) {
                dfs(i, graph, visited);
            }
        }

    }
}