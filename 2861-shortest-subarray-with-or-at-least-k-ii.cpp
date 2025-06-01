#include <vector>
#include <climits>

static const int INT_BIT_COUNT = 8 * sizeof(int);

class Solution {
public:
    int minimumSubarrayLength(std::vector<int>& nums, int k) {
        if (k == 0) {
            return 1;
        }

        std::vector<int> collectedBits(INT_BIT_COUNT);
        const int n = nums.size();

        int result = INT_MAX;
        for (int i = 0, j = 0; i < n; i++) {
            j = std::max(j, i);

            while (!hasCollectedEnough(collectedBits, k) && j < n) {
                add(collectedBits, nums[j]);
                j++;
            }

            if (hasCollectedEnough(collectedBits, k)) {
                result = std::min(result, j-i);
            }

            remove(collectedBits, nums[i]);
        }

        return result == INT_MAX ? -1 : result;
    }

private:
    int hasCollectedEnough(const std::vector<int>& collectedBits, int k) {
        return toInt(collectedBits) >= k;
    }

    int toInt(const std::vector<int>& collectedBits) {
        int result = 0;
        for(int shift = 0; shift < INT_BIT_COUNT; shift++) {
            if (collectedBits[shift] > 0) {
                result |= (1 << shift);
            }
        }

        return result;
    }

    void add(std::vector<int>& collectedBits, int num) {
        modifyBy(collectedBits, num, 1);
    }

    void remove(std::vector<int>& collectedBits, int num) {
        modifyBy(collectedBits, num, -1);
    }

    void modifyBy(std::vector<int>& collectedBits, int num, int value) {
        for(int shift = 0; num > 0 && shift < INT_BIT_COUNT; shift++) {
            if (num & 1) {
                collectedBits[shift] += value;
            }
            num >>= 1;
        }
    }
};