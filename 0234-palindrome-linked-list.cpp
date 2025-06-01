#include <utility>

struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};
 
class Solution {
public:
    bool isPalindrome(ListNode* head) {
        return isPalindrome(head, head).first;
    }

private:
    std::pair<bool, ListNode*> isPalindrome(ListNode* front, ListNode* last) {
        if (last->next == nullptr) {
            bool isPalindrome = front->val == last->val;
            return std::make_pair(isPalindrome, front->next);
        }

        auto[doesNextMatch, frontNode] = isPalindrome(front, last->next);
        return std::make_pair(doesNextMatch && frontNode->val==last->val, frontNode->next);
    }
};