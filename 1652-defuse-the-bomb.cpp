#include <vector>

class Solution {
public:
    std::vector<int> decrypt(std::vector<int>& code, int k) {
        const int n = code.size();

        if (k == 0) {
            return std::vector<int>(n, 0);
        }

        std::vector<int> result(n);

        std::vector<int> partialSum(n);
        if (k > 0) {
            partialSum[0] = code[0];
            for (int i = 1; i < n; i++) {
                partialSum[i] = partialSum[i - 1] + code[i];
            }

            for(int i = 0; i < n; i++) {
                if (i + k < n) {
                    result[i] = partialSum[i + k] - partialSum[i];
                } else {
                    result[i] = partialSum[n - 1] - partialSum[i] + partialSum[(i + k) % n];
                }
            }
        }

        if (k < 0) {
            partialSum[n - 1] = code[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                partialSum[i] = partialSum[i + 1] + code[i];
            }

            for(int i = 0; i < n; i++) {
                if (i + k >= 0) {
                    result[i] = partialSum[i + k] - partialSum[i];
                } else {
                    result[i] = partialSum[0] - partialSum[i] + partialSum[(i + k + n) % n];
                }
            }
        }

        return result;
    }
};