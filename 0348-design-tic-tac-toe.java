class TicTacToe {

    private final int n;
    private final char[][] board;

    public TicTacToe(int n) {
        this.n = n;
        this.board = new char[n][n];
    }

    public int move(int row, int col, int player) {
        board[row][col] = (char) player;

        if (checkHorizontal(row, player) || checkVertical(col, player) || checkDiagonalOne(player) || checkDiagonalTwo(player)) {
            return player;
        }

        return 0;
    }

    private boolean checkHorizontal(int row, int player) {
        for (int i = 0; i < n; i++) {
            if (board[row][i] != player) {
                return false;
            }
        }

        return true;
    }

    private boolean checkVertical(int col, int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][col] != player) {
                return false;
            }
        }

        return true;
    }

    private boolean checkDiagonalOne(int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][i] != player) {
                return false;
            }
        }

        return true;
    }

    private boolean checkDiagonalTwo(int player) {
        for (int i = 0; i < n; i++) {
            if (board[i][n - i - 1] != player) {
                return false;
            }
        }

        return true;
    }
}