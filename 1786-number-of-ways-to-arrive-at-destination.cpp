#include <vector>

class Solution {
public:
    int minOperations(std::vector<int>& nums, int k) {
        int totalXor = getXor(nums) ^ k;

        int result = 0;
        while (totalXor) {
            result += (totalXor & 1);
            totalXor >>= 1;
        }

        return result;
    }

private:
    int getXor(const std::vector<int>& nums) {
        int result = 0;
        for(int num : nums) {
            result ^= num;
        }

        return result;
    }
};