class Solution {
    public boolean isValidSudoku(char[][] board) {
        final var occurrences = new byte[10];

        for (int side = 0; side < 9; side++) {
            if (!isRowValid(board, side, occurrences) || !isColValid(board, side, occurrences)) {
                return false;
            }
        }

        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!isSquareValid(board, row, col, occurrences)) {
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean isSquareValid(char[][] board, int startRow, int startCol, byte[] occurrences) {
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                add(board, i, j, occurrences);
            }
        }

        return validateAndReset(occurrences);
    }

    private boolean isColValid(char[][] board, int col, byte[] occurrences) {
        for (int row = 0; row < 9; row++) {
            add(board, row, col, occurrences);
        }

        return validateAndReset(occurrences);
    }

    private boolean isRowValid(char[][] board, int row, byte[] occurrences) {
        for (int col = 0; col < 9; col++) {
            add(board, row, col, occurrences);
        }

        return validateAndReset(occurrences);
    }
    
    private void add(char[][] board, int row, int col, byte[] occurrences) {
        if (board[row][col] != '.') {
            occurrences[board[row][col] - '0']++;
        }
    }

    private boolean validateAndReset(byte[] occurrences) {
        boolean result = true;
        for (int i = 0; i < 10; i++) {
            if (occurrences[i] > 1) {
                return false;
            }
            occurrences[i] = 0;
        }

        return result;
    }
}