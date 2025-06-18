import java.util.Arrays;

class Solution {
    public int countComponents(int n, int[][] edges) {
        final var disjointSet = new DisjointSet(n);
        for (final var edge : edges) {
            disjointSet.union(edge[0], edge[1]);
        }

        return disjointSet.countSets();
    }


    private static class DisjointSet {
        final int[] parent;

        public DisjointSet(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int getParent(int v) {
            return parent[v] == v ? v : (parent[v] = getParent(parent[v]));
        }

        public void union(int v, int u) {
            final var a = getParent(v);
            final var b = getParent(u);

            if (a != b) {
                parent[b] = a;
            }
        }

        public int countSets() {
            return (int) Arrays.stream(parent)
                    .map(this::getParent)
                    .distinct()
                    .count();
        }
    }
}