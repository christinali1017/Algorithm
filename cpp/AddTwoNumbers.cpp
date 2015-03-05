/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode *addTwoNumbers(ListNode *l1, ListNode *l2) {
        ListNode *p1 = l1, *p2 = l2;
        ListNode *r = new ListNode(0);
        ListNode *begin = r;
        int sum = 0;
        
        while (p1 && p2) {
            sum = p1->val + p2->val + sum / 10;
            r->next = new ListNode(sum % 10);
            p1 = p1->next;
            p2 = p2->next;
            r = r->next;
        }
        
        ListNode *px = NULL;
        if (p1) { px = p1; }
        else if (p2) { px = p2; }
        
        while (px) {
            sum = sum / 10 + px->val;
            r->next = new ListNode(sum % 10);
            px = px->next;
            r = r->next;
        }
        if (sum >= 10) { r->next = new ListNode(sum / 10); }
        
        return begin->next;
    }
    
};