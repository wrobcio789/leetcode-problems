import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    private static final int WATER = 0;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        final var grid = new int[m][n];
        final var set = new DisjointSet();

        final var result = new ArrayList<Integer>();

        int num = WATER + 1;
        for (final var pos : positions) {
            final var row = pos[0];
            final var col = pos[1];

            if(grid[row][col] != WATER) {
                result.add(set.getDistinct());
                continue;
            }

            set.add(num);
            grid[row][col] = num++;

            if (row > 0 && grid[row - 1][col] != WATER) {
                set.join(grid[row - 1][col], grid[row][col]);
            }

            if (row < m - 1 && grid[row + 1][col] != WATER) {
                set.join(grid[row + 1][col], grid[row][col]);
            }

            if (col > 0 && grid[row][col - 1] != WATER) {
                set.join(grid[row][col - 1], grid[row][col]);
            }

            if (col < n - 1 && grid[row][col + 1] != WATER) {
                set.join(grid[row][col + 1], grid[row][col]);
            }

            result.add(set.getDistinct());
        }

        return result;
    }

    private class DisjointSet {
        int count = 0;
        private Map<Integer, Integer> parents = new HashMap<>();

        public void join(int a, int b) {
            a = getParent(a);
            b = getParent(b);
            parents.put(a, b);

            if (a != b) {
                count--;
            }
        }

        public int getDistinct() {
            return count;
        }

        private void add(int a) {
            parents.put(a, a);
            count++;
        }

        private int getParent(int a) {
            final var parent = parents.get(a);
            if (parent == a) {
                return a;
            }

            final var result = getParent(parent);
            parents.put(a, result);
            return result;
        }
    }
}