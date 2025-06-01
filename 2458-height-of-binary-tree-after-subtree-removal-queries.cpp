#include <vector>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

const int MAX_N = 100 * 1000 + 1;

class Solution {

public:
    std::vector<int> treeQueries(TreeNode* root, std::vector<int>& queries) {
        std::vector<int> depth(MAX_N);
        calcDepth(depth, root);

        std::vector<int> maxTreeSizeLeft(MAX_N);
        passResult(maxTreeSizeLeft, depth, root, -1, 0);

        std::vector<int> result(queries.size());
        for(int i=0; i<queries.size(); i++) {
            result[i] = maxTreeSizeLeft[queries[i]];
        }

        return result;
    }

private:
    int calcDepth(std::vector<int>& depthStore, TreeNode* node) {
        if (node == nullptr) {
            return 0;
        }

        int left_depth = calcDepth(depthStore, node->left);
        int right_depth = calcDepth(depthStore, node->right);
        int depth = std::max(left_depth, right_depth) + 1;
        depthStore[node->val] = depth;

        return depth;
    }

    void passResult(std::vector<int>& result, std::vector<int>& depthStore, TreeNode* node, int level, int maxTreeSizeLeft) {
        if (node == nullptr) {
            return;
        }

        result[node->val] = maxTreeSizeLeft;

        int childLevel = level + 1;
        passResult(result, depthStore, node->left, childLevel, std::max(maxTreeSizeLeft, childLevel + (node->right == nullptr ? 0 : depthStore[node->right->val])));
        passResult(result, depthStore, node->right, childLevel, std::max(maxTreeSizeLeft, childLevel + (node->left == nullptr ? 0 : depthStore[node->left->val])));
    }

};