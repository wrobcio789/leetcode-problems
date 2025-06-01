#include <algorithm>
#include <vector>

const long long MAX_VAL = 1e12;

class Solution {
public:
    long long minimumTotalDistance(std::vector<int>& robot,
                                   std::vector<std::vector<int>>& factory) {
        std::sort(robot.begin(), robot.end());
        std::sort(factory.begin(), factory.end(),
                  [](const std::vector<int>& a, const std::vector<int>& b) {
                      return a[0] < b[0];
                  });

        std::vector<int> factories = flatten(factory);
        std::vector<std::vector<long long>> partialResult(robot.size() + 1, std::vector<long long>(factories.size() + 1));
        for(int i = 0; i < robot.size(); i++) {
            partialResult[i][factories.size()] = MAX_VAL;
        }

        for (int robotIdx = robot.size() - 1; robotIdx >= 0; robotIdx--) {
            for (int factoryIdx = factories.size() - 1; factoryIdx >= 0; factoryIdx--) {
                long long distance = std::abs(robot[robotIdx] - factories[factoryIdx]);
                long long whenAssigned = distance + partialResult[robotIdx + 1][factoryIdx + 1];
                long long whenSkipping = partialResult[robotIdx][factoryIdx + 1];

                partialResult[robotIdx][factoryIdx] = std::min(whenAssigned, whenSkipping);
            }
        }

        return partialResult[0][0];
    }

private:
    std::vector<int> flatten(const std::vector<std::vector<int>>& factoriesDef) {
        std::vector<int> ret; 
        for (const auto& factory : factoriesDef) {
            for(int i = 0; i < factory[1]; i++) {
                ret.push_back(factory[0]);
            }
        }
        return ret;
    }
};