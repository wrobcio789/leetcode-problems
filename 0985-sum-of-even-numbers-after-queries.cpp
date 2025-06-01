#include <vector>
#include <numeric>

class Solution {
public:
    std::vector<int> sumEvenAfterQueries(std::vector<int>& nums, std::vector<std::vector<int>>& queries) {
        int evenSum = 0;
        for(auto num : nums) {
            if(num % 2 == 0) {
                evenSum += num;
            }
        }

        std::vector<int> result(queries.size());

        for(int i = 0; i<queries.size(); i++) {
            int index = queries[i][1];
            int value = queries[i][0];

            bool isNumsEven = nums[index] % 2 == 0;
            bool isValueEven = value % 2 == 0;
            if(isNumsEven) {
                evenSum -= nums[index];
                nums[index] += value;

                if(isValueEven) {
                    evenSum += nums[index];
                }
            } else {
                nums[index] += value;
                
                if(!isValueEven) {
                    evenSum += nums[index];
                }

            }

            result[i] = evenSum;
        }

        return result;
    }
};