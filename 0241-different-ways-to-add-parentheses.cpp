#include <string>
#include <vector>
#include <unordered_set>

class Solution {
public:
    std::vector<int> diffWaysToCompute(std::string expression) {
        if (isDigitNumber(expression)) {
            return std::vector<int>({parseNumber(expression)});
        }

        std::vector<int> result;
        for (int i = 0; i < expression.size() - 1; i++) {
            const char character = expression[i];
            if (character == '+' || character == '-' || character == '*') {
                const std::string leftExpr = expression.substr(0, i);
                const std::string rightExpr = expression.substr(i + 1);

                for (const int left : diffWaysToCompute(leftExpr)) {
                    for (const int right : diffWaysToCompute(rightExpr)) {
                        if (character == '*') {
                            result.push_back(left * right);
                        } else if (character == '-') {
                            result.push_back(left - right);
                        } else if (character == '+') {
                            result.push_back(left + right);
                        }
                    }
                }
            }
        }
        return result;
    }

private:

    bool isDigitNumber(std::string expression) {
        for(const char character : expression) {
            if (character < '0' || character > '9') {
                return false;
            }
        }
        return true;
    }

    int parseNumber(std::string expression) {
        int result = 0;
        for(const char character : expression) {
            result *= 10;
            result += character - '0';
        }
        return result;
    }
};