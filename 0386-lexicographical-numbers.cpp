#include <iostream>
#include <vector>
#include <algorithm>

class Solution {
public:
    std::vector<int> lexicalOrder(int n) {
        std::vector<int> result;
        result.reserve(n);

        search(result, n, 1);
        return result;
    }

    void search(std::vector<int>& nums, int n, int base) {
        std::cout << "Called with base "<< base << std::endl;
        for (int i = base; i <= std::min(n, base == 1 ? 9 : base + 9); i++) {
            nums.push_back(i);
            search(nums, n, i * 10);
        }
    }
};

int main() {
    for (auto num : Solution().lexicalOrder(100)) {
        std::cout << num << ", ";
    }
    return 0;
}