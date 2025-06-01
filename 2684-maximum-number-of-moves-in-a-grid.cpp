#include <vector>
#include <algorithm>
#include <iostream>

const int INITIAL_VALUE = 100*1000;

class Solution {
public:
    int maxMoves(std::vector<std::vector<int>>& grid) {
        const int height = grid.size();
        const int width = grid[0].size();

        int result = INITIAL_VALUE;
        std::vector<std::vector<int>> partialResult(2, std::vector<int>(height));
        for (int row = 0; row < height; row++) {
            partialResult[0][row] = INITIAL_VALUE;
        }

        for (int col = 1; col < width; col++) {
            std::vector<int>& currentResult = partialResult[col % 2];
            std::vector<int>& prevResult = partialResult[(col + 1) % 2];

            {
                const int row = 0;
                const int num = grid[row][col];

                currentResult[row] = std::max({
                    0,
                    grid[row][col - 1] < num ? prevResult[row] - 1 : 0,
                    grid[row + 1][col - 1] < num ? prevResult[row + 1] - 1 : 0
                });

                if (currentResult[row] != 0 && currentResult[row] < result) {
                    result = currentResult[row];
                }
            }

            for (int row = 1; row < height - 1; row++) {
                const int num = grid[row][col];

                currentResult[row]= std::max({
                    0,
                    grid[row - 1][col - 1] < num ? prevResult[row - 1] - 1 : 0,
                    grid[row][col - 1] < num ? prevResult[row] - 1 : 0,
                    grid[row + 1][col - 1] < num ? prevResult[row + 1] - 1 : 0
                });

                if (currentResult[row] != 0 && currentResult[row] < result) {
                    result = currentResult[row];
                }
            }

            {
                const int row = height - 1;
                const int num = grid[row][col];

                currentResult[row] = std::max({
                    0,
                    grid[row - 1][col - 1] < num ? prevResult[row - 1] - 1 : 0,
                    grid[row][col - 1] < num ? prevResult[row] - 1 : 0,
                });

                if (currentResult[row]!= 0 && currentResult[row] < result) {
                    result = currentResult[row];
                }
            }
        }

        return INITIAL_VALUE - result;
    }
};