#include <vector>

class Solution {
public:
    int numSubarrayBoundedMax(std::vector<int>& nums, int left, int right) {
        const int upperCount = countSubarraysWithNumsLessThanK(nums, right);
        const int lowerCount = countSubarraysWithNumsLessThanK(nums, left - 1);
        return upperCount - lowerCount;
    }

private:
    int countSubarraysWithNumsLessThanK(std::vector<int>& nums, const int k) {
        int result = 0;

        int length = 0;
        for(int i = 0; i < nums.size(); i++) {
            if(nums[i] > k) {
                result += countSubarraysForLength(length);
                length = 0;
            } else {
                length++;
            }
        }
        result += countSubarraysForLength(length);

        return result;
    }

    int countSubarraysForLength(int n) {
        return (n * (n+1LL)) / 2LL;
    }
};