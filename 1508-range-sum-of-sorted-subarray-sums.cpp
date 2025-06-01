#include <vector>
#include <algorithm>

static const long long MODULO = 1e9 + 7;

class Solution {
public:
    int rangeSum(std::vector<int>& nums, int n, int left, int right) {

        std::vector<int> partialSum(n + 1);
        for(int i = 1; i <= n; i++) {
            partialSum[i] = partialSum[i-1] + nums[i-1];
        }

        std::vector<int> subarraySums((n * (n + 1)) / 2);
        int subarraySumIndex = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int rangeSum = partialSum[j + 1] - partialSum[i];
                subarraySums[subarraySumIndex++] = rangeSum;
            }
        }

        std::sort(subarraySums.begin(), subarraySums.end());

        long long result = 0;
        for(int i = left - 1; i<right; i++) {
            result = result + (long long)(subarraySums[i]);
        }

        return result % MODULO;
    }
};