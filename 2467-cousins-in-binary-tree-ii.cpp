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
    TreeNode* replaceValueInTree(TreeNode* root) {
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

        dfsStack.push(TreeNodePair(root, 0));
        root->val = 0;
        while(!dfsStack.empty()) {
            TreeNodePair pair = dfsStack.top();
            dfsStack.pop();

            TreeNode* treeNode = pair.treeNode;
            const int level = pair.level;
            

            const int levelSum = levelToSumMap[level + 1];
            int childSum = (treeNode->left == nullptr? 0 : treeNode->left->val) + (treeNode->right == nullptr? 0 : treeNode->right->val);
            if(treeNode->left != nullptr) {
                treeNode->left->val = levelSum - childSum;
                dfsStack.push(TreeNodePair(treeNode->left, level + 1));
            }

            if(treeNode->right != nullptr) {
                treeNode->right->val = levelSum - childSum;
                dfsStack.push(TreeNodePair(treeNode->right, level + 1));
            }
        }


        return root;
    }
};