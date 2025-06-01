#include <string>

const int ASCII_SIZE = 128;

class Solution {
public:
    int lengthOfLongestSubstring(std::string str) {
        const int length = str.size();

        bool usedStore[ASCII_SIZE] = {};

        int result = 0;
        for(int i = 0, j = 0; i<length; i++) {
            while(j < length && !isUsed(usedStore, str[j])) {
                usedStore[str[j]] = true;
                j++;
                const int substrSize = j - i;
                if (substrSize > result) {
                    result = substrSize;
                }
            }

            usedStore[str[i]] = false;
        }
        
        return result;
    }

private:
    bool isUsed(bool* store, char character) {
        return store[character];
    }
};