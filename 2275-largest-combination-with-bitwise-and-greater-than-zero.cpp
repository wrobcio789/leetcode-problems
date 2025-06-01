#include <vector>
#include <algorithm>

static const int INT_BIT_SIZE = 8 * sizeof(int);

class Solution {
public:
    int largestCombination(std::vector<int>& candidates) {
        std::vector<int> byteCount(INT_BIT_SIZE);

        for(int num : candidates) {
            int byteNum = 0;
            while(num) {
                byteCount[byteNum++] += num & 1;
                num >>= 1;
            }
        }

        return *std::max_element(byteCount.cbegin(), byteCount.cend());
    }
};