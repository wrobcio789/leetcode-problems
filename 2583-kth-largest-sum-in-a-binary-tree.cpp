#include <stack>
#include <unordered_map>
#include <vector>
#include <algorithm>


struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

struct TreeNodePair {
    TreeNode* treeNode;
    int level;

    TreeNodePair(TreeNode* treeNode, int level) {
        this->treeNode = treeNode;
        this->level = level;
    }
};

class Solution {
public:
    long long kthLargestLevelSum(TreeNode* root, int k) {
        std::unordered_map<int, long long> levelToSumMap;

        std::stack<TreeNodePair> dfsStack;
        dfsStack.push(TreeNodePair(root, 0));

        while(!dfsStack.empty()) {
            TreeNodePair pair = dfsStack.top();
            dfsStack.pop();

            TreeNode* treeNode = pair.treeNode;
            const int level = pair.level;
            levelToSumMap[level] += treeNode->val;

            if(treeNode->left != nullptr) {
                dfsStack.push(TreeNodePair(treeNode->left, level + 1));
            }

            if(treeNode->right != nullptr) {
                dfsStack.push(TreeNodePair(treeNode->right, level + 1));
            }
        }

        std::vector<long long> sortedValues;
        sortedValues.reserve(levelToSumMap.size());
        for (auto levelAndSum : levelToSumMap) {
            sortedValues.push_back(levelAndSum.second);
        }
        std::sort(sortedValues.begin(), sortedValues.end());

        if (k > sortedValues.size()) {
            return -1;
        }
        return sortedValues[sortedValues.size() - k];
    }
};