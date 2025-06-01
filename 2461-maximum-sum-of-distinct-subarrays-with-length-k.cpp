#include <vector>
#include <unordered_set>

class SetWithSum {
private:
    std::unordered_set<int> numsInSubarray;
    long long sum = 0;

public:
    void insert(int num) {
        sum += num;
        numsInSubarray.insert(num);
    }

    void remove(int num) {
        sum -= num;
        numsInSubarray.erase(num);
    }

    bool contains(int num) {
        return numsInSubarray.count(num) > 0;
    }

    long long getSum() {
        return sum;
    }

};

class Solution {
public:
    long long maximumSubarraySum(std::vector<int>& nums, int k) {
        const int n = nums.size();

        if (k > n) {
            return 0;
        }

        long long result = 0;
        SetWithSum numsInSubarray;

        int start = 0, pointer = start;
        for (int start = 0; start + k <= n; start++) {
            while (pointer < start + k) {
                const int num = nums[pointer];
                if (numsInSubarray.contains(num)) {
                    break;
                } else {
                    numsInSubarray.insert(num);
                    pointer++;
                }
            }

            
            if (pointer - start == k) {
                result = std::max(numsInSubarray.getSum(), result);
            }

            numsInSubarray.remove(nums[start]);
        }

        return result;
    }
};