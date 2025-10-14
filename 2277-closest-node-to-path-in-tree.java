import java.util.Arrays;

class Solution {
    private static final int ROOT = 0;

    public int[] closestNode(int n, int[][] edges, int[][] query) {
        final var orderMarker = new OrderMarker().markOrderDfs(ROOT, edges, n);
        final var parent = orderMarker.parentResult;
        final var order = orderMarker.orderResult;

        return Arrays.stream(query)
                .mapToInt(q -> processQuery(q, order, parent))
                .toArray();
    }

    private int processQuery(int[] query, int[] order, int[] parent) {
        int a = query[0], b = query[1], c = query[2];

        while (a != b) {
            while (c != ROOT && order[c] > order[a] && order[c] > order[b]) {
                c = parent[c];
            }

            if (c == a) {
                return a;
            } else if (c == b) {
                return b;
            }

            if (order[a] < order[b]) {
                b = parent[b];
            } else {
                a = parent[a];
            }
        }

        return a;
    }

    private static class OrderMarker {
        int order;
        int[] orderResult;
        int[] parentResult;


        public OrderMarker markOrderDfs(int v, int[][] edges, int n) {
            order = 0;
            orderResult = new int[n];
            parentResult = new int[n];

            markOrderDfsInternal(v, edges, v);
            return this;
        }

        private void markOrderDfsInternal(int v, int[][] edges, int parent) {
            orderResult[v] = order++;
            parentResult[v] = parent;
            for (final var edge : edges) {
                if (edge[0] == v && edge[1] != parent) {
                    markOrderDfsInternal(edge[1], edges, v);
                } else if (edge[1] == v && edge[0] != parent) {
                    markOrderDfsInternal(edge[0], edges, v);
                }
            }
        }
    }

}