#include <vector>

class Solution {
public:
    long long countFairPairs(std::vector<int>& nums, int lower, int upper) {
        const int n = nums.size();
        std::sort(nums.begin(), nums.end());

        long long result = 0;
        for(int i = 0; i<n; i++) {
            const int num = nums[i];
            const int lowerBound = lower - num;
            const int upperBound = upper - num;

            auto lowerIt = std::lower_bound(nums.begin(), nums.end() - n + i, lowerBound);
            auto upperIt = std::upper_bound(nums.begin(), nums.end() - n + i, upperBound);

            const int added = std::distance(lowerIt, upperIt);
            result += added;
        }

        return result;
    }
};