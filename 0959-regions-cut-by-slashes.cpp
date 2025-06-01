#include <vector>
#include <string>
#include <set>
#include <unordered_map>

class Solution {
public:
    int regionsBySlashes(std::vector<std::string>& grid) {
        const int n = grid.size();

        std::vector<std::pair<int, int>> edges;
        edges.reserve(2000);

        for (int col = 0; col < n; col++) {
            int a = getVertexNumber(0, col);
            int b = getVertexNumber(0, col + 1);
            int c = getVertexNumber(n, col);
            int d = getVertexNumber(n, col + 1);
            edges.emplace_back(a, b);
            edges.emplace_back(c, d);
        }

        for (int row = 0; row <= n; row++) {
            int a = getVertexNumber(row, 0);
            int b = getVertexNumber(row + 1, 0);
            int c = getVertexNumber(row, n);
            int d = getVertexNumber(row + 1, n);
            edges.emplace_back(a, b);
            edges.emplace_back(c, d);
        }

        for(int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                char symbol = grid[row][col];

                if (symbol == '/') {
                    int a = getVertexNumber(row, col + 1);
                    int b = getVertexNumber(row + 1, col);
                    edges.emplace_back(a, b);
                } else if (symbol == '\\') {
                    int a = getVertexNumber(row, col);
                    int b = getVertexNumber(row+1, col+1);
                    edges.emplace_back(a, b);
                }
            }
        }

        int e = edges.size(), v = countVertices(edges), disjointComponents = countDisjointComponents(edges);
        return e - v + disjointComponents; //modified Euler's formula        
    }

private:
    class DisjointSet {
    private:
        std::unordered_map<int, int> parent;

        int getRoot(int a) {
            if (parent.count(a) > 0) {
                const int aParent = parent[a];
                return a == aParent ? a : (parent[a] = getRoot(aParent));
            }
            return parent[a] = a;
        }

    public:

        void connect(int a, int b) {
            a = getRoot(a);
            b = getRoot(b);
            
            if(a != b) {
                parent[b] = a;
            }
        }

        int countDisjointSets() {
            std::set<int> rootsBag;
            for(const auto& pair : parent) {
                rootsBag.insert(getRoot(pair.first));
            }

            return rootsBag.size();
        }
    };

    int getVertexNumber(int row, int col) {
        return 100 * row + col;
    }

    int countDisjointComponents(const std::vector<std::pair<int, int>>& edges) {
        DisjointSet set;

        for(const auto& edge : edges) {
            set.connect(edge.first, edge.second);
        }

        return set.countDisjointSets();
    }

    int countVertices(const std::vector<std::pair<int, int>>& edges) {
        std::set<int> result;

        for(const auto& edge : edges) {
            result.insert(edge.first);
            result.insert(edge.second);
        }

        return result.size();
    }
};