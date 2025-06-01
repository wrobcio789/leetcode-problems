#include <vector>
#include <array>
#include <algorithm>

class Node {
public:
    bool val;
    bool isLeaf;
    Node* topLeft;
    Node* topRight;
    Node* bottomLeft;
    Node* bottomRight;
    
    Node() {
        val = false;
        isLeaf = false;
        topLeft = nullptr;
        topRight = nullptr;
        bottomLeft = nullptr;
        bottomRight = nullptr;
    }
    
    Node(bool _val, bool _isLeaf) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = nullptr;
        topRight = nullptr;
        bottomLeft = nullptr;
        bottomRight = nullptr;
    }
    
    Node(bool _val, bool _isLeaf, Node* _topLeft, Node* _topRight, Node* _bottomLeft, Node* _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};


class Solution {
public:
    Node* construct(std::vector<std::vector<int>>& grid) {
        const int n = grid.size();
        return construct(grid, 0, 0, n);
    }

    Node* construct(std::vector<std::vector<int>>& grid, int y, int x, int n) {
        if (n == 1) {
            return new Node(grid[y][x], true);
        } else {
            const int childN = n/2;
            Node* topLeft = construct(grid, y, x, childN);
            Node* topRight = construct(grid, y, x + childN, childN);
            Node* bottomLeft = construct(grid, y + childN, x, childN);
            Node* bottomRight = construct(grid, y + childN, x + childN, childN);
            
            std::array<Node*, 4> children = {topLeft, topRight, bottomLeft, bottomRight};
            if (std::all_of(children.begin(), children.end(), [topLeft](Node* child) {
                return child->isLeaf && child->val == topLeft->val;
            })) {
                int val = topLeft->val;
                std::for_each(children.begin(), children.end(), [](Node* child) {
                    delete child;
                });
                return new Node(val, true);
            }

            return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
        }        
    }
};