#include <vector>

template<int N>
class BitCountMemory {
private:
    int arr[N];

public:
    constexpr BitCountMemory() : arr() {
        for (int i = 0; i < N; i++){
            arr[i] = countBits(i);
        }
    }

    int count(int num) const {
        return arr[num];
    }

private:
    constexpr int countBits(int num) { // Brian Kernighan’s algorithm
        int result = 0;
        while(num) {
            num &= (num - 1);
            result++;
        }
        return result;
    }
};

class Solution {
private:
    static constexpr int LIMIT = (1 << 8) + 1;
    static constexpr BitCountMemory<LIMIT> bitCounter = BitCountMemory<LIMIT>();

public:
    bool canSortArray(std::vector<int>& nums) {

        int highestNumberFromPrevGroups = -1;
        for(int i = 0; i < nums.size(); i++) {

            int groupStart = i;
            int highestNumberInThisGroup = nums[groupStart];
            int groupCount = bitCounter.count(nums[groupStart]);

            if(nums[groupStart] < highestNumberFromPrevGroups) {
                return false;
            }

            while(i + 1 < nums.size() && groupCount == bitCounter.count(nums[i+1])) {
                i++;
                highestNumberInThisGroup = std::max(highestNumberInThisGroup, nums[i]);

                if(nums[i] < highestNumberFromPrevGroups) {
                    return false;
                }
            }

            highestNumberFromPrevGroups = std::max(highestNumberFromPrevGroups, highestNumberInThisGroup);
        }

        return true;
    }

private:
    bool haveSameCount(int a, int b) {
        return bitCounter.count(a) == bitCounter.count(b);
    }
};