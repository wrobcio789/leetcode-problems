#include <string>
#include <sstream>
#include <iostream>

class Solution {
public:
    std::string compressedString(const std::string& word) {
        std::stringstream ss;

        for(int i = 0; i < word.size();) {

            int j = 1;
            while (j < 9 && i + j < word.size() && word[i] == word[i + j]) {
                j++;
            }

            ss << char('0' + j) << word[i];
            i += j;
        }   

        return ss.str();     
    }
};