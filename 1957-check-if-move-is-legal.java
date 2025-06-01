import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.List;

class Solution {
    private static final int SIZE = 8;

    private static final List<IndexChanger> MOVES = List.of(//
            indexChanger(row -> row + 1, UnaryOperator.identity()),
            indexChanger(row -> row - 1, UnaryOperator.identity()),
            indexChanger(UnaryOperator.identity(), col -> col + 1),
            indexChanger(UnaryOperator.identity(), col -> col - 1),
            indexChanger(row -> row + 1, col -> col + 1),
            indexChanger(row -> row + 1, col -> col - 1),
            indexChanger(row -> row - 1, col -> col + 1),
            indexChanger(row -> row - 1, col -> col - 1)
        );

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        char oppositeColor = color == 'W' ? 'B' : 'W';
        
        for (final var move : MOVES) {
            int row = rMove;
            int col = cMove;

            row = move.changeRow(row);
            col = move.changeCol(col);

            if(!inBoundaries(row, col) || board[row][col] != oppositeColor) {
                continue;
            }

            row = move.changeRow(row);
            col = move.changeCol(col);

            while(inBoundaries(row, col)) {
                if (board[row][col] == color) {
                    return true;
                }

                
                if (board[row][col] == '.') {
                    break;
                }

                row = move.changeRow(row);
                col = move.changeCol(col);
            }
        }

        return false;
    }

    private boolean inBoundaries(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }
    

    private static IndexChanger indexChanger(UnaryOperator<Integer> rowChanger, UnaryOperator<Integer> colChanger) {
        return new IndexChanger() {

            @Override
            public int changeRow(int row) {
                return rowChanger.apply(row);
            }

            @Override
            public int changeCol(int col) {
                return colChanger.apply(col);
            }
            
        };
    }

    private interface IndexChanger {
        int changeRow(int row);
        int changeCol(int col);
    }
}