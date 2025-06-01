#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <stack>
#include <vector>
#include <cmath>

class Solution
{
public:
    int longestCommonPrefix(std::vector<int> &arr1, std::vector<int> &arr2) {
        for (int power = 8, threshold = 100*1000*1000; power >= 0; power--, threshold /= 10) {
            if (power == 0) {
                threshold = 0;
            }
            std::unordered_set<int> prefixesOfFirst;
            for (int i = 0; i < arr1.size(); i++) {
                const int num = arr1[i];
                if (num >= threshold) {
                    prefixesOfFirst.insert(num);
                    arr1[i] /= 10;
                }
            }

            for (int i = 0; i < arr2.size(); i++) {  
                const int num = arr2[i];
                if (num >= threshold) {
                    arr2[i] /= 10;
                    if (prefixesOfFirst.count(num) != 0) {
                        return power + 1;
                    }
                }
            }
        }

        return 0;
    }
};

int main() {
    std::vector<int> arr1 = {7, 6, 5};
    std::vector<int> arr2 = {9};
    auto result = Solution().longestCommonPrefix(arr1, arr2);
    std::cout << result << std::endl;
}