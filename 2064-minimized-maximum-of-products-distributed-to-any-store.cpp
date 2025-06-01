#include <vector>

class Solution {
public:
    int minimizedMaximum(int n, std::vector<int>& quantities) {
        const int maxQuantity = max(quantities);

        int left = 1, right = maxQuantity + 1;
        while(left < right) {
            const int mid = (left + right)/2;
            if (canKBeTheAnswer(n, quantities, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return right;
    }

private:
    bool canKBeTheAnswer(int n, const std::vector<int>& quantities, int k) {
        int divisionsNeeded = 0;
        for(int i = 0; i < quantities.size(); i++) {
            divisionsNeeded += (quantities[i] + k - 1) / k;
        }

        return divisionsNeeded <= n;
    }

    int max(const std::vector<int>& quantities) {
        int result = quantities[0];
        for (int i = 1; i < quantities.size(); i++) {
            if(quantities[i] > result) {
                result = quantities[i];
            }
        }

        return result;
    }
};

