#include <vector>
#include <algorithm>
#include <climits>
#include <numeric>
#include <iostream>

class Solution {
public:
    long long minCost(std::vector<int>& cost, int shiftCost) {
        const int itemsCount = cost.size();

        std::vector<long long> minCost(itemsCount, INT_MAX);

        int shift;
        for (shift = 0; shift < itemsCount; shift++) {

            long long savingsForThisShift = 0;
            for (int i = 0; i < itemsCount; i++) {
                const int offset = (i + itemsCount - shift) % itemsCount;

                if (minCost[i] > cost[offset]) {
                    const long long savings = (minCost[i] - cost[offset]);
                    savingsForThisShift += savings;
                }
            }

            if (savingsForThisShift >= shiftCost ) {

                for (int i = 0; i < itemsCount; i++) {
                    const int offset = (i + itemsCount - shift) % itemsCount;
                    minCost[i] = std::min<long long>(minCost[i], cost[offset]);
                }
            } else {
                break;
            }
        }

        return std::reduce(minCost.begin(), minCost.end()) + (long long)(shift - 1) * shiftCost;
    }
};