#include <vector>
#include <numeric>
#include <climits>
#include <algorithm>

class Solution {
public:
    int smallestRangeII(std::vector<int>& nums, int k) {
        const int n = nums.size();

        if(n <= 1) {
            return 0;
        }

        std::sort(nums.begin(), nums.end());

        int result = nums[n-1] - nums[0]; //In case the flip point is < 0
        for(int i = 0; i < n - 1; i++) {
            int maxValue = std::max(nums[i]+k, nums[n-1]-k);
            int minValue = std::min(nums[i+1]-k, nums[0] + k);
            int maxDiff = maxValue - minValue; //Max diff given that flip point is between i and i + 1
            result = std::min(result, maxDiff);
        }

        return result;
    }
};