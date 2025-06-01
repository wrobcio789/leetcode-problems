#include <vector>
#include <queue>


class Solution {
private:
    struct Point {
        int row;
        int col;

        Point(int row, int col) {
            this -> row = row;
            this -> col = col;
        }
    };

public:
    std::vector<std::vector<int>> pacificAtlantic(std::vector<std::vector<int>>& heights) {
        const int rows = heights.size();
        const int cols = heights[0].size();

        std::vector<std::vector<bool>> visitedForAtlantic(rows, std::vector<bool>(cols));
        std::vector<std::vector<bool>> visitedForPacific(rows, std::vector<bool>(cols));

        std::queue<Point> antlanticQ, pacificQ;
        for(int i = 0; i<cols; i++) {
            antlanticQ.push(Point(rows - 1, i));
            pacificQ.push(Point(0, i));
        }

        for(int i = 0; i<rows; i++) {
            antlanticQ.push(Point(i, cols-1));
            pacificQ.push(Point(i, 0));
        }

        runBfs(antlanticQ, heights, visitedForAtlantic);
        runBfs(pacificQ, heights, visitedForPacific);

        std::vector<std::vector<int>> result;
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<cols; j++) {
                if(visitedForAtlantic[i][j] && visitedForPacific[i][j]) {
                    result.push_back(std::vector<int>({i, j}));
                }
            }
        }

        return result;
    }

private:

    void runBfs(std::queue<Point>& bfsQ, const std::vector<std::vector<int>>& heights, std::vector<std::vector<bool>>& visited) {
        const int rows = heights.size();
        const int cols = heights[0].size();
        
        while(!bfsQ.empty()) {
            Point point = bfsQ.front(); bfsQ.pop();
            int row = point.row;
            int col = point.col;
            int level = heights[row][col];

            if(visited[row][col]) {
                continue;
            }
        
            visited[row][col] = true;

            if (row > 0 && level <= heights[row-1][col]) {
                bfsQ.emplace(row-1, col);
            }
            if (row < rows - 1 && level <= heights[row + 1][col]) {
                bfsQ.emplace(row+1, col);
            } 
            if (col > 0 && level <= heights[row][col-1]) {
                bfsQ.emplace(row, col-1);
            }
            if (col < cols - 1 && level <= heights[row][col+1]) {
                bfsQ.emplace(row, col+1);
            }   

        }
    }
};