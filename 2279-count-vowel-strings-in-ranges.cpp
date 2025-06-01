#include <vector>
#include <string>

class Solution {
public:
    std::vector<int> vowelStrings(std::vector<std::string>& words, std::vector<std::vector<int>>& queries) {
        std::vector<int> result;
        result.reserve(words.size());

        std::vector<int> sumOfMatchingWordsToIndex = std::vector<int>(words.size());
        for(int i = 0, count = 0; i < words.size(); i++) {
            const auto& word = words[i];
            const auto countAtIndex = isVowel(word) ? ++count : count;
            sumOfMatchingWordsToIndex[i] = countAtIndex;
    
        }

        for(const auto& query : queries) {
            const auto begin = query.front();
            const auto end = query.back();
            const auto numberOfWordsMatchingInRange = sumOfMatchingWordsToIndex[end] - (begin == 0 ? 0 : sumOfMatchingWordsToIndex[begin - 1]);
            result.push_back(numberOfWordsMatchingInRange);
        }

        return result;

    }

private:
    bool isVowel(const std::string& word) {
        return isVowel(word.front()) && isVowel(word.back());
    }

    bool isVowel(const char character) {
        return character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u';
    }
};