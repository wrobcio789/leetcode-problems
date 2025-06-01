#include <vector>
#include <string>

class Solution {
public:
    std::vector<std::string> letterCasePermutation(std::string s) {
        std::vector<int> letterPositions;
        for (int i = 0; i<s.size(); i++) {
            if (isLetter(s[i])) {
                letterPositions.push_back(i);
            }
        }

        const int letterCount = letterPositions.size();
        const int permutations = 1 << letterCount;

        std::vector<std::string> result; result.reserve(permutations);
        for(int i = 0; i<permutations; i++) {
            std::string currentStr = s;
            
            int permutationScheme = i;
            for(int j = 0; j<letterCount; j++) {
                const int letterIndex = letterPositions[j];
                if (permutationScheme & 1) {
                    currentStr[letterIndex] = toUppercase(currentStr[letterIndex]);
                } else {
                    currentStr[letterIndex] = toLowercase(currentStr[letterIndex]);
                }

                permutationScheme >>= 1;
            }

            result.push_back(currentStr);
        }

        return result;
    }

private:

    bool isLetter(char a) {
        return isLowercase(a) || isUpperCase(a);
    }

    char toLowercase(char a) {
        if(isUpperCase(a)) {
            return a - 'A' + 'a';
        }

        return a;
    }

    char toUppercase(char a) {
        if(isLowercase(a)) {
            return a - 'a' + 'A';
        }
        return a;
    }

    bool isLowercase(char a) {
        return a >= 'a' && a <= 'z';
    }

    bool isUpperCase(char a) {
        return a >= 'A' && a <= 'Z';
    }
};