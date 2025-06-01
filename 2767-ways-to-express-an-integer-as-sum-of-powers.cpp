#include <vector>
#include <cstdint>
#include <iostream>
#include <algorithm>

static const int MODULO = 1e9 + 7;

class Solution {
public:
    int numberOfWays(int n, int x) {
        std::vector<int> numberOfWays(n + 1);

        numberOfWays[0] = 1;
        
        for (int max = 1; max <= n; max++) {
            const int64_t maxRaisedToX = pow(max, x);
            const int partitionPoint = std::min<int64_t>(maxRaisedToX, n + 1);

            for(int num = n; num >= partitionPoint; num--) {
                const int64_t previousNumber = num - partitionPoint;
                numberOfWays[num] = numberOfWays[num] + numberOfWays[previousNumber];
                numberOfWays[num] %= MODULO;
            }
        }


        return numberOfWays[n];
    }

private:
    int64_t pow(int64_t base, int exp) {
        int64_t result = 1;
        while(exp--) {
            result *= base;
        }

        return result;
    }
};