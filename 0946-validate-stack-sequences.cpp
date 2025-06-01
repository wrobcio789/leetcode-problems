#include <vector>
#include <stack>

class Solution {
public:
    bool validateStackSequences(std::vector<int>& pushed, std::vector<int>& popped) {
        std::stack<int> stack;

        int popIndex = 0;
        for(auto toPush : pushed) {
            stack.push(toPush);

            while(!stack.empty() && popped[popIndex] == stack.top()) {
                popIndex++;
                stack.pop();
            }
        }

        return stack.empty();
    }
};