# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    # @return a ListNode
    def addTwoNumbers(self, l1, l2):
        p1 = l1
        p2 = l2
        begin = ListNode(0)
        r = begin
        sum = 0
        
        while p1 and p2:
            sum = p1.val + p2.val + sum / 10
            r.next = ListNode(sum % 10)
            p1 = p1.next
            p2 = p2.next
            r = r.next
        
        px = None
        if p1: px = p1
        elif p2: px = p2
        
        while px:
            sum = sum / 10 + px.val
            r.next = ListNode(sum % 10)
            px = px.next
            r = r.next
        
        if sum / 10 > 0:
            r.next = ListNode(sum / 10)
        
        return begin.next

