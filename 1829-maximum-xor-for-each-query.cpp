#include <vector>
#include <numeric>

class Solution {
public:
    std::vector<int> getMaximumXor(std::vector<int>& nums, int maximumBit) {
        int totalXorSum = std::reduce(nums.cbegin(), nums.cend(), 0, [](int a, int b) { return a ^ b; });

        const int allOnes = (1 << maximumBit) - 1;
        const int n = nums.size();

        std::vector<int> result(n);
        for (int i = 0; i < n; i++) {
            result[i] = totalXorSum ^ allOnes;
            totalXorSum ^= nums[n - i - 1];
        }

        return result;
    }
};