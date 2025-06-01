#include <vector>
#include <numeric>
#include <unordered_map>

static const int MAX_VAL = 100 + 1;
static const int MAX_TARGET = 301;
static const int MAX_COUNT_SIZE = MAX_TARGET;
static const int MOD = 1e9 + 7;

class Solution {
public:
    int threeSumMulti(std::vector<int>& arr, int target) {
        int COUNTS[MAX_COUNT_SIZE] = {};

        int result = 0;
        for (int i = 0; i < arr.size(); i++) {
            const int rest = target - arr[i];
            if (rest >= 0 ) {
                result += COUNTS[rest];
                result %= MOD;
            }

            for (int j = 0; j < i; j++) {
                COUNTS[arr[i] + arr[j]]++;
            }
        }

        return result;
    }
};