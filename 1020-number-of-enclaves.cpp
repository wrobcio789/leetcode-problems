#include <vector>
#include <queue>

class Solution {
public:
    int numEnclaves(std::vector<std::vector<int>>& grid) {
        const int height = grid.size();
        const int width = grid[0].size();

        std::vector<std::vector<bool>> visited(height, std::vector<bool>(width, false));
        std::queue<std::pair<int,int>> bfsQ;

        for(int i = 0; i<height; i++) {
            bfsQ.emplace(i, 0);
            bfsQ.emplace(i, width - 1);
        }

        for(int i = 0; i<width; i++) {
            bfsQ.emplace(0, i);
            bfsQ.emplace(height-1, i);
        }

        int result = 0;
        while(!bfsQ.empty()) {
            auto[y, x] = bfsQ.front(); bfsQ.pop();

            if (y < 0 || y>=height || x < 0 || x >= width) {
                continue;
            }

            if(visited[y][x] || grid[y][x] == 0) {
                continue;
            }

            visited[y][x] = true;

            bfsQ.emplace(y + 1, x);
            bfsQ.emplace(y - 1, x);
            bfsQ.emplace(y, x + 1);
            bfsQ.emplace(y, x - 1);
        }

        for(int i = 0; i<height; i++) {
            for(int j = 0; j<width; j++) {
                result += (!visited[i][j] && grid[i][j] == 1);
            }
        }

        return result;
    }
};