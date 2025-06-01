#include <vector>
#include <cstdlib>

static const int MAX_DIGITS_IN_NUM = 10;
static const int SYSTEM_BASE = 10;

class Solution {
public:
    long long sumDigitDifferences(std::vector<int>& nums) {
        std::vector<std::vector<int>> digitCount(MAX_DIGITS_IN_NUM, std::vector<int>(SYSTEM_BASE, 0));

        for(int i = 0; i<nums.size(); i++) {
            int num = nums[i];

            for (int j = 0; num != 0; j++, num /= 10) {
                const int digit = num % 10;
                digitCount[j][digit] ++;
            }
        }

        long long result = 0;
        for(int i = 0; i<MAX_DIGITS_IN_NUM; i++) {
            long long digitsCountSoFar = 0;
            for (int j = 0; j < SYSTEM_BASE; j++) {
                result += digitCount[i][j] * digitsCountSoFar;
                digitsCountSoFar += digitCount[i][j];
            }
        }

        return result;
    }

private:
    int calcDigitSum(int num) {
        int result = 0;
        while(num != 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }
};