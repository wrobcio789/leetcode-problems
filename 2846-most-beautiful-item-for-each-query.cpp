#include <vector>
#include <algorithm>

class Solution {
public:
    std::vector<int> maximumBeauty(std::vector<std::vector<int>>& items, std::vector<int>& queries) {
        std::sort(items.begin(), items.end(), [](const std::vector<int>& a, const std::vector<int>& b) { return a[0] < b[0]; });

        std::vector<int> maxPrefixBeauty(items.size());
        maxPrefixBeauty[0] = items[0][1];
        for(int i = 1; i<items.size(); i++) {
            maxPrefixBeauty[i] = std::max(maxPrefixBeauty[i - 1], items[i][1]);
        }

        std::vector<int> result(queries.size());
        for(int i = 0; i<queries.size(); i++) {
            int left = 0, right = items.size();
            const int price = queries[i];

            int answer = 0;
            while(left < right) {
                const int mid = (left + right)/2;
                if (items[mid][0] > price) {
                    right = mid;
                } else {
                    answer = maxPrefixBeauty[mid];
                    left = mid + 1;
                }
            }

            result[i] = answer;
        }

        return result;
    }
};