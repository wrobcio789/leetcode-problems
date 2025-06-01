#include <vector>
#include <algorithm>
#include <climits>

class Solution {
public:
    int minSubArrayLen(int target, std::vector<int>& nums) {
        const int n = nums.size();
        
        int result = INT_MAX;
        int sum = 0;
        bool wasFound = false;
        for(int i=0, j=0; i < n; i++) {
            j = std::max(i, j);
            while(j < n && sum < target) {
                sum += nums[j];
                j++;
            }

            if (sum >= target) {
                result = std::min(j - i, result);
                wasFound = true;
            }

            sum -= nums[i];
        }

        if (wasFound) {
            return result;
        }

        return 0;
    }
};