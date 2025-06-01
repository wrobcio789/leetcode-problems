#include <vector>
#include <stack>
#include <climits>
#include <iostream>

class Solution {
public:
    int minimumMountainRemovals(std::vector<int>& nums) {
        const int size = nums.size();

        std::vector<int> lis(size), lds(size);

        for (int i = 0; i < size; i++) {
            const int num = nums[i];

            for (int j = 0; j < i; j++) {
                if (nums[j] < num) {
                    lis[i] = std::max(lis[i], lis[j] + 1);
                }
            }
        }


        for (int i = size - 1; i >= 0; i--) {
            const int num = nums[i];

            for (int j = size - 1; j > i; j--) {
                if (nums[j] < num) {
                    lds[i] = std::max(lds[i], lds[j] + 1);
                }
            }
        }

        int result = INT_MAX;
        for (int i = 1; i < size - 1; i++) {
            if (lis[i] > 0 && lds[i] > 0) {
                const int changesRequired = size - lis[i] - lds[i] - 1;
                if (changesRequired < result) {
                    result = changesRequired;
                }
            }
        }

        return result;
    }
};