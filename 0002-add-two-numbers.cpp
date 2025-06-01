struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        
        ListNode root;
        ListNode* previous = &root;
        int rest = 0;
        while (l1 != nullptr || l2 != nullptr) {
            int sum = getValue(l1) + getValue(l2) + rest;
            if (sum >= 10) {
                sum -= 10;
                rest = 1;
            } else {
                rest = 0;
            }

            previous = add(previous, sum);
            l1 = getNext(l1);
            l2 = getNext(l2);
        }

        if (rest != 0) {
            add(previous, rest);
        }

        return root.next;
    }

private:
    ListNode* add(ListNode* to, int value) {
        ListNode* result = new ListNode(value);
        if (to != nullptr) {
            to->next = result;
        }
        return result;
    }

    int getValue(ListNode* node) {
        return node == nullptr ? 0 : node->val;
    }

    ListNode* getNext(ListNode* node) {
        return node == nullptr ? nullptr : node->next;
    }
};