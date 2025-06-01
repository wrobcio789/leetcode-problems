#include <vector>
#include <string>
#include <algorithm>
#include <iostream>

const int MINS_IN_DAY = 24 * 60;

class Solution {
public:
    int findMinDifference(std::vector<std::string>& timePoints) {
        std::vector<int> minutes(timePoints.size());
        for (int i = 0; i < timePoints.size(); i++) {
            minutes[i] = toMinutes(timePoints[i]);
        }

        std::sort(minutes.begin(), minutes.end());

        int result = INT_MAX;
        for(int i = 1; i<minutes.size(); i++) {
            int diff = minutes[i] - minutes[i - 1];
            if (diff < result) {
                result = diff;
            }
        }

        if (minutes.size() >= 2) {
            int diff = MINS_IN_DAY + minutes.front() - minutes.back();
            if (diff < result) {
                result = diff;
            }
        }
        
        return result;
    }

private:
    int toMinutes(const std::string& time) {
        return 600 * (time[0] - '0') + 60 * (time[1] - '0') + 10 * (time[3] - '0') + time[4] - '0';
    }
};