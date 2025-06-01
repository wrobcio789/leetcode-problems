#include <vector>

class Solution {
public:
    bool canArrange(std::vector<int>& arr, int k) {
        std::vector<int> buckets = std::vector<int>(k);
        for(const auto number : arr) {
            buckets[(k + (number % k)) % k]++;
        }

        if(buckets[0] % 2 != 0) {
            return false;
        }

        for(int i = 1; i<=k/2; i++) {
            int corresponding = k - i;
            if(buckets[i] != buckets[corresponding]) {
                return false;
            }
        }

        return true;
    }
};