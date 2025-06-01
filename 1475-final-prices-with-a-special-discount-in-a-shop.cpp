#include <vector>
#include <stack>
#include <utility>

class Solution {
public:
    std::vector<int> finalPrices(const std::vector<int>& prices) {
        const int n = prices.size();
        std::vector<int> result(n);

        std::stack<int> undiscountedPricesIndices;
        for(int i = 0; i < n; i++) {
            const int currentPrice = prices[i];

            while (!undiscountedPricesIndices.empty()) {
                const int indexOfFirstUndiscountedPrice = undiscountedPricesIndices.top();
                const int firstPrice = prices[indexOfFirstUndiscountedPrice];

                if (firstPrice >= currentPrice) {
                    result[indexOfFirstUndiscountedPrice] = firstPrice - currentPrice;
                    undiscountedPricesIndices.pop();
                } else {
                    break;
                }
            }

            undiscountedPricesIndices.push(i);
        }

        while (!undiscountedPricesIndices.empty()) {
            const int indexOfFirstUndiscountedPrice = undiscountedPricesIndices.top();
            const int firstPrice = prices[indexOfFirstUndiscountedPrice];   
            result[indexOfFirstUndiscountedPrice] = firstPrice;
            undiscountedPricesIndices.pop();
        }

        return result;
    }
};