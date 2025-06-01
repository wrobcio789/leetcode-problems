#include <vector>
#include <algorithm>

class Solution {
public:
    std::vector<int> sortArrayByParity(std::vector<int>& nums) {
        int baseIndex = 0;
        while(baseIndex < nums.size() && nums[baseIndex] % 2 == 0) {
            baseIndex++;
        }

        int searchIndex = baseIndex+1;
        while (searchIndex < nums.size()) {
            if (nums[searchIndex] % 2 == 0) {
                std::swap(nums[baseIndex], nums[searchIndex]);
                while (baseIndex < nums.size() && nums[baseIndex] % 2 == 0) {
                    baseIndex++;
                }
            }

            searchIndex = std::max(searchIndex + 1, baseIndex + 1);
        }

        return nums;
    }
};