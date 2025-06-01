#include <vector>
#include <algorithm>

static const int MAX_POS = 5 * 1e4 + 1;
static const int MIN_POS = -5 * 1e4 + 1;

class Solution {
public:
    int eraseOverlapIntervals(std::vector<std::vector<int>>& intervals) {
        std::sort(intervals.begin(), intervals.end(), [](const std::vector<int>& a, const std::vector<int>& b) {
            return a[1] < b[1];
        });

        int result = 0;
        int previousEnd = MIN_POS;
        for(const std::vector<int>& interval : intervals) {
            if (interval[0] < previousEnd) {
                result++;
            } else {
                previousEnd = interval[1];
            }
        }

        return result;
    }
};