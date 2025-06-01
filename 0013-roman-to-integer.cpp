#include <string>
#include <stack>
#include <unordered_map>

static const std::unordered_map<char, int> ROMAN_TO_VALUE_MAP = {
    { 'I', 1 },
    { 'V', 5 },
    { 'X', 10 },
    { 'L', 50 },
    { 'C', 100 },
    { 'D', 500 },
    { 'M', 1000 },
};

class Solution {

public:
    int romanToInt(std::string& s) {
        std::stack<int> valuesToAdd;
        for (const auto& digit : s) {
            const int number = ROMAN_TO_VALUE_MAP.at(digit);
            if (!valuesToAdd.empty() && valuesToAdd.top() < number) {
                const int valueAtTop = valuesToAdd.top();
                valuesToAdd.pop();
                valuesToAdd.push(number - valueAtTop);
            } else {
                valuesToAdd.push(number);
            }
        }

        int result = 0;
        while(!valuesToAdd.empty()) {
            result += valuesToAdd.top();
            valuesToAdd.pop();
        }

        return result;

    }
};