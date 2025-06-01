#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>
#include <queue>

class Solution {
public:
    int minExtraChar(std::string s, std::vector<std::string>& dictionary) {
        std::vector<std::vector<int>> jumps(s.size());

        for(int i = 0; i < s.size(); i++) {
            const std::string substr = s.substr(i);
            for(const auto dictWord : dictionary) {
                if (substr.rfind(dictWord, 0) == 0) {
                    jumps[i].push_back(i + dictWord.size());
                }
            }
        }

        std::vector<int> score(s.size() + 1);

        for(int i = s.size() - 1; i >= 0; i--) {
            score[i] = INT_MAX;

            for(const auto neigbour : jumps[i]) {
                if (score[neigbour] < score[i]) {
                    score[i] = score[neigbour];
                }
            }

            if (score[i + 1] + 1 < score[i]) {
                score[i] = score[i + 1] + 1;
            }
        }

        return score[0];
    }
};

