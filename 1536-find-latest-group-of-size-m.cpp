#include <vector>

class Solution {
public:
    int findLatestStep(std::vector<int>& arr, int m) {
        std::vector<int> streakSizes(arr.size() + 2);

        if (arr.size() == m) {
            return m;
        }

        int answer = -1;
        for(int i = 0; i < arr.size(); i++) {
            const int index = arr[i];

            const int leftStreakSize = streakSizes[index - 1];
            const int rightStreakSize = streakSizes[index + 1];
            const int newStreakSize = leftStreakSize + rightStreakSize + 1;

            if (leftStreakSize == m || rightStreakSize == m) {
                answer = i; //actually previous step, but they're counting from 0
            }

            streakSizes[index - leftStreakSize] = streakSizes[index + rightStreakSize] = newStreakSize;
        }

        return answer;
    }
};