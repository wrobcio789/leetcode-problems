#include <string>
#include <sstream>

class Solution {
public:
    std::string makeFancyString(std::string& s) {
        char lastChar = '-';
        int streak = 0;

        std::stringstream ss;
        for(const char& character : s) {
            if (character != lastChar) {
                lastChar = character;
                streak = 1;
                ss << character;
            } else {
                streak++;
                if(streak < 3) {
                    ss << character;
                }
            }
        }

        return ss.str();
    }
};