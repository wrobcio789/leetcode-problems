#include <algorithm>
#include <vector>
#include <queue>
#include <climits>

static const int MAX_VAL = 1e4 + 13;
static const int INF = INT_MAX / 2;

class Solution {
private:
    struct NumberCostPair{
        int num;
        int cost;

        NumberCostPair(int num, int cost) {
            this->num = num;
            this->cost = cost;
        }
    };

    struct NumberCostPairComparator {
        bool operator() (NumberCostPair& a, NumberCostPair& b) {
            return a.cost > b.cost;
        }
    };

public:
    int minimumOperationsToMakeEqual(int x, int y) {
        const int upperLimit = ((x + 55)/55) * 55;

        if(y >= x) {
            return y - x;
        }

        std::vector<int> costs(upperLimit + 1, INF);
        costs[x] = 0;

        std::priority_queue<NumberCostPair, std::vector<NumberCostPair>, NumberCostPairComparator> bfsQueue;
        bfsQueue.push(NumberCostPair(x, 0));

        while(!bfsQueue.empty()) {
            const auto pair = bfsQueue.top(); bfsQueue.pop();
            const int num = pair.num;
            const int cost = pair.cost;

            const int upper11 = upperMultiple(num, 11);
            const int upper5 = upperMultiple(num, 5);
            const int lower11 = lowerMultiple(num, 11);
            const int lower5 = lowerMultiple(num, 5);

            if (upper11 != lower11) {
                if (upper11 <= upperLimit) {
                    pushIfNeeded(bfsQueue, costs, upper11, cost + upper11 - num);
                }
                pushIfNeeded(bfsQueue, costs, lower11, cost + num - lower11);
            } else { //if num % 11 == 0
                pushIfNeeded(bfsQueue, costs, num/11, cost + 1);
            }

            if (upper5 != lower5) {
                if (upper5 <= upperLimit) {
                    pushIfNeeded(bfsQueue, costs, upper5, cost + upper5 - num);
                }
                pushIfNeeded(bfsQueue, costs, lower5, cost + num - lower5);
            } else { //if num % 5 == 0
                pushIfNeeded(bfsQueue, costs, num/5, cost + 1);
            }

            pushIfNeeded(bfsQueue, costs, y, cost + std::abs(num - y));

        }

        return costs[y];
    }

private:

    void pushIfNeeded(std::priority_queue<NumberCostPair, std::vector<NumberCostPair>, NumberCostPairComparator>& queue, std::vector<int>& costs, int num, int cost) {
        if(cost < costs[num]) {
            costs[num] = cost;
            queue.push(NumberCostPair(num, cost));
        }
    }


    int upperMultiple(int x, int base) {
        return ((x + base - 1)/base) * base;
    }

    int lowerMultiple(int x, int base) {
        return (x/base) * base;
    }
};