#include <iostream>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <stack>

class Solution
{
public:
    int maxUniqueSplit(std::string s) {
        std::unordered_set<std::string> substrings;
        uint32_t result = 0;

        backtrack(s, 0, substrings, result);
        return result;
    }

    void backtrack(std::string s, uint32_t from, std::unordered_set<std::string> &substrings, uint32_t& answer) {
        if (from >= s.size()) {
            answer = std::max<uint32_t>(answer, substrings.size());
            return;
        }

        for (uint32_t i = from; i < s.size(); i++) {
            const std::string substr = s.substr(from, i - from + 1);
            if (substrings.count(substr) == 0) {
                substrings.insert(substr);
                backtrack(s, i + 1, substrings, answer);
                substrings.erase(substr);
            }
        }
    }
};