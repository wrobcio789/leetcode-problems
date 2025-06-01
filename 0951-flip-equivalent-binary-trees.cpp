#include <iostream>
#include <stack>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

struct NodePair {
    TreeNode* first;
    TreeNode* second;

    NodePair(TreeNode* first, TreeNode* second) {
        this->first = first;
        this->second = second;
    }
};

class Solution {
public:
    bool flipEquiv(TreeNode* root1, TreeNode* root2) {
        if (root1 == nullptr && root2 == nullptr) {
            return true;
        }

        if(getValue(root1) != getValue(root2)) {
            return false;
        }

        std::stack<NodePair> dfsStack;
        dfsStack.push(NodePair(root1, root2));
        while(!dfsStack.empty()) {
            const NodePair pair = dfsStack.top();
            dfsStack.pop();

            const auto first = pair.first;
            const auto second = pair.second;

            if (getValue(first->left) == getValue(second->left) && getValue(first->right) == getValue(second->right)) {
                if (first->left != nullptr) {
                    dfsStack.push(NodePair(first->left, second->left));
                }
                if (first->right != nullptr) {
                    dfsStack.push(NodePair(first->right, second->right));
                }
            } else if (getValue(first->left) == getValue(second->right) && getValue(first->right) == getValue(second->left)) {
                if (first->left != nullptr) {
                    dfsStack.push(NodePair(first->left, second->right));
                }
                if (first->right != nullptr) {
                    dfsStack.push(NodePair(first->right, second->left));
                }
            } else {
                return false;
            }

        }

        return true;
    }

private:
    int getValue(TreeNode* node) {
        return node == nullptr ? -1 : node->val;
    }
};