#include <string>
#include <stack>

static const int MAX_SIZE = 1e5 + 1;

class Solution {
private:
    struct PrefixAndTimesPair {
        std::string prefix;
        int times;

        PrefixAndTimesPair(const std::string& prefix, int times) {
            this->prefix = prefix;
            this->times = times;
        }
    };

public:
    std::string decodeString(const std::string& s) {
        std::string result;

        std::stack<PrefixAndTimesPair> callStack;
        for(int i = 0; i < s.size(); i++) {
            if (isDigit(s[i])) {
                int num = s[i] - '0';
                while(i+1 < s.size() && isDigit(s[i + 1])) {
                    i++;
                    num *= 10;
                    num += s[i] - '0';
                }
                i++; //Skip the '['
               callStack.push(PrefixAndTimesPair(result, num));
               result.clear();
            } else if (s[i] == ']') {
                const PrefixAndTimesPair& pair = callStack.top();
                result = pair.prefix + times(result, pair.times);

                callStack.pop();
            } else {
                result += s[i];
            }
        }

        return result;
    }

private:

    std::string times(std::string& str, int k) {
        std::string result; result.reserve(str.size() * k + 1);
        while(k--) {
            result += str;
        }
        return result;
    }

    bool isDigit(char rune) {
        return rune >= '0' && rune <= '9';
    }
};