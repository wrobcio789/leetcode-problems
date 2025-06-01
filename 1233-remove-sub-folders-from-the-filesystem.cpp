#include <vector>
#include <string>
#include <algorithm>
#include <iostream>

class Solution {
public:
    std::vector<std::string> removeSubfolders(std::vector<std::string>& folders) {
        std::sort(folders.begin(), folders.end());
        std::string guard = "-";
        std::string* lastStr = &guard;
        std::vector<std::string> result;
        for(auto& str : folders) {
            if (!(str.starts_with(*lastStr)) || (str.size() > lastStr->size() && str[lastStr->size()] != '/')){
                result.push_back(str);
                lastStr = &str;
            }
        }
        return result;
    }
};