#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

class Solution {
public:
    std::string largestNumber(std::vector<int>& nums) {
        std::sort(nums.begin(), nums.end(), [this](int a, int b) {
            std::string aStr = std::to_string(a), bStr = std::to_string(b);
            return (aStr + bStr) > (bStr + aStr);
        });

        if(nums[0] == 0) {
            return "0";
        }

        std::stringstream stream;
        for(const auto num : nums) {
            stream << std::to_string(num);
        }
        return stream.str();
    }
};