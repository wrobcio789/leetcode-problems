#include <string>

class Solution {
public:
    bool rotateString(std::string s, std::string goal) {
        return s.size() == goal.size() && (s + s).find(goal) != std::string::npos;
    }
};