class Solution {
    public int smallestCommonElement(int[][] mat) {
        final var rows = mat.length;
        final var cols = mat[0].length;

        final var indices = new int[rows];

        int candidate = mat[0][0];
        while (true) {
            boolean hasChangedCandidate = false;

            for (int i = 0; i < rows; i++) {
                while (indices[i] < cols && mat[i][indices[i]] < candidate) {
                    indices[i]++;
                }

                if (indices[i] >= cols) {
                    return -1;
                }

                if (mat[i][indices[i]] != candidate) {
                    candidate = mat[i][indices[i]];
                    hasChangedCandidate = true;
                }
            }

            if (!hasChangedCandidate) {
                break;
            }
        }

        return candidate;
    }
}