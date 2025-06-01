#include <vector>
#include <algorithm>

class Solution {
public:
    long long minIncrementOperations(std::vector<int>& nums, int k) {
        std::vector<long long> cost = {0, LLONG_MAX, LLONG_MAX};

        for(const int num : nums) {
            long long costIfCurrentIsSet = std::max(0, k - num) + *std::min_element(cost.begin(), cost.end());
            cost[LAST] = cost[MIDDLE];
            cost[MIDDLE] = cost[CURRENT];
            cost[CURRENT] = costIfCurrentIsSet;
        }

        return *std::min_element(cost.begin(), cost.end());
    }

private:
    enum Index {
        CURRENT = 0,
        MIDDLE,
        LAST
    };
};