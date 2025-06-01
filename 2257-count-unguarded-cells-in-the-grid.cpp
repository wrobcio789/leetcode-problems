#include <vector>

static const char EMPTY = 1;
static const char FILLED = 2;
static const char CELL_THRESHOLD = FILLED;
static const char GUARD = 3;
static const char WALL = 4;

class Solution {
public:
    int countUnguarded(int height, int width, std::vector<std::vector<int>>& guards, std::vector<std::vector<int>>& walls) {
        std::vector<std::vector<char>> array(height, std::vector<char>(width, EMPTY));

        for(const auto& wall : walls) {
            array[wall[0]][wall[1]] = WALL;
        }

        for(const auto& guard : guards) {
            array[guard[0]][guard[1]] = GUARD;
        }

        for(const auto& guard : guards) {
            const int row = guard[0];
            const int col = guard[1];
            
            int y = row - 1;
            while (y >= 0 && array[y][col] <= CELL_THRESHOLD) {
                array[y][col] = FILLED;
                y--;
            }

            y = row + 1;
            while (y < height && array[y][col] <= CELL_THRESHOLD) {
                array[y][col] = FILLED;
                y++;
            }

            int x = col - 1;
            while (x >= 0 && array[row][x] <= CELL_THRESHOLD) {
                array[row][x] = FILLED;
                x--;
            }

            
            x = col + 1;
            while (x < width && array[row][x] <= CELL_THRESHOLD) {
                array[row][x] = FILLED;
                x++;
            }
        }

        int result = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                result += (array[row][col] == EMPTY);
            }
        }

        return result;
    }
};