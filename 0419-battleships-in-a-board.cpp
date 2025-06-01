#include <vector>

class Solution {
public:
    int countBattleships(const std::vector<std::vector<char>>& board) {
        const int height = board.size();
        const int width = board[0].size();

        int result = 0;
        for(int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if(board[row][col] == 'X' && (!isSelected(board, row-1, col) && !isSelected(board, row, col-1))) {
                    result++;
                }
            }
        }

        return result;
        
    }

private:
    bool isSelected(const std::vector<std::vector<char>>& board, int row, int col) {
        const int height = board.size();
        const int width = board[0].size();

        if (row < 0 || row >= height) {
            return false;
        }

        if (col < 0 || col >= width) {
            return false;
        }

        return board[row][col] == 'X';
    }

};