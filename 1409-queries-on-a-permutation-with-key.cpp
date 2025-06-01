#include <vector>

class Solution {
public:
    std::vector<int> processQueries(std::vector<int>& queries, int m) {
        std::vector<int> numMap(m);
        for(int i = 0; i < m; i++) {
            numMap[i] = i;
        }

        std::vector<int> result(queries.size());
        for(int i = 0; i < queries.size(); i++) {
            const int query = queries[i] - 1;

            const int numLocation = numMap[query];
            result[i] = numLocation;

            for(int j = 0; j < m; j++) {
                numMap[j] += (numLocation > numMap[j]);
            }
            numMap[query] = 0;

        }

        return result;
    }
};