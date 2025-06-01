#include <string>
#include <vector>
#include <numeric>
#include <algorithm>

class Solution {
public:
    std::vector<int> smallestTrimmedNumbers(std::vector<std::string>& nums, std::vector<std::vector<int>>& queries) {
        const int numsSize = nums.size();
        std::vector<std::vector<int>> sortedNumsByKRightmost = radixSort(nums);

        const int queriesSize = queries.size();
        std::vector<int> result(queriesSize);
        for(int i = 0; i<queriesSize; i++) {
            int k = queries[i][0];
            int trim = queries[i][1];

            result[i] = sortedNumsByKRightmost[trim][k-1];
        }

        return result;
    }

private:
    std::vector<std::vector<int>> radixSort(std::vector<std::string>& nums) {
        int maxLength = 0;
        for(std::string& num : nums) {
            if(num.size() > maxLength) {
                maxLength = num.size();
            }
        }

        std::vector<std::vector<int>> sortedNumsByKRightmost(maxLength + 1, std::vector<int>(nums.size()));
        for(int i = 0; i< nums.size(); i++) {
            sortedNumsByKRightmost[0][i] = i;
        }

        for(int i = 1; i <= maxLength; i++) {
            countSort(nums, sortedNumsByKRightmost[i-1], i-1, sortedNumsByKRightmost[i]);
        }

        return sortedNumsByKRightmost;
    }

    void countSort(const std::vector<std::string>& nums, const std::vector<int>& prevSort, int digitIndex, std::vector<int>& dest) {
        static const int DIGITS = 10;
        int counter[DIGITS] = {};


        for(const std::string& num : nums) {
            int digit = getDigit(num, digitIndex);
            counter[digit]++;
        }

        for (int i = 1; i<DIGITS; i++) {
            counter[i] += counter[i-1];
        }

        for (int i = prevSort.size() - 1; i>=0; i--) {
            int prevNumIndex = prevSort[i];
            const std::string& num = nums[prevNumIndex];
            int digit = getDigit(num, digitIndex);
            int pos = --counter[digit];
            dest[pos] = prevNumIndex;
        }
    }

    int getDigit(const std::string& num, int digitIndex) {
        int index = num.size() - 1 - digitIndex;
        return index < 0 ? 0 : (num[index] - '0');
    }
};