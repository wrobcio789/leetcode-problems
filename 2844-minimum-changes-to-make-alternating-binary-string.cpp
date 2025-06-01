#include <string>

class Solution {
public:
    int minChanges(const std::string& s) {
        int result = 0;

        for(int i = 0; i < s.size(); i+=2) {
            char firstBit = s[i];
            char secondBit = s[i + 1];
            result += (firstBit != secondBit);
        }

        return result;
    }
};