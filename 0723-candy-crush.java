class Solution {
    public static final int EMPTY = 0;

    public int[][] candyCrush(int[][] board) {
        while (iterate(board)) ;

        return board;
    }

    private boolean iterate(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        boolean[][] removalFlag = new boolean[rows][cols];
        performHorizontalFlagging(board, rows, cols, removalFlag);
        performVerticalFlagging(board, rows, cols, removalFlag);

        final var hasChanged = cleanFlagged(board, rows, cols, removalFlag);
        performFall(board, rows, cols);

        return hasChanged;
    }

    private static boolean cleanFlagged(int[][] board, int rows, int cols, boolean[][] removalFlag) {
        boolean hasChanged = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (removalFlag[i][j]) {
                    hasChanged = true;
                    board[i][j] = EMPTY;
                }
            }
        }
        return hasChanged;
    }

    private static void performFall(int[][] board, int rows, int cols) {
        for (int i = 0; i < cols; i++) {
            int fall = 0;
            for (int j = rows - 1; j >= 0; j--) {
                board[j + fall][i] = board[j][i];

                if (board[j][i] == EMPTY) {
                    fall++;
                }
            }
            for (int j = 0; j < fall; j++) {
                board[j][i] = EMPTY;
            }
        }
    }

    private static void performVerticalFlagging(int[][] board, int rows, int cols, boolean[][] removalFlag) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (board[j][i] == EMPTY) {
                    continue;
                }

                int k = j;
                while (k + 1 < rows && board[k + 1][i] == board[j][i]) {
                    k++;
                }

                if (k - j + 1 >= 3) {
                    for (int z = j; z <= k; z++) {
                        removalFlag[z][i] = true;
                    }
                }

                j = k;
            }
        }
    }

    private static void performHorizontalFlagging(int[][] board, int rows, int cols, boolean[][] removalFlag) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == EMPTY) {
                    continue;
                }

                int k = j;
                while (k + 1 < cols && board[i][k + 1] == board[i][j]) {
                    k++;
                }

                if (k - j + 1 >= 3) {
                    for (int z = j; z <= k; z++) {
                        removalFlag[i][z] = true;
                    }
                }

                j = k;
            }
        }
    }
}