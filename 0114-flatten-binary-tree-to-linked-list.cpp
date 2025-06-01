#include <iostream>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
public:
    void flatten(TreeNode* root) {
        if (root != nullptr) {
            flattenWithReturn(root);
        }
    }

private:
    TreeNode* flattenWithReturn(TreeNode* node) {
        TreeNode* left = node->left;
        TreeNode* right = node->right;
        TreeNode* lastNode = node;

        if (left != nullptr) {
            lastNode = flattenWithReturn(left);
            node->right = left;
            node->left = nullptr;
        }

        if (right != nullptr) {
            lastNode->right = right;
            lastNode = flattenWithReturn(right);
        }

        return lastNode;
    }
};