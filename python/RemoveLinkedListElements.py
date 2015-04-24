# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @param {ListNode} head
    # @param {integer} val
    # @return {ListNode}
    def removeElements(self, head, val):
        if not head:
            return None

        p, q = None, head

        while q is not None:
            if q.val == val:
                if p is None:  # q is head
                    head = q.next
                else:
                    p.next = q.next
                q = q.next
            else:
                p, q = q, q.next

        return head
