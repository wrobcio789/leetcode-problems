#include <vector>
#include <algorithm>

const int MAX_NUM = 100 * 1000;
const int MAX_NUM_ROOT_CEIL = 317;

class Solution {
public:
    int longestSquareStreak(std::vector<int>& nums) {
        std::vector<int> streakTracker(MAX_NUM + 1);
        int result = -1;

        std::sort(nums.begin(), nums.end());
        for(int num : nums) {
            int newScore = streakTracker[num] + 1;
            if(newScore > result) {
                result = newScore;
            }
            if (num < MAX_NUM_ROOT_CEIL) {
                streakTracker[num * num] = newScore;
            }
        }  

        return result == 1 ? -1 : result;
    }
};