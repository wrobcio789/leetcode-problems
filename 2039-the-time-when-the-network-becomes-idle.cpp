#include <vector>
#include <queue>
#include <climits>

static const int ROOT = 0;
static const int INF = INT_MAX;

class Solution {

public:
    int networkBecomesIdle(std::vector<std::vector<int>>& edges, std::vector<int>& patience) {
        const int verticesSize = patience.size();

        std::vector<std::vector<int>> graph(verticesSize);
        for (const auto& edge : edges) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }

        std::vector<int> distances = runBFSDistances(graph);

        int result = 0;
        for(int i = 1; i < verticesSize; i++) {
            const int distance = distances[i];
            const int timeAwaiting = distance * 2;
            const int modulus = timeAwaiting % patience[i];
            const int whenLastMessageWasSent = timeAwaiting - (modulus == 0 ? patience[i] : modulus);
            const int whenLastMessageComesBack = whenLastMessageWasSent + timeAwaiting;
            
            if(result < whenLastMessageComesBack) {
                result = whenLastMessageComesBack;
            }
        }

        return result + 1;
    }

private:

    std::vector<int> runBFSDistances(const std::vector<std::vector<int>>& graph) {
        const int verticesSize = graph.size();        
        std::vector<int> distances(verticesSize, INF);
        distances[ROOT] = 0;

        std::queue<int> toVisitQueue;
        toVisitQueue.push(ROOT);
        while(!toVisitQueue.empty()) {
            const int vertex = toVisitQueue.front();
            const int distance = distances[vertex];
            toVisitQueue.pop();

            for(const int& neighbour : graph[vertex]) {
                if (distances[neighbour] > distance + 1) {
                    distances[neighbour] = distance + 1;
                    toVisitQueue.push(neighbour);
                }
            }
        }

        return distances;
    }
};