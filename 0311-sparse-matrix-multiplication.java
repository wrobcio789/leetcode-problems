class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        final var resultHeight = mat1.length;
        final var resultWidth = mat2[0].length;

        final var kSize = mat1[0].length;

        final var result = new int[resultHeight][resultWidth];

        for (int i = 0; i < resultHeight; i++) {
            for (int k = 0; k < kSize; k++) {
                if (mat1[i][k] == 0) {
                    continue;
                }

                for (int j = 0; j < resultWidth; j++) {
                    result[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }

        return result;
    }
}