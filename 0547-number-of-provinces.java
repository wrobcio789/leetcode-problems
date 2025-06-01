class Solution {
    public int findCircleNum(int[][] isConnected) {
        final var n = isConnected.length;
        final var visited = new boolean[n];

        int result = 0;
        for(int i = 0; i<n; i++) {
            if(!visited[i]) {
                dfs(i, isConnected, visited);
                result++;
            }
        }

        return result;
    }

    private void dfs(int node, int[][] graph, boolean[] visited) {
        visited[node] = true;
        final var neighbours = graph[node];
        final var n = neighbours.length;
        for(int i = 0; i<n; i++) {
            if(neighbours[i] == 1 && !visited[i]) {
                dfs(i, graph, visited);
            }
        }
    }
}