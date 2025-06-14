class Solution {
    public int findLonelyPixel(char[][] picture) {
        final var rows = picture.length;
        final var cols = picture[0].length;

        final var columnCount = new int[cols];
        final var rowCount = new int[rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B') {
                    columnCount[j]++;
                    rowCount[i]++;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B' && columnCount[j] == 1 && rowCount[i] == 1) {
                    result++;
                }
            }
        }

        return result;
    }
}